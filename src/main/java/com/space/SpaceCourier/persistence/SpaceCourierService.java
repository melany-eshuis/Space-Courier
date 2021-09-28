package com.space.SpaceCourier.persistence;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.space.SpaceCourier.domain.SpaceCourier;



@Service
@Transactional
public class SpaceCourierService {
	
	@Autowired
	private SpaceCourierRepository spaceCourierRepository;
	
	public SpaceCourier save(SpaceCourier spacecourier) {
		return spaceCourierRepository.save(spacecourier);
	}
	 
	
	//////////VRAGEN AAN DOCENT
	public Optional<SpaceCourier> findById(Long id) {
		return spaceCourierRepository.findById(id);
	}
	
	public Iterable <SpaceCourier> findAll() {
		Iterable <SpaceCourier> result = spaceCourierRepository.findAll();
		return result;
	}
}
