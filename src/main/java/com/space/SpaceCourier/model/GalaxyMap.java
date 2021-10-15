package com.space.SpaceCourier.model;

import java.util.ArrayList;

public class GalaxyMap 
{
	private ArrayList<Star> starList = new ArrayList<>();
	private ArrayList<Connection> connectionList = new ArrayList<>();
	Connection destinationConnection = null;
	private int radius = 5;
	
	/* Adds a star to starList, all stars will be added this way. */
	public void addStar(Star star) 
	{
		starList.add(star);
		//System.out.println("Added star: " + star.getName());
	}
	
	/* Returns a star from the starList based on their name, if it exists. Otherwise returns null */
	public Star getStar(String starName) 
	{
		for(Star s : starList) 
		{
			if(s.getName().equals(starName)) 
			{
				return s;
			}
		}
		
		return null;
	}
	
	/* Returns starList, aka all Stars */
	public ArrayList<Star> getAllStars()
	{
		return starList;
	}
	
	/* Sets setSingleConnection 2 ways */
	public void setConnection(String firstStarName, String secondStarName, int weight) 
	{
		setSingleConnection(firstStarName, secondStarName, weight);
		setSingleConnection(secondStarName, firstStarName, weight);
	}
	
	/* Sets a setSingleConnection based on the way count. If count is 2, it will call the method twice, both ways. */
	public void setConnection(String firstStarName, String secondStarName, int weight, int way) 
	{
		if(way == 1) 
		{
			setSingleConnection(firstStarName, secondStarName, weight);
		}
		else if(way == 2) 
		{
			setSingleConnection(firstStarName, secondStarName, weight);
			setSingleConnection(secondStarName, firstStarName, weight);
		}
		else 
		{
			System.out.println("Way: " + way + " is not a correct amount"); 
		} 
	}
	
	/* Sets the connection between 2 stars, a single way. The setConnection() method handles whether it has to be both ways or not. */ 
	public void setSingleConnection(String firstStarName, String secondStarName, int weight)
	{
		Star firstStar = getStar(firstStarName);
		Star secondStar = getStar(secondStarName);
		
		if(firstStar == null || secondStar == null) 
		{
			System.out.println("Either the star: " + firstStarName + " or the star: " + secondStarName + " doesn't exist.");
		}
		else 
		{
			Connection connection = getConnection(firstStar, secondStar);
			if(connection != null) 
			{
				System.out.println("Connection already exists, changing weight from " + connection.getWeight() + " to " + weight);
				connection.setWeight(weight);
			}
			else 
			{
				System.out.println("Addd connection " + firstStar.getName() + " to " + secondStar.getName() + " with weight " + weight);
				connectionList.add(new Connection(firstStar, secondStar, weight));
			}
		}
	}
	
	/* Using findPath, returns a connection with the full path between the first and second star.*/
	public Connection getShortestPath(String firstStarName, String secondStarName) 
	{
		resetStarPathData();
		Star startStar = getStar(firstStarName);
		Star destinationStar = getStar(secondStarName);
		if(getConnection(startStar, destinationStar) == null) 
		{
			destinationConnection = new Connection(startStar, destinationStar, 0, false);
		}
		else 
		{
			destinationConnection = getConnection(startStar, destinationStar);
		}
		
		startStar.setEstimation(0);
		startStar.setVisited(true);
		
		findPath(destinationConnection, startStar);
		return destinationConnection;
	}
	
	/* The loop that finds the full path*/
	private void findPath(Connection destinationConnection, Star currentStar) 
	{
		int shortestWeight = Integer.MAX_VALUE;
		Star shortestStar = null;
		
		for(Connection c : getConnections(currentStar)) 
		{
			if(!c.getSecondStar().getVisited()) 
			{
				if(c.getWeight() < shortestWeight) 
				{
					shortestWeight = c.getWeight();
					shortestStar = c.getSecondStar();
				}
			}
		}
		
		if(shortestStar != null) 
		{
			shortestStar.setEstimation(currentStar.getEstimation() + shortestWeight);
			shortestStar.setVisited(true);
			destinationConnection.addStarToPath(shortestStar);
			
			if(!destinationConnection.getSecondStar().equals(shortestStar)) 
			{
				findPath(destinationConnection, shortestStar);
			}
		} 
	}
	
	/* Just a simple reset function */
	private void resetStarPathData() 
	{
		for(Star s : starList) 
		{
			s.setEstimation(Integer.MAX_VALUE);
			s.setVisited(false);
		}
	}
	
	/* Get a specific Connection. */
	public Connection getConnection(Star firstStar, Star secondStar) 
	{
		for(Connection c : connectionList) 
		{
			if(c.isConnection(firstStar, secondStar)) 
			{
				return c;
			}
		}
		return null;
	}
	
	/* returns all  existing connections to this planet */
	public ArrayList<Connection> getConnections(Star star)
	{
		ArrayList<Connection> currentConnectionList = new ArrayList<>();
		
		for(Connection c : connectionList) 
		{
			if(c.getFirstStar().equals(star)) 
			{
				currentConnectionList.add(c);
			}
		}
		
		return currentConnectionList;
	}

	public int getDistance(Star starA, Star starB) 
	{
		int A = starA.getX() - starB.getX();
		int B = starA.getY() - starB.getY();
		int C = (int)Math.sqrt(A*A + B*B); 
		
//		System.out.println("A is: " +A);
//		System.out.println("B is: " +B);
//		System.out.println("C is: " +C);
//		System.out.println(starA.getX());
//		System.out.println(starA.getY());
//		System.out.println(starB.getX());
//		System.out.println(starB.getY());
		return C;
	}

	
	public ArrayList<Connection> getConnectionList() {
		return connectionList;
	}
		
	public void getRoute(){
		ArrayList<Star> listOfStars = new ArrayList<>();
		boolean notDone = true;

		
		for(Star s : starList) {
			
			listOfStars.add(new Star(s.getX(),s.getY(),s.getR(), s.getHex(), s.getName())); 
		}
		
		Star star0 = listOfStars.get(0);
		while(notDone) {
		
		
			for(Star x : listOfStars) {
				if ( getDistance(star0, x) <= radius) {
					connectionList.add(new Connection(star0, x, getDistance(star0, x)));
					
					//System.out.println("BINNEN DE 5 DISTANCE");
					//System.out.println(x.getX() + " " + x.getY());
				}
			}
			listOfStars.remove(0);
			if(listOfStars.isEmpty()) {
				notDone = false;
			} else {
				star0 = listOfStars.get(0);
			}
			
			
		}
		
		
		
	}



}


/* --Methode om pythagoras te berekenen (afstand tussen twee sterren)
 * --findRadius variabelen om andere sterren te vinden
 * nieuwe methode maken waar we alle afstanden berekenen binnen de findRadius
 * 		-copy vd sterrenlijst
 * 		-pakken eerste ster
 * 		-maken forloop die door alle andere sterren gaat
 * 		-berekenen de afstand tussen elke ster, als id findRadius zit, maak connection.
 * 		-Huidige ster uit de lijst te halen en process herhalen.
 * 
 */




































