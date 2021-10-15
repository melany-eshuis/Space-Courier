package com.space.SpaceCourier.model;

import java.util.ArrayList;
import java.util.Random;


public class InitializeData 
{
	public static GalaxyMap galaxyMap = new GalaxyMap();
	
	public InitializeData() 
	{
		int STAR_COUNT = 100;
		for(int i=0 ; i< STAR_COUNT ; i++) 
		{
			randomStar();
		}
		
		System.out.println("TESTING ARRAYLIST");
		System.out.println(galaxyMap.getAllStars().size());	
		
		galaxyMap.getRoute();
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

		Integer temp = random.nextInt(900);
		String name = temp.toString();
		
		checkStarOccurence(x, y, r, hex, name);
	}
	
	public void checkStarOccurence(int x, int y, int r, String hex, String name) 
	{
		ArrayList<Star> starList = galaxyMap.getAllStars();
		boolean exists = false;
		
		for(Star s: starList) 
		{
			if(s.getX() == x && s.getY() == y) 
			{
				exists = true;
				System.out.println("SAAAY WUUUUUUUUUT");
			} 
		}
		if(exists)
		{
			randomStar();
		} 
		else 
		{
			galaxyMap.addStar(new Star(x,y,r,hex,name));
		}
	}
}


