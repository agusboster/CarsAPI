package com.exercise.layers.RepositoryLayer;

import com.exercise.layers.Entities.Optional.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface OptionalRepository extends CrudRepository<Optional, Integer> {

    @Query("select o from Optional o where o.carId = :_carId")
    Iterable<Optional> findAllByCarId(@Param("_carId") Integer _carId);

    @Query("select o from Optional o where o.carId = :_carId and o.name = :_name")
    java.util.Optional<Optional> findByNameAndCarId(@Param("_carId") Integer _carId, @Param("_name") String _name);

    @Query("select o from Optional o where o.name = :_name")
    Iterable<Optional> findAllByName(@Param("_name") String _name);
}
