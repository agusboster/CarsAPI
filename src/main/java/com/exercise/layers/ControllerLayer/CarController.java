package com.exercise.layers.ControllerLayer;

import com.exercise.layers.Entities.Car.Car;
import com.exercise.layers.Entities.Stat.Stat;
import com.exercise.layers.Entities.Stat.StatsDTO;
import com.exercise.layers.Exceptions.CarException;
import com.exercise.layers.ServiceLayer.CarService;
import com.exercise.layers.ServiceLayer.OptionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/carsAPI")
public class CarController {
    private CarService carService;
    private OptionalService optionalService;

    @Autowired
    public CarController(CarService _carService, OptionalService _optionalService){
        this.carService=_carService;
        this.optionalService=_optionalService;
    }

    @GetMapping(path="/hola")
    public @ResponseBody String welcome() {
        // This returns a JSON or XML with the users
        return "Bienvenidos!";
    }

    @PostMapping(path = "/", consumes = "application/json", produces = {"application/json"})
    public ResponseEntity<Object> saveCar (@RequestBody Car newCar){
        Car savedCar = carService.saveCar(newCar);
        Car responseCar = carService.findById(savedCar.getId()).get();
        return ResponseEntity.status(HttpStatus.OK).body(responseCar);
    }

    @PutMapping(path = "/", consumes = "application/json", produces = {"application/json"})
    public ResponseEntity<Object> updateCar(@RequestBody Car car){
        try {
            Car updatedCar = carService.updateCar(car);
            Car responseCar = carService.findById(updatedCar.getId()).get();
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


    @GetMapping(path = "/cars/{_id}", produces = {"application/json"})
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


    @GetMapping(path = "/getAll", consumes = "application/json", produces = {"application/json"})
    public ResponseEntity<Object> getAllCars(){
        Optional<List<Car>> carsAux = carService.getAllCars();
        try {
            List<Car> cars = carsAux.get();
            return ResponseEntity.status(HttpStatus.OK).body(cars);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("There are not any cars in the db.");
        }
    }

    @GetMapping(path = "/carPrice/{_id}", consumes = "application/json", produces = {"application/json"})
    public ResponseEntity<Object> getCarPrice(@PathVariable("_id") String _id){
        if(_id.isBlank()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Car id should be provided.");
        }
        Integer carId = Integer.valueOf(_id);
        try {
            Float carPrice = carService.getCarPrice(carId);
            return ResponseEntity.status(HttpStatus.OK).body(carPrice);
        } catch (CarException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is not any car with id " + _id);
        }

    }

    @GetMapping(path = "/stats", consumes = "application/json", produces = {"application/json"})
    public ResponseEntity<Object> getStats(){
        StatsDTO stats = new StatsDTO();
        List<Stat> carsStats = carService.getCarsStats();
        List<Stat> optionalStats = optionalService.getOptionalsStats();
        int carsCount = carService.getAllCars().get().size();
        int optionalsCount = optionalService.getAllOptionals().size();
        stats.setCars(carsStats);
        stats.setOptionals(optionalStats);
        stats.setCarsCount(carsCount);
        stats.setOptionalsCount(optionalsCount);
        return ResponseEntity.status(HttpStatus.OK).body(stats);
    }




}
