package test;

import engine.core.Application;
import engine.rendering.WindowFlags;

public class Main
{
	public static void main(String[] args)
	{
		WindowFlags flags = new WindowFlags("My window", 800, 600);
		
		Application app = new Application(new TestGame(), 60.0, flags);
		app.start();
	}
}
