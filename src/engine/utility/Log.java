package engine.utility;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Writes Log messages to an internal buffer and flushes them to
 * text files upon overflow or user request.
 * 
 * @author Tim Hornick
 *
 */
public class Log 
{
	private static final int MAX_LOG_BUFFER_SIZE = 100;
	private static final long FILE_RUNTIME_ID = System.currentTimeMillis();	

	public static LogLevel logLevel = LogLevel.INTERNAL;
	public static boolean consoleEnabled = true;		
	
	private static ArrayList<String> lines = new ArrayList<String>();

	public static void error(String msg) 
	{
		addLine(LogLevel.ERROR, msg);
	}
	
	public static void warning(String msg) 
	{
		addLine(LogLevel.WARNING, msg);
	}
	
	public static void info(String msg) 
	{
		addLine(LogLevel.INFO, msg);
	}
	
	public static void debug(String msg) 
	{
		addLine(LogLevel.DEBUG, msg);
	}
	
	public static void internal(String msg) 
	{ 
		addLine(LogLevel.INTERNAL, msg);
	}
	
	private static void addLine(LogLevel level, String msg) 
	{
		if(level.ordinal() <= logLevel.ordinal()) 
		{
			String line = level.tag + " " + msg;
			lines.add(line);
			if(consoleEnabled) System.out.println(line);
			if(lines.size() >= MAX_LOG_BUFFER_SIZE) flushLog();
		}
	}
	
	private static void flushLog() 
	{
		try 
		{
			FileWriter file = new FileWriter("LOG_" + FILE_RUNTIME_ID + ".txt");
			BufferedWriter bf = new BufferedWriter(file);
			for(String s : lines) 
			{
				bf.write(s + "\n");
			}
			bf.close();
		} catch (IOException e) { e.printStackTrace(); }
		lines.clear();
	}
	
	public enum LogLevel 
	{
		NONE(""),
		ERROR("[ERROR]"),
		WARNING("[WARNING]"),
		INFO("[INFO]"),
		DEBUG("[DEBUG]"),
		INTERNAL("[INTERNAL]");
		
		public String tag;
		
		private LogLevel(String tag) 
		{
			this.tag = tag;
		}
	}
	
}
