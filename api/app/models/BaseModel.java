package models;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

public class BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9127819341547937253L;
	
	@Override
	public Object clone() {
		try {
			Object obj = new Object();
			BeanUtils.copyProperties(obj, this);
			return obj;
		} catch (IllegalAccessException e) {
			return null;
		} catch (InvocationTargetException e) {
			return null;
		}
	}

}
