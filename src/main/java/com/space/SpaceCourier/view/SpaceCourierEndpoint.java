package com.space.SpaceCourier.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.space.SpaceCourier.Model.Connection;
import com.space.SpaceCourier.Model.InitializeData;
import com.space.SpaceCourier.Model.Star;
import com.space.SpaceCourier.persistence.PlanetService;

@RestController
public class SpaceCourierEndpoint 
{
	@Autowired
	private PlanetService planetService = new PlanetService(); 

	
	@CrossOrigin(origins = "*", allowedHeaders ="*")
	@GetMapping("getpath/{first}/{second}")
	public String getPathMapping(@PathVariable String first, @PathVariable String second) 
	{
		System.out.println("First: " + first + " Second: " + second);
		
		String firstName = first.toUpperCase();   // This is just for now. Later on we can remove the upper case and just use the variables directly.
		String secondName = second.toUpperCase(); // This is just for now. Later on we can remove the upper case and just use the variables directly.
		String response = "";
		
		if(InitializeData.galaxyMap.getStar(firstName) != null && InitializeData.galaxyMap.getStar(secondName) != null) 
		{
			Connection path = InitializeData.galaxyMap.getShortestPath(firstName, secondName);
			response = "The shortest path from " + firstName + " to " + secondName + " is:";
			
			
			for(Star p : path.getPath()) 
			{
				response += " " + p.getName();
			}
			response += ".";
		} 
		else 
		{
			response = "You didn't type the planet names correctly.";
		}
		
		return response;
	}
	
	/* Currently the way to load in the dummy data. */
	@CrossOrigin(origins = "*", allowedHeaders ="*")
	@GetMapping("initialize")
	public void initializeData() 
	{
		System.out.println("Initialize method");
		for(Star p : InitializeData.galaxyMap.getAllStars())
		{
			System.out.println("Saving " + p.getName());
			planetService.save(p);
		}
	}
}
