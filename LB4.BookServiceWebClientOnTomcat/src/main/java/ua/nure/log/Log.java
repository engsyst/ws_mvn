package ua.nure.log;

public class Log {
	private Class<?> clazz;
	public static final int TRACE = 3;
	public static final int DEBUG = 2;
	public static final int INFO = 1;
	public static final int ERROR = 0;
	
	private int level;
	
	private StringBuffer sb = new StringBuffer();
	private int sblen;
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	private Log(int logLevel) {
		level = logLevel;
	}

	private Log(int logLevel, Class<?> c) {
		this(logLevel);
		if (c != null) {
			clazz = c;
			sb.append(clazz.getSimpleName()).append(": ");
			sblen = sb.length();
		}
	}
	
	public static Log getInstance(int logLevel) {
		Log log = new Log(logLevel);
		return log;
	}

	public static Log getInstance(int logLevel, Class<?> c) {
		return new Log(logLevel, c);
	}
	
	private void clear() {
		sb.setLength(sblen);
	}
	
	private void print(String... msgs) {
		clear();
		for (String s : msgs) {
			sb.append(s).append(", ");
		}
		System.out.println(sb.toString());
	}
	
	public void error(String... msgs) {
		print(msgs);
	}
	
	public void info(String... msgs) {
		if (level >= INFO)
			print(msgs);
	}
	
	public void debug(String... msgs) {
		if (level >= DEBUG)
			print(msgs);
	}
	
	public void trace(String... msgs) {
		if (level >= TRACE)
			print(msgs);
	}
}
