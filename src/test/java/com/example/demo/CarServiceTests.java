package com.example.demo;


import com.exercise.layers.Entities.Car.Car;
import com.exercise.layers.Exceptions.CarException;
import com.exercise.layers.ServiceLayer.CarService;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarServiceTests {

    @Autowired
    CarService carService;

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public final void testFindById_WhenTheCarDoesNotExist(){
        Optional<Car> car = this.carService.findById(Integer.valueOf(530));
        assertTrue("There is no car with id 530.", !car.isPresent());
    }

    @Test
    public final void testFindById_WhenTheCarDoesExist(){
        Optional<Car> car = this.carService.findById(Integer.valueOf(1));
        assertTrue("There car with id 1 has been found.", car.isPresent());
    }

    @Test
    public final void testGetAll() {
        List<Car> testList = this.carService.getAllCars().get();
        assertTrue("Since there are test data in DB, list should not be empty", !testList.isEmpty());
    }

    @Rule
    public final ExpectedException getPriceRule = ExpectedException.none();
    @Test
    public final void testGetCarPrice_WhenTheCarDoesNotExist() throws CarException {
        getPriceRule.expect(CarException.class);
        this.carService.getCarPrice(Integer.valueOf(530));
    }

    //Mejorar este test
    @Test
    public final void testGetCarPrice_WhenTheCarDoesExist() throws CarException {
        getPriceRule.expect(CarException.class);
        Float carPrice = this.carService.getCarPrice(Integer.valueOf(1));
        assertTrue("The car price is " + carPrice, carPrice.isNaN());
    }

    @Rule
    public final ExpectedException deleteRule = ExpectedException.none();
    @Test
    public final void testDeleteCar_WhenTheCarDoesNotExist() throws CarException {
        deleteRule.expect(CarException.class);
        this.carService.deleteCar(Integer.valueOf(530));
    }

    @Rule
    public final ExpectedException updateRule = ExpectedException.none();
    @Test
    public final void testUpdateCar_WhenTheCarDoesNotExist() throws CarException {
        Car unexistentCar = new Car(Integer.valueOf(530), "Polo", "Sedan", Float.valueOf(800000));
        updateRule.expect(CarException.class);
        this.carService.updateCar(unexistentCar);
    }

}
