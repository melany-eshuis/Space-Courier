package com.space.SpaceCourier.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Planet 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long id;
	
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

