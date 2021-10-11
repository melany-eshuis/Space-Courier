package com.space.SpaceCourier.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.space.SpaceCourier.Model.Planet;

@Component
public interface PlanetRepository extends CrudRepository<Planet, Long> 
{

}
