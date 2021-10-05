package com.space.SpaceCourier.Model;

import java.util.ArrayList;

public class GalaxyMap // Here also didn't add the toString()
{
	private ArrayList<Planet> planets = new ArrayList<>();
	
	public void addPlanet(Planet planet) 
	{
		if(findPlanet(planet.getName()) == null) 
		{
			planets.add(planet);
		}
		else
		{
			System.out.println("This planet already exsists in the galaxy");
		}
	}
	
	public Planet findPlanet(String name) 
	{
		for(Planet p : planets) 
		{
			if(p.getName().equals(name)) 
			{
				return p;
			}
		}
		return null;
	}
	
	public void setConnectionOneWay(String planetAName, String planetBName, int weightAtoB) 
	{
		Planet planetA = findPlanet(planetAName);
		Planet planetB = findPlanet(planetBName);
		planetA.setConnection(planetB, weightAtoB);
	}
	
	public void setConnectionBothWays(String planetAName, String planetBName, int weightAtoB, int weightBtoA) 
	{
		Planet planetA = findPlanet(planetAName);
		Planet planetB = findPlanet(planetBName);
		planetA.setConnection(planetB, weightAtoB);
		planetB.setConnection(planetA, weightBtoA);
	}
	
	public void setConnectionBothWays(String planetAName, String planetBName, int weight) 
	{
		setConnectionBothWays(planetAName, planetBName, weight, weight);
	}
	
	// Dijkstra
	
	public void getShortestPath(String from, String to) 
	{
		Planet destination = findPlanet(to);
		Planet currentPlanet = findPlanet(from);	
		currentPlanet.setVisited(true);
		currentPlanet.setWeightShortestPath(0);
		
		if(destination != null && currentPlanet != null) 
		{
			while (currentPlanet != destination) 
			{
				handleCurrentPlanet(currentPlanet);
				currentPlanet = determineNextPlanetToCheck(currentPlanet);
				currentPlanet.setVisited(true);
			}
			
			String path = currentPlanet.printPath(""); // !!
			System.out.println("The shortest path = " + path);
		}
		else
		{
			System.out.println("The input was not valid");
		}
		resetShortestPath();
		
	}
	
	public void handleCurrentPlanet(Planet currentPlanet) 
	{
		ArrayList<Planet> connectedPlanets = currentPlanet.getConnectedPlanets();
		ArrayList<Integer> connectedPlanetWeights = currentPlanet.getConnectedPlanetWeights();
		
		for(int i = 0; i < connectedPlanets.size(); i++) 
		{
			if(currentPlanet.getWeightShortestPath() + connectedPlanetWeights.get(i) < connectedPlanets.get(i).getWeightShortestPath()) 
			{
				connectedPlanets.get(i).setWeightShortestPath(currentPlanet.getWeightShortestPath() + connectedPlanetWeights.get(i));
				connectedPlanets.get(i).setPreviousPlanetOnShortestRoute(currentPlanet);
			}
		}
	}
	
	public Planet determineNextPlanetToCheck(Planet currentPlanet) 
	{
		int minWeight = Integer.MAX_VALUE;
		for(Planet planet : planets) 
		{
			if(!planet.getVisited() && planet.getWeightShortestPath() < minWeight) 
			{
				currentPlanet = planet;
			}
		}
		return currentPlanet;
	}
	
	public void printWeightOfEachtPlanet(ArrayList<Planet> planetList) 
	{
		for(Planet planet : planetList) 
		{
			System.out.print("The weight of " + planet.getName() + " = " + planet.getWeightShortestPath() + " previous planet = ");
			if(planet.getPreviousPlanetOnShortestRoute()!=null) 
			{
				System.out.println(planet.getPreviousPlanetOnShortestRoute().getName());
			}
			else 
			{
				System.out.println("null");
			}
		}
	}
	
	public void resetShortestPath() 
	{
		for(Planet planet : planets) 
		{
			planet.resetShortestPath();
		}
	}
}
