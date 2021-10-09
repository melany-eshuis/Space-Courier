package com.space.SpaceCourier.Model;

import java.util.ArrayList;

public class GalaxyMap 
{
	private ArrayList<Planet> planetList = new ArrayList<>();
	private ArrayList<Connection> connectionList = new ArrayList<>();
	Connection destinationConnection = null;
	
	public GalaxyMap() 
	{
		
	}
	
	/* Adds a planet to planetList, all planets will be added this way. */
	public void addPlanet(Planet planet) 
	{
		planetList.add(planet);
		System.out.println("Added planet: " + planet.getName());
	}
	
	/* Returns a planet from the planetList based on their name, if it exists. Otherwise returns null */
	public Planet getPlanet(String planetName) 
	{
		for(Planet p : planetList) 
		{
			if(p.getName().equals(planetName)) 
			{
				return p;
			}
		}
		
		return null;
	}
	
	/* Sets setSingleConnection 2 ways */
	public void setConnection(String firstPlanetName, String secondPlanetName, int weight) 
	{
		setSingleConnection(firstPlanetName, secondPlanetName, weight);
		setSingleConnection(secondPlanetName, firstPlanetName, weight);
	}
	
	/* Sets a setSingleConnection based on the way count. If count is 2, it will call the method twice, both ways. */
	public void setConnection(String firstPlanetName, String secondPlanetName, int weight, int way) 
	{
		if(way == 1) 
		{
			setSingleConnection(firstPlanetName, secondPlanetName, weight);
		}
		else if(way == 2) 
		{
			setSingleConnection(firstPlanetName, secondPlanetName, weight);
			setSingleConnection(secondPlanetName, firstPlanetName, weight);
		}
		else 
		{
			System.out.println("Way: " + way + " is not a correct amount"); 
		} 
	}
	
	/* Sets the connection between 2 planets, a single way. The setConnection() method handles whether it has to be both ways or not. */ 
	public void setSingleConnection(String firstPlanetName, String secondPlanetName, int weight)
	{
		Planet firstPlanet = getPlanet(firstPlanetName);
		Planet secondPlanet = getPlanet(secondPlanetName);
		
		if(firstPlanet == null || secondPlanet == null) 
		{
			System.out.println("Either the planet: " + firstPlanetName + " or the planet: " + secondPlanetName + " doesn't exist.");
		}
		else 
		{
			Connection connection = getConnection(firstPlanet, secondPlanet);
			if(connection != null) 
			{
				System.out.println("Connection already exists, changing weight from " + connection.getWeight() + " to " + weight);
				connection.setWeight(weight);
			}
			else 
			{
				System.out.println("Addd connection " + firstPlanet.getName() + " to " + secondPlanet.getName() + " with weight " + weight);
				connectionList.add(new Connection(firstPlanet, secondPlanet, weight));
			}
		}
	}
	
	/* Using findPath, returns a connection with the full path between the first and second planet.*/
	public Connection getShortestPath(String firstPlanetName, String secondPlanetName) 
	{
		resetPlanetPathData();
		Planet startPlanet = getPlanet(firstPlanetName);
		Planet destinationPlanet = getPlanet(secondPlanetName);
		if(getConnection(startPlanet, destinationPlanet) == null) 
		{
			destinationConnection = new Connection(startPlanet, destinationPlanet, 0, false);
		}
		else 
		{
			destinationConnection = getConnection(startPlanet, destinationPlanet);
		}
		
		startPlanet.setEstimation(0);
		startPlanet.setVisited(true);
		
		findPath(destinationConnection, startPlanet);
		return destinationConnection;
	}
	
	/* The loop that finds the full path*/
	private void findPath(Connection destinationConnection, Planet currentPlanet) 
	{
		int shortestWeight = Integer.MAX_VALUE;
		Planet shortestPlanet = null;
		
		for(Connection c : getConnections(currentPlanet)) 
		{
			if(!c.getSecondPlanet().getVisited()) 
			{
				if(c.getWeight() < shortestWeight) 
				{
					shortestWeight = c.getWeight();
					shortestPlanet = c.getSecondPlanet();
				}
			}
		}
		
		if(shortestPlanet != null) 
		{
			shortestPlanet.setEstimation(currentPlanet.getEstimation() + shortestWeight);
			shortestPlanet.setVisited(true);
			destinationConnection.addPlanetToPath(shortestPlanet);
			
			if(!destinationConnection.getSecondPlanet().equals(shortestPlanet)) 
			{
				findPath(destinationConnection, shortestPlanet);
			}
		} 
	}
	
	/* Just a simple reset function */
	private void resetPlanetPathData() 
	{
		for(Planet p : planetList) 
		{
			p.setEstimation(Integer.MAX_VALUE);
			p.setVisited(false);
		}
	}
	
	/* Get a specific Connection. */
	public Connection getConnection(Planet firstPlanet, Planet secondPlanet) 
	{
		for(Connection c : connectionList) 
		{
			if(c.isConnection(firstPlanet, secondPlanet)) 
			{
				return c;
			}
		}
		return null;
	}
	
	/* returns all  existing connections to this planet */
	public ArrayList<Connection> getConnections(Planet planet)
	{
		ArrayList<Connection> currentConnectionList = new ArrayList<>();
		
		for(Connection c : connectionList) 
		{
			if(c.getFirstPlanet().equals(planet)) 
			{
				currentConnectionList.add(c);
			}
		}
		
		return currentConnectionList;
	}
}
