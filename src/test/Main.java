package test;

import engine.core.Application;
import engine.rendering.WindowFlags;
import engine.utility.Log.LogLevel;

public class Main
{
	public static void main(String[] args)
	{
		WindowFlags flags = new WindowFlags("My window", 800, 600);
		flags.setLogLevel(LogLevel.INTERNAL);
		flags.setConsoleEnabled(true);
		flags.setIconFiles("icon16.png", "icon32.png");
		
		Application app = new Application(new ShaderTester(), 60.0, flags);
		app.start();
	}
}
