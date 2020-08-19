package com.exercise.layers.RepositoryLayer;

import com.exercise.layers.Entities.Car.Car;
import com.exercise.layers.Entities.Optional.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CarRepository extends CrudRepository<Car, Integer> {
    @Query("select c from Car c where c.type = :_type")
    Iterable<Car> findAllByType(@Param("_type") String _type);
}
