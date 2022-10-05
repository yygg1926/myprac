package prac01.test01.context;


import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.reflections.Reflections;

import prac01.test01.annotation.Component;

@SuppressWarnings("deprecation")
public class ApplicationContext {

	Hashtable<String,Object> objTable = new Hashtable<String,Object>();
	
	public Object getBean(String key) {
		return objTable.get(key);
	}
	
	public ApplicationContext(String propertiesPath) throws Exception{
		Properties props = new Properties();	// Properties클래스는 key,value형식 데이터를 다룰때 사
		props.load(new FileReader(propertiesPath));
		
		prepareObjects(props);
		prepareAnnotationObjects(); // 애너테이션 붙은 객체 생
		injectDependency();
	}
	
	
	private void prepareAnnotationObjects() throws Exception{
		Reflections reflector = new Reflections("");
		
		Set<Class<?>> list = reflector.getTypesAnnotatedWith(Component.class);
		String key = null;
		
		for(Class<?> clazz : list) {
			key = clazz.getAnnotation(Component.class).value();
			objTable.put(key, clazz.newInstance());
		}
	}
	
	private void prepareObjects(Properties props) throws Exception{
		Context ctx = new InitialContext();
		String key = null;
		String value = null;
		
		for(Object item : props.keySet()) {	// 프로퍼티 파일의 key값들을 가져온
			key = (String)item;
			value = props.getProperty(key);
			if(key.startsWith("jndi.")) {
				objTable.put(key, ctx.lookup(value));	// ctx.lookup을 이용해 JNDI인터페이스를 통해 톰캣 서버에 등록된 객체를 찾는
			}else {
				objTable.put(key, Class.forName(value).newInstance());	// 인스턴스 생
			}
		}
	}
	
	private void injectDependency() throws Exception{
		for(String key : objTable.keySet()) {
			if(!key.startsWith("jndi.")) {
				callSetter(objTable.get(key));
			}
		}
	}
	
	private void callSetter(Object obj) throws Exception{
		Object dependency = null;
		for(Method m : obj.getClass().getMethods()) {
			if(m.getName().startsWith("set")) {
				dependency = findObjectByType(m.getParameterTypes()[0]);
				if(dependency != null) {
					m.invoke(obj, dependency);
				}
			}
		}
	}
	
	private Object findObjectByType(Class<?> type) {
		for(Object obj : objTable.values()) {
			if(type.isInstance(obj)) {
				return obj;
			}
		}
		return null;
	}
}
