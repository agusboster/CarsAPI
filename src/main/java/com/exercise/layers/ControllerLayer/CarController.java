package com.exercise.layers.ControllerLayer;

import com.exercise.layers.Entities.Car;
import com.exercise.layers.Exceptions.CarException;
import com.exercise.layers.ServiceLayer.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carsAPI")
public class CarController {
    private CarService carService;

    @Autowired
    public CarController(CarService _carService){
        this.carService=_carService;
    }

    @GetMapping(path = "/cars", consumes = "application/json", produces = {"application/json"})
    public ResponseEntity<Object> getAllCars(){
        Optional<List<Car>> carsAux = carService.getAllCars();
        try {
            List<Car> cars = carsAux.get();
            return ResponseEntity.status(HttpStatus.OK).body(cars);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong. Error message: " + e.getMessage());
        }
    }

    @GetMapping(path = "/{_id}", produces = {"application/json"})
    public ResponseEntity<Object> getCarById(@PathVariable("_id") String _id){
        if(_id.isBlank()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Car id should be provided.");
        }
        Integer carId = Integer.valueOf(_id);
        Optional<Car> carAux = carService.findById(carId);
        try {
            Car car = carAux.get();
            return ResponseEntity.status(HttpStatus.OK).body(car);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is not any car with id " + _id);
        }
    }

    @PutMapping(path = "/", consumes = "application/json", produces = {"application/json"})
    public ResponseEntity<Object> updateCar(@RequestBody Car car){
        try {
            Car responseCar = carService.updateCar(car);
            return ResponseEntity.status(HttpStatus.OK).body(responseCar);
        } catch (CarException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping(path = "/{_id}", produces = {"application/json"})
    public ResponseEntity<Object> deleteCar (@PathVariable("_id") String _id){
        if(_id.isBlank()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Car id should be provided.");
        }
        Integer carId = Integer.valueOf(_id);
        try {
            carService.deleteCar(carId);
            return ResponseEntity.status(HttpStatus.OK).body("Car with id: " + _id + " has been deleted.");
        } catch (CarException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e.getMessage());
        }
    }

    @PostMapping(path = "/", consumes = "application/json", produces = {"application/json"})
    public ResponseEntity<Object> saveCar (@RequestBody Car newCar){
        Car savedCar = carService.saveCar(newCar);
        return ResponseEntity.status(HttpStatus.OK).body(savedCar);
    }

}
