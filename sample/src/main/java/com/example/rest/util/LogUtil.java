package com.example.rest.util;

public class LogUtil {

	private LogUtil() {}

	public static String getErrorStrFromException(Throwable exe) {

		int level = 3;
		int counter = 0;

		if (null == exe) {
			return "nullString";
		}

		StackTraceElement[] stackTraceElementArray = exe.getStackTrace();
		StringBuilder stringBuilder = new StringBuilder(exe.toString());
		stringBuilder.append("# detailMessage: ").append(exe.getMessage());
		stringBuilder.append("# cause: ").append(exe.getCause());

		if (null != stackTraceElementArray) {
			
			for (StackTraceElement stackTraceElement : stackTraceElementArray) {
				stringBuilder.append("# ").append(stackTraceElement.toString());
				counter++;
				if (counter > level) {
					break;
				}
			}
			
		}
		return stringBuilder.toString();
	}

}
