package com.space.SpaceCourier.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.space.SpaceCourier.Model.Planet;

@Service
@Transactional
public class PlanetService 
{
	
	@Autowired
	private PlanetRepository planetRepository;
	
	public Iterable<Planet> getAllPlanets() 
	{
		return planetRepository.findAll();
	}
	
	public Planet save(Planet planet) 
	{
		return planetRepository.save(planet);
	}
}
