package com.vurtnec.component.util;

public class Objects {

	public static String toString(Object object) {
		String objStr = "";
		if(object != null) {
			objStr = object.toString();
		}
		return objStr;
	}
}
