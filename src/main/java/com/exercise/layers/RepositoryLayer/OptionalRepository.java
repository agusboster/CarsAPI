package com.exercise.layers.RepositoryLayer;

import com.exercise.layers.Entities.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.nio.file.DirectoryIteratorException;

public interface OptionalRepository extends CrudRepository<Optional, Integer> {

    @Query("select o from Optional o where o.carId = :_carId")
    Iterable<Optional> findAllByCarId(Integer _carId);

}
