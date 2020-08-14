package com.exercise.layers.ServiceLayer;

import com.exercise.layers.Entities.Car;
import com.exercise.layers.Exceptions.CarException;

import java.util.List;
import java.util.Optional;

public interface CarService {

    public Optional<List<Car>> getAllCars();
    public Optional<Car> findById(Integer id) throws CarException;
    public void saveCar(Car _car);
    public void deleteCar(String id);
    public void updateCar(Car _car);
    public void addOptionals(Car _car);

}
