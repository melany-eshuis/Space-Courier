package com.space.SpaceCourier.Model;

public class TempData
{
	//Temporary until we have a good way of doing this on the frontend.
	public static GalaxyMap galaxyMap = new GalaxyMap();
	
	public TempData() 
	{
		System.out.println("Adding Planets:");
		galaxyMap.addPlanet(new Planet("A"));
		galaxyMap.addPlanet(new Planet("B"));
		galaxyMap.addPlanet(new Planet("C"));
		galaxyMap.addPlanet(new Planet("D"));
		galaxyMap.addPlanet(new Planet("E"));
		galaxyMap.addPlanet(new Planet("F"));
		galaxyMap.addPlanet(new Planet("G"));
		galaxyMap.addPlanet(new Planet("H"));
		galaxyMap.addPlanet(new Planet("I"));
		galaxyMap.addPlanet(new Planet("J"));
		
		System.out.println("");
		System.out.println("Adding connections:");
		galaxyMap.setConnection("A", "B", 6);
		galaxyMap.setConnection("B", "C", 5);
		galaxyMap.setConnection("A", "D", 11);
		galaxyMap.setConnection("D", "E", 7);
		galaxyMap.setConnection("E", "F", 14);
		galaxyMap.setConnection("D", "F", 26);
		galaxyMap.setConnection("D", "G", 5);
		galaxyMap.setConnection("F", "I", 17);
		galaxyMap.setConnection("F", "H", 3);
		galaxyMap.setConnection("G", "H", 3);
		galaxyMap.setConnection("G", "J", 5);
		galaxyMap.setConnection("H", "I", 5);
		galaxyMap.setConnection("H", "J", 4);
		
		System.out.println("");
		
		
		
	}
}
