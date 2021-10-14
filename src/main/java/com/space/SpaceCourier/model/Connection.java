package com.space.SpaceCourier.model;

import java.util.ArrayList;

public class Connection 
{
	private Star firstStar;
	private Star secondStar;
	private int weight;
	private ArrayList<Star> path = new ArrayList<>(); // (If calculated) The path needed to travel from the first to the second star.
	private boolean directConnection; // A.K.A., can you go there directly or not.
	
	/* Makes a normal (direct) Connection. Also adds the first star to the path. */
	public Connection(Star firstStar, Star secondStar, int weight) 
	{
		this.firstStar = firstStar;
		this.secondStar = secondStar;
		this.weight = weight;
		directConnection  = true;
		path.add(firstStar);
	}
	
	/* Makes a connection that isn't direct, used to find a path. Also adds the first star to the path. */
	public Connection(Star firstStar, Star secondStar, int weight, boolean directConnection) 
	{
		this.firstStar = firstStar;
		this.secondStar = secondStar;
		this.weight = weight;
		this.directConnection = directConnection;
		path.add(firstStar);
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