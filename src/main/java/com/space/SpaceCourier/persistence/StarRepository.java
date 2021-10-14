package com.space.SpaceCourier.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.space.SpaceCourier.model.Star;

@Component
public interface StarRepository extends CrudRepository<Star, Long> 
{

}
