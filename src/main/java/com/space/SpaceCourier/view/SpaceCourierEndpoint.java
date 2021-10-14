package com.space.SpaceCourier.view;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.space.SpaceCourier.model.Connection;
import com.space.SpaceCourier.model.GalaxyMap;
import com.space.SpaceCourier.model.InitializeData;
import com.space.SpaceCourier.model.Star;
import com.space.SpaceCourier.persistence.StarService;

@RestController
public class SpaceCourierEndpoint 
{
	@Autowired
	private StarService starService = new StarService(); 

	
	@CrossOrigin(origins = "*", allowedHeaders ="*")
	@GetMapping("getpath/{first}/{second}")
	public String getPathMapping(@PathVariable String first, @PathVariable String second) 
	{
		System.out.println("First: " + first + " Second: " + second);
		
		String response = "";
		
		if(InitializeData.galaxyMap.getStar(first) != null && InitializeData.galaxyMap.getStar(second) != null) 
		{
			Connection path = InitializeData.galaxyMap.getShortestPath(first, second);
			response = "The shortest path from " + first + " to " + second + " is:";
			
			
			for(Star s : path.getPath()) 
			{
				response += " " + s.getName();
			}
			response += ".";
		} 
		else 
		{
			response = "You didn't type the star names correctly.";
		}
		
		return response;
	}
	
	/* Currently the way to load in the dummy data. */
	@CrossOrigin(origins = "*", allowedHeaders ="*")
	@GetMapping("initialize")
	public void initializeData() 
	{
		System.out.println("Initialize method");
		for(Star s : InitializeData.galaxyMap.getAllStars())
		{
			System.out.println("Saving " + s.getName());
			starService.save(s);
		}
	}
	@CrossOrigin(origins = "*", allowedHeaders ="*")
	@GetMapping("courierservice")
	public ArrayList<Star> courierservice()
	{
		System.out.println("HOI MELANY");

		return InitializeData.galaxyMap.getAllStars();
	}
	
}
