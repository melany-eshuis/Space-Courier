package com.space.SpaceCourier.Model;

import java.util.ArrayList;

public class Planet 
{
	private String name;
	private int estimation; // Used to calculate the path. 
	private boolean visited; // Used to calculate the path.
	
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
	
	public void setEstimation(int estimation) 
	{
		this.estimation = estimation;
	}
	
	public int getEstimation() 
	{
		return estimation;
	}
	
	public void setVisited(boolean visited) 
	{
		this.visited = visited;
	}
	
	public boolean getVisited() 
	{
		return visited;
	}
}

