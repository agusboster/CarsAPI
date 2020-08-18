package com.exercise.layers.ServiceLayer;

import com.exercise.layers.Entities.Car;
import com.exercise.layers.Entities.Optional;
import com.exercise.layers.Exceptions.CarException;
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


    public Car saveCar(Car _car) {
        if(_car.getId() != null){
            _car.setId(null);
        }
        updateOrSaveOptionals(_car.getId(), _car.getOptionals());
        return carRepository.save(_car);
    }

    public void deleteCar(Integer _carId) throws CarException{
        java.util.Optional<Car> carAux = carRepository.findById(_carId);
        if(carAux.isEmpty()){
            throw new CarException("The car with id " + _carId + " didn't exist before the request. The deleting" +
                    "operation is not necessary.");
        }
        carRepository.deleteById(_carId);
    }

    public Car updateCar(Car _car) throws CarException {
        java.util.Optional<Car> carAux = carRepository.findById(_car.getId());
        if(carAux.isEmpty()){
            if(_car.getId() == null){
                throw new CarException("Please provide the car Id that you would like to update.");
            } else{
                throw new CarException("There is no existing car in the db with id "
                        + _car.getId() + " to update.");
            }
        }
        updateOrSaveOptionals(_car.getId(), _car.getOptionals());
        return carRepository.save(_car);
    }

    public Float getCarPrice(Integer _id) throws CarException{
        java.util.Optional<Car> carAux = findById(_id);
        if(carAux.isEmpty()){
            throw new CarException("There is not any car with id " + _id);
        }
        Float basicPrice = carAux.get().getBasicPrice();
        Float optionalsPrice = optionalService.getOptionalsPrice(carAux.get().getOptionals());

        return basicPrice + optionalsPrice;
    }

    ///Manejar la posible exception en el controller
    public java.util.Optional<Car> findById(Integer _carId){
        java.util.Optional<Car> carAux = carRepository.findById(_carId);
        if(carAux.isEmpty()){
            return carAux;
        }
        Car car = carAux.get();
        addOptionals(car);
        return java.util.Optional.ofNullable(car);
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

    public void updateOrSaveOptionals(Integer _carId, List<Optional> _carOptionals){
        optionalService.updateOrSaveCarOptionals(_carId, _carOptionals);
    }


}
