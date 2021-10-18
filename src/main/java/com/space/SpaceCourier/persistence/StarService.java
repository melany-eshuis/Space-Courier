package com.space.SpaceCourier.persistence;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.space.SpaceCourier.model.Star;

@Service
@Transactional
public class StarService 
{
	
	@Autowired
	private StarRepository starRepository;
	
	public Iterable<Star> getAllStars() 
	{
		return starRepository.findAll();
	}
	
	public Star save(Star star) 
	{
		return starRepository.save(star);
	}
	
	public Star getStar() 
	{
		System.out.println("Yes!");
		return starRepository.findById((long) 1).get();
	}
}
