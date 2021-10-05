package com.space.SpaceCourier.Model;

public class TempData 
{
	// Temp initialization till we get the fullstack/frontent.
	private GalaxyMap galaxyMap = new GalaxyMap();
	
	public TempData() 
	{
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
		
		galaxyMap.setConnectionBothWays("A", "B", 6);
		galaxyMap.setConnectionBothWays("B", "C", 5);
		galaxyMap.setConnectionBothWays("A", "D", 11);
		galaxyMap.setConnectionBothWays("D", "E", 7);
		galaxyMap.setConnectionBothWays("E", "F", 14);
		galaxyMap.setConnectionBothWays("D", "F", 26);
		galaxyMap.setConnectionBothWays("D", "G", 5);
		galaxyMap.setConnectionBothWays("F", "I", 17);
		galaxyMap.setConnectionBothWays("F", "H", 3);
		galaxyMap.setConnectionBothWays("G", "H", 3);
		galaxyMap.setConnectionBothWays("G", "J", 5);
		galaxyMap.setConnectionBothWays("H", "I", 5);
		galaxyMap.setConnectionBothWays("H", "J", 4);
		
		galaxyMap.getShortestPath("C", "G");
	}
}
