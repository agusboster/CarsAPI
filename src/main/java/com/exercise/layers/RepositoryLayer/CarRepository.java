package com.exercise.layers.RepositoryLayer;

import com.exercise.layers.Entities.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Integer> {
}
