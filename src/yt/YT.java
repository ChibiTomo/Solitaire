package yt;

public class YT {

	public static final String EOL = System.getProperty("line.separator");

	public static void println(String msg) {
		print(msg + EOL);
	}

	public static void println(int n) {
		println("" + n);
	}

	public static void print(String string) {
		System.out.print(string);
	}

	public static boolean isInt(String string) {
		try {
			Integer.parseInt(string);
			return true;
		} catch (Exception e) {
		}
		return false;
	}
}
