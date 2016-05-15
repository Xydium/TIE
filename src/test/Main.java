package test;

import engine.core.Application;
import engine.rendering.WindowFlags;
import engine.utility.Log;
import engine.utility.Log.LogLevel;

public class Main
{
	public static void main(String[] args)
	{
		WindowFlags flags = new WindowFlags("My window", 800, 600);
		flags.setLogLevel(LogLevel.INTERNAL);
		flags.setConsoleEnabled(true);
		
		Application app = new Application(new TestGame(), 60.0, flags);
		app.start();
	}
}
