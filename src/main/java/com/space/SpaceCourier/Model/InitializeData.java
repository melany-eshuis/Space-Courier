package com.space.SpaceCourier.Model;

import java.util.ArrayList;
import java.util.Random;


public class InitializeData 
{
	public static GalaxyMap galaxyMap = new GalaxyMap();
//	
//	public InitializeData() 
//	{
//		System.out.println("Adding Planets:");
//		galaxyMap.addPlanet(new Planet("A"));
//		galaxyMap.addPlanet(new Planet("B"));
//		galaxyMap.addPlanet(new Planet("C"));
//		galaxyMap.addPlanet(new Planet("D"));
//		galaxyMap.addPlanet(new Planet("E"));
//		galaxyMap.addPlanet(new Planet("F"));
//		galaxyMap.addPlanet(new Planet("G"));
//		galaxyMap.addPlanet(new Planet("H"));
//		galaxyMap.addPlanet(new Planet("I"));
//		galaxyMap.addPlanet(new Planet("J"));
//		System.out.println("");
//
//		System.out.println("Adding connections:");
//		galaxyMap.setConnection("A", "B", 6);
//		galaxyMap.setConnection("B", "C", 5);
//		galaxyMap.setConnection("A", "D", 11);
//		galaxyMap.setConnection("D", "E", 7);
//		galaxyMap.setConnection("E", "F", 14);
//		galaxyMap.setConnection("D", "F", 26);
//		galaxyMap.setConnection("D", "G", 5);
//		galaxyMap.setConnection("F", "I", 17);
//		galaxyMap.setConnection("F", "H", 3);
//		galaxyMap.setConnection("G", "H", 3);
//		galaxyMap.setConnection("G", "J", 5);
//		galaxyMap.setConnection("H", "I", 5);
//		galaxyMap.setConnection("H", "J", 4);
//		System.out.println("");
//	}
	
	public InitializeData() {
		int STAR_COUNT = 200;
		for(int i=0 ; i< STAR_COUNT ; i++) {
			randomStar();
		}
		
		
		System.out.println("TESTING ARRAYLIST");
		System.out.println(galaxyMap.getAllStars().size());
		
		
	}
	
	public void randomStar() {
		Random random = new Random();
		//1600 posities
		int x = random.nextInt(48);
		
		//900 posities
		int y = random.nextInt(27);
		int r = random.nextInt(10);
		int color1 = random.nextInt(16);
		int color2 = random.nextInt(16);
		int color3 = random.nextInt(16);
		int color4 = random.nextInt(16);
		int color5 = random.nextInt(16);
		int color6 = random.nextInt(16);
		
		String hex = "#" + Integer.toHexString(color1);
		hex += Integer.toHexString(color2);
		hex += Integer.toHexString(color3);
		hex += Integer.toHexString(color4);
		hex += Integer.toHexString(color5);
		hex += Integer.toHexString(color6);
		
		
//		System.out.println(x);
//		System.out.println(y);
//		System.out.println(r);
//		System.out.println(hex);
		Integer temp = random.nextInt(900);
		String name = temp.toString();
		
		checkStarOccurence(x, y, r, hex, name);
		//galaxyMap.addStar(new Star(x,y,r,hex,name));
	}
	
	public void checkStarOccurence(int x, int y, int r, String hex, String name) {
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
		} else 
		{
			galaxyMap.addStar(new Star(x,y,r,hex,name));
		}
	}

}


