package com.space.SpaceCourier.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Star 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long id;
	
	private String name;
	private int estimation; // Used to calculate the path. 
	private boolean visited; // Used to calculate the path.
	private int x;
	private int y;
	private int r;
	private String hex;
	
	public Star(int x, int y, int r, String hex, String name) {
		this.x = x;
		this.y = y;
		this.r = r;
		this.hex = hex;
		this.name = name;
	}
	
	
	public int getX() 
	{
		return x;
	}
	
	public void setX(int x) 
	{
		this.x = x;
	}
	
	public int getY() 
	{
		return y;
	}
	
	public void setY(int y) 
	{
		this.y = y;
	}
	
	public int getR() 
	{
		return r;
	}
	
	public void setR(int r) 
	{
		this.r = r;
	}
	
	public String getHex() 
	{
		return hex;
	}
	
	public void setHex(String hex) 
	{
		this.hex = hex;
	}
	
	public Star(String name) 
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

