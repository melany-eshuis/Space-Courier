package com.space.SpaceCourier.Model;

import java.util.ArrayList;

public class Planet // Didn't include the toString()
{
	private String name;
	private ArrayList<Planet> connectedPlanets = new ArrayList<>();
	private ArrayList<Integer> connectedPlanetWeights = new ArrayList<>();
	private Planet previousPlanetOnShortestRoute = null;
	private int weightShortestPath = Integer.MAX_VALUE;
	private boolean visited = false;
	
	public Planet(String name) 
	{
		this.name = name;
	}
	
	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public boolean getVisited() 
	{
		return visited;
	}
	
	public void setVisited(boolean visited) 
	{
		this.visited = visited;
	}
	
	public int getWeightShortestPath() 
	{
		return weightShortestPath;
	}
	
	public void setWeightShortestPath(int weight)
	{
		weightShortestPath = weight;
	}
	
	public Planet getPreviousPlanetOnShortestRoute() 
	{
		return previousPlanetOnShortestRoute;
	}
	
	public void setPreviousPlanetOnShortestRoute(Planet planet) 
	{
		previousPlanetOnShortestRoute = planet;
	}
	
	public ArrayList<Planet> getConnectedPlanets()
	{
		return connectedPlanets;
	}
	
	public ArrayList<Integer> getConnectedPlanetWeights()
	{
		return connectedPlanetWeights;
	}
	
	public void resetShortestPath() 
	{
		previousPlanetOnShortestRoute = null;
		weightShortestPath = Integer.MAX_VALUE;
		visited = false;
	}
	
	public void setConnection(Planet planet, int weight) 
	{
		if(!connectedPlanets.contains(planet)) 
		{
			connectedPlanets.add(planet);
			connectedPlanetWeights.add(weight);
		}
		else 
		{
			System.out.println("This connection already consists: " + name + " > " + planet.name);
		}
	}
	
	public String printPath(String path) 
	{
		System.out.println("Path is: " + path);
		System.out.println("");
		if (!path.equals("")) 
		{
			path = (name + " > " + path);
		}
		else 
		{
			path = name;
		}
		
		if(previousPlanetOnShortestRoute != null)
		{
			previousPlanetOnShortestRoute.printPath(path);
		}
		else
		{
			System.out.println("!Path is: " + path);
			return path;
		}
		System.out.println("I got here."); //gets called 4 times
		return "I didn't give the right info";
	}
}

