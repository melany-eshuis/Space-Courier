package com.space.SpaceCourier.view;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.space.SpaceCourier.Model.Connection;
import com.space.SpaceCourier.Model.Planet;
import com.space.SpaceCourier.Model.TempData;

@RestController
public class SpaceCourierEndpoint 
{
	@CrossOrigin(origins = "*", allowedHeaders ="*")
	@GetMapping("getpath/{first}/{second}")
	public String getPathMapping(@PathVariable String first, @PathVariable String second) 
	{
		System.out.println("First: " + first + " Second: " + second);
		
		String firstName = first.toUpperCase();   // This is just for now. Later on we can remove the upper case and just use the variables directly.
		String secondName = second.toUpperCase(); // This is just for now. Later on we can remove the upper case and just use the variables directly.
		
		Connection path = TempData.galaxyMap.getShortestPath(firstName, secondName);
		
		String response = "The shortest path from " + firstName + " to " + secondName + " is:";
		
		for(Planet p : path.getPath()) 
		{
			response += " " + p.getName();
		}
		response += ".";
		return response;
	}
}
