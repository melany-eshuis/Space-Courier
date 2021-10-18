package com.space.SpaceCourier.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Star 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long id;
	private String name;
	private int x;
	private int y;
	private int r;
	private String hex;
	
	@Transient
	private int estimation; // Used to calculate the path. 
	@Transient
	private boolean visited; // Used to calculate the path.
	@Transient
	private Star previousStar;
	
	public Star(int x, int y, int r, String hex, String name) 
	{
		this.x = x;
		this.y = y;
		this.r = r;
		this.hex = hex;
		this.name = name;
	}
	
	public Star(String name) 
	{
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
		System.out.println("Estimation of " + name + " changed from " + this.estimation + " to " + estimation);
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
	
	public Star getPreviousStar() 
	{
		return previousStar;
	}

	public void setPreviousStar(Star previousStar) 
	{
		this.previousStar = previousStar;
	}
}

