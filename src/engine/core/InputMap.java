package engine.core;

import java.util.ArrayList;
import java.util.HashMap;

public class InputMap 
{

	private HashMap<String, ArrayList<Integer>> inputMap;
	
	public InputMap()
	{
		inputMap = new HashMap<String, ArrayList<Integer>>();
	}
	
	public void addKey(String name, int... keys) 
	{
		ArrayList<Integer> keyConns = new ArrayList<Integer>();
		for(int k : keys) 
		{
			keyConns.add(k);
		}
		inputMap.put(name, keyConns);
	}
	
	public boolean getKey(String key) 
	{
		ArrayList<Integer> keys = inputMap.get(key);
		for(int k : keys) 
		{
			if(Input.getKey(k)) 
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean getKeyDown(String key)
	{
		ArrayList<Integer> keys = inputMap.get(key);
		for(int k : keys)
		{
			if(Input.getKeyDown(k)) 
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean getKeyUp(String key)
	{
		ArrayList<Integer> keys = inputMap.get(key);
		for(int k : keys)
		{
			if(Input.getKeyDown(k))
			{
				return true;
			}
		}
		return false;
	}
	
}
