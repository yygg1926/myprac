package prac01.test01.bind;

import java.lang.reflect.Method;

import javax.servlet.ServletRequest;

public class ServletRequestDataBinder {
	
	public static Object bind(ServletRequest req, Class<?> dataType, String dataName) throws Exception{
		if(isPrimitiveType(dataType)) {
			return createValueObject(dataType, req.getParameter(dataName));
		}
		
		set<String> paramNames = req.getParameterMap().keySet();
		Object dataObject = dataType.newInstance();
		Method m = null;
		
		for(String paramName : paramNames) {
			m = findSetter(dataType, paramName);
			if(m != null) {
				m.invoke(dataObject, createValueObject(m.getParameterTypes()[0],
						req.getParameter(paramName)));
			}
		}
		return dataObject;
	}
	
	private static boolean isPrimitiveType(Class<?> type) {
		if(type.getName().equals("int") || type == Integer.class ||
				type.getName())
	}
	
}
