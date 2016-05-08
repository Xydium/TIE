package engine.audio;

import java.util.HashMap;
import java.util.Map;

import kuusisto.tinysound.Music;
import kuusisto.tinysound.Sound;
import kuusisto.tinysound.TinySound;

public class GlobalAudio 
{

	private static Map<String, Sound> sounds;
	private static Map<String, Music> music;
	
	public static void initAudio()
	{
		sounds = new HashMap<String, Sound>();
		music = new HashMap<String, Music>();
		TinySound.init();
	}
	
	public static void addSound(String name, String path) {
		sounds.put(name, TinySound.loadSound(path));
	}
	
	public static void addMusic(String name, String path) {
		music.put(name, TinySound.loadMusic(path));
	}
	
	public static void removeSound(String name) {
		sounds.remove(name);
	}
	
	public static void removeMusic(String name) {
		music.remove(name);
	}
	
	public static void playSound(String name, double vol) {
		sounds.get(name).play(vol);
	}
	
	public static void playSound(String name) {
		playSound(name, 1.0);
	}
	
	public static void stopSound(String name) {
		sounds.get(name).stop();
	}
	
	private static void playMusic(String name, double vol, boolean loop) {
		music.get(name).play(loop, vol);
	}
	
	public static void playMusic(String name) {
		playMusic(name, 1.0, false);
	}
	
	public static void playMusic(String name, double volume) {
		playMusic(name, volume, false);
	}
	
	public static void loopMusic(String name) {
		playMusic(name, 1.0, true);
	}
	
	public static void loopMusic(String name, double vol) {
		playMusic(name, vol, true);
	}
	
}
