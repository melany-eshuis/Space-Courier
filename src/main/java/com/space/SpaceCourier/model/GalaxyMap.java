package com.space.SpaceCourier.model;

import java.util.ArrayList;
import java.util.Random;

public class GalaxyMap 
{
	private ArrayList<Star> starList = new ArrayList<>();
	private ArrayList<Connection> connectionList = new ArrayList<>();
	Connection destinationConnection = null;
	private ArrayList<Star> path = new ArrayList<>();
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
		System.out.println("Star " + starName + " doesn't exist.");
		System.out.println("Existsing stars are: ");
		
		for(Star s : starList) 
		{
			System.out.println(s.getName());
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
				System.out.println("Connection already exists, changing weight from " + connection.getDistance() + " to " + weight);
				connection.setDistance(weight);
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
		System.out.println("--- getShortestPath Start ---");
		
		resetStarPathData();
		Star startStar = getStar(firstStarName);
		Star destinationStar = getStar(secondStarName);
		
		if(getConnection(startStar, destinationStar) == null) 
		{
			destinationConnection = new Connection(startStar, destinationStar, 0, false);
			System.out.println("It's not connected yet.");
		}
		else 
		{
			destinationConnection = getConnection(startStar, destinationStar);
			System.out.println("It's connected.");
		}
		
		startStar.setEstimation(0);
		startStar.setVisited(true);
		
		findPath(destinationConnection, startStar);
		
		System.out.println("--- setPath start ---");
		setPath(destinationConnection, destinationStar);
		System.out.println("--- setPath end ---");
		
		path.add(0, startStar);
		
		for(Star p: path) 
		{
			destinationConnection.addStarToPath(p);
		}
		
		System.out.println("--- getShortestPath end ---");
		return destinationConnection;
	}
	
	private void setPath(Connection connection, Star Star) 
	{
		Star startStar = connection.getFirstStar();
		Star currentStar = Star;
		path.add(0, currentStar);
		
		System.out.println("Current Star: " + currentStar.getName());
		if(!currentStar.getPreviousStar().equals(startStar)) 
		{
			setPath(connection, currentStar.getPreviousStar());
		}
	}
	
	/* The loop that finds the full path*/
	private void findPath(Connection connection, Star currentStar) 
	{
		System.out.println("");
		System.out.println("---- Find Path Start --- ");
		int shortest = Integer.MAX_VALUE;
		Star shortestStar = null;
		
		System.out.println("getConnections: " + getConnections(currentStar).size());
		for (Connection c : getConnections(currentStar)) 
		{
			String firstStarName = "";
			String secondStarName = "";
			if(currentStar.getName().equals(c.getFirstStar().getName())) 
			{
				firstStarName = c.getFirstStar().getName();
				secondStarName = c.getSecondStar().getName();
			}
			else if(currentStar.getName().equals(c.getSecondStar().getName())) 
			{
				firstStarName = c.getSecondStar().getName();
				secondStarName = c.getFirstStar().getName();
			}
			
			System.out.println(firstStarName + " is connected with " + secondStarName);
			int totalEstimation = getStar(firstStarName).getEstimation() + c.getDistance();
	
			
			
			if(c.getDistance() < shortest && !getStar(secondStarName).getVisited()) 
			{
				System.out.println(secondStarName + " is the new shortest distance.");
				shortest = c.getDistance();
				shortestStar = getStar(secondStarName);
			}
			
			System.out.println("Total estimation is " + totalEstimation + " second star estimation is " + getStar(secondStarName).getEstimation());
			System.out.println("From the list estimation is: " + getStar(secondStarName).getEstimation());
			
			if(totalEstimation < getStar(secondStarName).getEstimation()) 
			{
				getStar(secondStarName).setEstimation(totalEstimation);
				getStar(secondStarName).setPreviousStar(currentStar);
				System.out.println("!!The previous star of " + secondStarName + " is " + currentStar.getName());
			}
		}
		
		
		boolean go = false;
		for(Star s : starList) 
		{
			if(!s.getVisited()) 
			{
				go = true;
			}
		}
		
		System.out.println("Go is " + go);
		
		if(shortestStar != null) 
		{
			shortestStar.setVisited(true);
			System.out.println(shortestStar.getName() + " is visited.");
			findPath(connection, shortestStar);
		}
		else
		{
			if(go && currentStar.getPreviousStar() != null) 
			{
				System.out.println("We're in go, previsous star is: " + currentStar.getPreviousStar().getName());
				findPath(connection, currentStar.getPreviousStar());
			}
			
		}
		System.out.println("--- Find Path End ---");
	}
	
	/* Just a simple reset function */
	private void resetStarPathData() 
	{
		path.clear();
		for(Star s : starList) 
		{
			s.setEstimation(Integer.MAX_VALUE);
			s.setVisited(false);
			s.setPreviousStar(null);
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
		System.out.println("--- getConnections start ---");
		ArrayList<Connection> currentConnectionList = new ArrayList<>();
		
		System.out.println("Checking star: " + star.getName());
		
		for(Connection c : connectionList) 
		{
			if(c.getFirstStar().getName().equals(star.getName()) || c.getSecondStar().getName().equals(star.getName())) 
			{
				currentConnectionList.add(c);
			}
		}
		
		System.out.println("--- getConnections end ---");
		return currentConnectionList;
	}

	public int getDistance(Star starA, Star starB) 
	{
		int A = starA.getX() - starB.getX();
		int B = starA.getY() - starB.getY();
		int C = (int)Math.sqrt(A*A + B*B); 
		return C;
	}

	
	public ArrayList<Connection> getConnectionList() 
	{
		return connectionList;
	}
		
	public void getRoute()
	{
		ArrayList<Star> listOfStars = new ArrayList<>();
		boolean notDone = true;
		
		for(Star s : starList) 
		{
			listOfStars.add(new Star(s.getX(),s.getY(),s.getR(), s.getHex(), s.getName())); 
		}
		
		Star star0 = listOfStars.get(0);
		while(notDone) 
		{
			listOfStars.remove(0);
			for(Star x : listOfStars) 
			{
				if ( getDistance(star0, x) <= radius) 
				{
					connectionList.add(new Connection(star0, x, getDistance(star0, x)));
					
				}
			}
			
			if(listOfStars.isEmpty()) {
				notDone = false;
			} else {
				star0 = listOfStars.get(0);
			}
		}
	}
	
	public void initialize() 
	{
		getAllStars().clear();
		getConnectionList().clear();

		int STAR_COUNT = 100;
		for(int i=0 ; i< STAR_COUNT ; i++) 
		{
			randomStar();
		}
		
		System.out.println(getAllStars().size());	
		
		getRoute();
	}

	public void randomStar() 
	{
		Random random = new Random();
		
		int x = random.nextInt(47) + 1;
		int y = random.nextInt(26) + 1;
		int r = random.nextInt(10) + 5;
		int color1 = random.nextInt(14)+2;
		int color2 = random.nextInt(14)+2;
		int color3 = random.nextInt(14)+2;
		int color4 = random.nextInt(14)+2;
		int color5 = random.nextInt(14)+2;
		int color6 = random.nextInt(14)+2;
		
		String hex = "#" + Integer.toHexString(color1);
		hex += Integer.toHexString(color2);
		hex += Integer.toHexString(color3);
		hex += Integer.toHexString(color4);
		hex += Integer.toHexString(color5);
		hex += Integer.toHexString(color6);

		Integer temp = random.nextInt(101);
		String name = temp.toString();
		
		checkStarOccurence(x, y, r, hex, name);
	}
	
	public void checkStarOccurence(int x, int y, int r, String hex, String name) 
	{
		ArrayList<Star> starList = getAllStars();
		boolean exists = false;
		
		for(Star s: starList) 
		{
			if(s.getX() == x && s.getY() == y) 
			{
				exists = true;
				System.out.println("SAAAY WUUUUUUUUUT");
			} 
			
			if(s.getName().equals(name)) 
			{
				exists = true;
			}
		}
		if(exists)
		{
			randomStar();
		} 
		else 
		{
			addStar(new Star(x,y,r,hex,name));
		}
	}
}