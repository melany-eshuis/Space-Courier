package com.space.SpaceCourier.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.space.SpaceCourier.Model.Star;

@Service
@Transactional
public class PlanetService 
{
	
	@Autowired
	private PlanetRepository planetRepository;
	
	public Iterable<Star> getAllPlanets() 
	{
		return planetRepository.findAll();
	}
	
	public Star save(Star planet) 
	{
		return planetRepository.save(planet);
	}
}
