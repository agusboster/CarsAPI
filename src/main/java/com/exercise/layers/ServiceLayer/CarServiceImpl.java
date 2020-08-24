package com.exercise.layers.ServiceLayer;

import com.exercise.layers.Entities.Car.Car;
import com.exercise.layers.Entities.Optional.Optional;
import com.exercise.layers.Entities.Stat.Stat;
import com.exercise.layers.Exceptions.CarException;
import com.exercise.layers.RepositoryLayer.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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
        List<Optional> carOptionals = _car.getOptionals();
        Car savedCar = carRepository.save(_car);
        carOptionals.forEach(optional -> optional.setCarId(savedCar.getId()));
        updateOrSaveOptionals(savedCar.getId(), carOptionals);
        return savedCar;
    }

    public void deleteCar(Integer _carId) throws CarException{
        java.util.Optional<Car> carAux = carRepository.findById(_carId);
        if(!carAux.isPresent()){
            throw new CarException("The car with id " + _carId + " didn't exist before the request. The deleting" +
                    "operation is not necessary.");
        }
        optionalService.deleteCarOptionals(_carId);
        carRepository.deleteById(_carId);
    }

    public Car updateCar(Car _car) throws CarException {
        java.util.Optional<Car> carAux = carRepository.findById(_car.getId());
        if(!carAux.isPresent()){
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
        if(!carAux.isPresent()){
            throw new CarException("There is not any car with id " + _id);
        }
        Float basicPrice = carAux.get().getBasicPrice();
        Float optionalsPrice = optionalService.getOptionalsPrice(carAux.get().getOptionals());

        return basicPrice + optionalsPrice;
    }

    ///Manejar la posible exception en el controller
    public java.util.Optional<Car> findById(Integer _carId){
        java.util.Optional<Car> carAux = carRepository.findById(_carId);
        if(!carAux.isPresent()){
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

    public List<Stat> getCarsStats() {
        List<Stat> stats = new ArrayList<>();
        int totalCars = getAllCars().get().size();
        getCarStats("sedan", stats, totalCars);
        getCarStats("family", stats, totalCars);
        getCarStats("coupe", stats, totalCars);
        return stats;
    }

    public List<Car> getAllCarsByType(String _type) {
        List<Car> cars = new ArrayList<>();
        Iterable<Car> carsAux = carRepository.findAllByType(_type);
        carsAux.forEach(carAux -> cars.add(carAux));
        return cars;
    }

    public void getCarStats(String carType, List<Stat> stats, int totalCars){
        int totalCar = getAllCarsByType(carType).size();
        float percent = (totalCar *100)/totalCars;
        Stat carStats = new Stat(carType, totalCar, percent);
        stats.add(carStats);
    }


}
