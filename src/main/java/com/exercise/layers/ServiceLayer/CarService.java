package com.exercise.layers.ServiceLayer;

import com.exercise.layers.Entities.Car;
import com.exercise.layers.Entities.Stat;
import com.exercise.layers.Exceptions.CarException;

import java.util.List;
import java.util.Optional;

public interface CarService {

    Optional<List<Car>> getAllCars();
    Optional<Car> findById(Integer id);
    Car saveCar(Car _car);
    void deleteCar(Integer _carId) throws CarException;
    Car updateCar(Car _car) throws CarException;
    Float getCarPrice(Integer _id) throws CarException;
    void addOptionals(Car _car);
    void updateOrSaveOptionals(Integer _carId, List<com.exercise.layers.Entities.Optional> _carOptionals);
    List<Stat> getCarsStats();
}
