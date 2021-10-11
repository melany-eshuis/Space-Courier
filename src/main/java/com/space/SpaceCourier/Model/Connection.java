package com.space.SpaceCourier.Model;

import java.util.ArrayList;

public class Connection 
{
	private Star firstPlanet;
	private Star secondPlanet;
	private int weight;
	private ArrayList<Star> path = new ArrayList<>(); // (If calculated) The path needed to travel from the first to the second planet.
	private boolean directConnection; // A.K.A., can you go there directly or not.
	
	/* Makes a normal (direct) Connection. Also adds the first planet to the path. */
	public Connection(Star firstPlanet, Star secondPlanet, int weight) 
	{
		this.firstPlanet = firstPlanet;
		this.secondPlanet = secondPlanet;
		this.weight = weight;
		directConnection  = true;
		path.add(firstPlanet);
	}
	
	/* Makes a connection that isn't direct, used to find a path. Also adds the first planet to the path. */
	public Connection(Star firstPlanet, Star secondPlanet, int weight, boolean directConnection) 
	{
		this.firstPlanet = firstPlanet;
		this.secondPlanet = secondPlanet;
		this.weight = weight;
		this.directConnection = directConnection;
		path.add(firstPlanet);
	}
	
	public Star getFirstPlanet() 
	{
		return firstPlanet;
	}
	
	public void setFirstPlanet(Star planet) 
	{
		firstPlanet = planet;
	}
	
	public Star getSecondPlanet() 
	{
		return secondPlanet;
	}
	
	public void setSecondPlanet(Star planet) 
	{
		secondPlanet = planet;
	}
	
	public int getWeight() 
	{
		return weight;
	}
	
	public void setWeight(int weight) 
	{
		this.weight = weight;
	}
	
	public void setDirectConnection(boolean directConnection) 
	{
		this.directConnection = directConnection;
	}
	
	public boolean isDirectConnection() 
	{
		return directConnection;
	}
	
	public void addPlanetToPath(Star planetName) 
	{
		path.add(planetName);
	}
	
	public ArrayList<Star> getPath()
	{
		return path;
	}
	
	public boolean isConnection(Star firstPlanet, Star secondPlanet) 
	{
		if(this.firstPlanet.equals(firstPlanet) && this.secondPlanet.equals(secondPlanet)) 
		{
			return true;
		} 

		return false;
	}
}