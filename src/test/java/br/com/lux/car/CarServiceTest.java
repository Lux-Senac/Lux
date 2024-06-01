package br.com.lux.car;

import br.com.lux.domain.car.Car;
import br.com.lux.repository.car.CarRepository;
import br.com.lux.services.car.CarService;
import br.com.lux.services.car.carImp.CarImpService;
import br.com.lux.services.exception.ServiceException;
import jakarta.validation.Validation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import jakarta.validation.Validator;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @Mock
    private Validator validator;

    @Mock
    private CarService carService;

    @Before
    public void setUp() {
        carService = new CarImpService(carRepository, validator);
    }

    @Test
    public void testRegisterCar() {
        Car car = new Car();
        car.setName("Example Car");
        car.setMotor("V6");
        car.setPrice(BigDecimal.valueOf(50000));



        // Act
        carService.registerCar(car);

        // Assert
        ArgumentCaptor<Car> carCaptor = ArgumentCaptor.forClass(Car.class);
        verify(carRepository).save(carCaptor.capture());
        Car savedCar = carCaptor.getValue();
        assertEquals(car, savedCar);
    }

    @Test
    public void testFindCarByIdSuccess() {

        Integer carId = 1;
        Car car = new Car();
        car.setId(carId);
        when(carRepository.findById(carId)).thenReturn(Optional.of(car));

        Car result = carService.findCarById(carId);

        assertEquals(car, result);
    }

    @Test
    public void testFindCarByIdWithInvalidId() {
        Integer invalidCarId = -1;
        when(carRepository.findById(invalidCarId)).thenReturn(Optional.empty());

        Car result = carService.findCarById(invalidCarId);

        assertNull(result);
    }
}