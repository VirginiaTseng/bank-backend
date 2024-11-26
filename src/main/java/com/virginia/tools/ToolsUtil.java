package com.virginia.tools;

public class ToolsUtil {

	public static boolean isEmpty(Object obj) {
		return null == obj || obj.toString().trim().length() == 0;
	}

	public static int strNums(String str, char b) {
		int count = 0;
		char a[] = str.toCharArray();
		for (char i : a) {
			if (i == b)
				count++;
		}
		return count;
	}

}
