package com.space.SpaceCourier.model;

import java.util.ArrayList;

public class Connection 
{
	private Star firstStar;
	private Star secondStar;
	private int distance;
	private ArrayList<Star> path = new ArrayList<>(); // (If calculated) The path needed to travel from the first to the second star.
	private boolean directConnection; // A.K.A., can you go there directly or not.
	
	/* Makes a normal (direct) Connection. Also adds the first star to the path. */
	public Connection(Star firstStar, Star secondStar, int distance) 
	{
		this.firstStar = firstStar;
		this.secondStar = secondStar;
		this.distance = distance;
		directConnection  = true;
	}
	
	/* Makes a connection that isn't direct, used to find a path. Also adds the first star to the path. */
	public Connection(Star firstStar, Star secondStar, int distance, boolean directConnection) 
	{
		this.firstStar = firstStar;
		this.secondStar = secondStar;
		this.distance = distance;
		this.directConnection = directConnection;
	}
	
	public Star getFirstStar() 
	{
		return firstStar;
	}
	
	public void setFirstStar(Star star) 
	{
		firstStar = star;
	}
	
	public Star getSecondStar() 
	{
		return secondStar;
	}
	
	public void setSecondStar(Star star) 
	{
		secondStar = star;
	}
	
	public int getDistance() 
	{
		return distance;
	}
	
	public void setDistance(int distance) 
	{
		this.distance = distance;
	}
	
	public void setDirectConnection(boolean directConnection) 
	{
		this.directConnection = directConnection;
	}
	
	public boolean isDirectConnection() 
	{
		return directConnection;
	}
	
	public void addStarToPath(Star starName) 
	{
		path.add(starName);
	}
	
	public ArrayList<Star> getPath()
	{
		return path;
	}
	
	public boolean isConnection(Star firstStar, Star secondStar) 
	{
		if(this.firstStar.equals(firstStar) && this.secondStar.equals(secondStar)) 
		{
			return true;
		} 

		return false;
	}
}