package com.exercise.layers.ServiceLayer;

import com.exercise.layers.Entities.Car;
import com.exercise.layers.Exceptions.CarException;

import java.util.List;
import java.util.Optional;

public interface CarService {

    public Optional<List<Car>> getAllCars();
    public Optional<Car> findById(Integer id);
    public Car saveCar(Car _car);
    public void deleteCar(Integer _carId) throws CarException;
    public Car updateCar(Car _car) throws CarException;
    public void addOptionals(Car _car);
    public void updateOrSaveOptionals(Integer _carId, List<com.exercise.layers.Entities.Optional> _carOptionals);

}
