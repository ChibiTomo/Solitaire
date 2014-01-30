package yt;

public class YT {

	public static final String EOL = System.getProperty("line.separator");

	public static void println(Object o) {
		if (o != null) {
			print(o.toString() + EOL);
		} else {
			print(null + EOL);
		}
	}

	public static void println(int n) {
		println("" + n);
	}

	public static void println() {
		println("");
	}

	public static void print(Object o) {
		System.out.print(o.toString());
	}

	public static boolean isInt(String string) {
		try {
			Integer.parseInt(string);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	public static void debug(Object o) {
		Exception e = new Exception();
		StackTraceElement[] stack = e.getStackTrace();
		StackTraceElement el = stack[1];

		String className = el.getClassName();
		String methodName = el.getMethodName();
		String fileName = el.getFileName();
		int line = el.getLineNumber();

		println("[DEBUG] " + className + "." + methodName + "(" + fileName
				+ ":" + line + ")");
		println(o);
		println();
	}

	public static String getErrorLocation(Exception e) {
		StackTraceElement[] stack = e.getStackTrace();
		StackTraceElement el = stack[0];

		String className = el.getClassName();
		String methodName = el.getMethodName();
		String fileName = el.getFileName();
		int line = el.getLineNumber();

		String location = className + "." + methodName + "(" + fileName + ":"
				+ line + ")";
		return location;
	}
}
