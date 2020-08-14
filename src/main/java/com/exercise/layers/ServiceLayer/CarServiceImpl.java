package com.exercise.layers.ServiceLayer;

import com.exercise.layers.Entities.Car;
import com.exercise.layers.Entities.Optional;
import com.exercise.layers.RepositoryLayer.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("carService")
@Transactional
public class CarServiceImpl implements CarService {

    CarRepository carRepository;
    OptionalService optionalService;

    @Autowired
    public CarServiceImpl(CarRepository _carRepo, OptionalService _optionalService) {
        this.carRepository = _carRepo;
        this.optionalService = _optionalService;
    }



    public void saveCar(Car _car) {

    }

    public void deleteCar(String id) {

    }

    public void updateCar(Car _car) {

    }

    ///Manejar la posible exception en el controller
    public java.util.Optional<Car> findById(Integer _carId){
        return carRepository.findById(_carId);
    }

    //Manejar la posible exception en el controller
    public java.util.Optional<List<Car>> getAllCars(){
        List<Car> cars = new ArrayList<>();
        Iterable<Car> iterableCars = carRepository.findAll();
        iterableCars.forEach(car -> addOptionals(car));
        iterableCars.forEach(car -> cars.add(car));
        return java.util.Optional.ofNullable(cars);
    }

    public void addOptionals(Car _car){
        List<Optional> carOptionals = optionalService.findAllByCarId(_car.getId());
        _car.setOptionals(carOptionals);
    }



}
