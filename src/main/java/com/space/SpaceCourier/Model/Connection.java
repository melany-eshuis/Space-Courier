package com.space.SpaceCourier.Model;

import java.util.ArrayList;

public class Connection 
{
	private Planet firstPlanet;
	private Planet secondPlanet;
	private int weight;
	private ArrayList<Planet> path = new ArrayList<>(); // (If calculated) The path needed to travel from the first to the second planet.
	private boolean directConnection; // A.K.A., can you go there directly or not.
	
	/* Makes a normal (direct) Connection. Also adds the first planet to the path. */
	public Connection(Planet firstPlanet, Planet secondPlanet, int weight) 
	{
		this.firstPlanet = firstPlanet;
		this.secondPlanet = secondPlanet;
		this.weight = weight;
		directConnection  = true;
		path.add(firstPlanet);
	}
	
	/* Makes a connection that isn't direct, used to find a path. Also adds the first planet to the path. */
	public Connection(Planet firstPlanet, Planet secondPlanet, int weight, boolean directConnection) 
	{
		this.firstPlanet = firstPlanet;
		this.secondPlanet = secondPlanet;
		this.weight = weight;
		this.directConnection = directConnection;
		path.add(firstPlanet);
	}
	
	public Planet getFirstPlanet() 
	{
		return firstPlanet;
	}
	
	public void setFirstPlanet(Planet planet) 
	{
		firstPlanet = planet;
	}
	
	public Planet getSecondPlanet() 
	{
		return secondPlanet;
	}
	
	public void setSecondPlanet(Planet planet) 
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
	
	public void addPlanetToPath(Planet planetName) 
	{
		path.add(planetName);
	}
	
	public ArrayList<Planet> getPath()
	{
		return path;
	}
	
	public boolean isConnection(Planet firstPlanet, Planet secondPlanet) 
	{
		if(this.firstPlanet.getName().equals(firstPlanet) && this.secondPlanet.getName().equals(secondPlanet)) 
		{
			return true;
		} 

		return false;
	}
}