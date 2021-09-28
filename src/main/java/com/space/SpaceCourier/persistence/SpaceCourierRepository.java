package com.space.SpaceCourier.persistence;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.space.SpaceCourier.domain.SpaceCourier;

@Component
public interface SpaceCourierRepository extends CrudRepository<SpaceCourier, Long>{

}
