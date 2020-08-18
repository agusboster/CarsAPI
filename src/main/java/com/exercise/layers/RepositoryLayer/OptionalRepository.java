package com.exercise.layers.RepositoryLayer;

import com.exercise.layers.Entities.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface OptionalRepository extends CrudRepository<Optional, Integer> {

    @Query("select o from Optional o where o.carId = :_carId")
    Iterable<Optional> findAllByCarId(Integer _carId);

    @Query("select o from Optional o where o.carId = :_carId and o.name = :_name")
    java.util.Optional<Optional> findByNameAndCarId(Integer _carId, String _name);
}
