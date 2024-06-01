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
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import jakarta.validation.ConstraintViolationException;

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
    public void testFindCarByIdSuccess() {
        // Arrange
        Integer carId = 1;
        Car car = new Car();
        car.setId(carId);
        when(carRepository.findById(carId)).thenReturn(Optional.of(car));

        // Act
        Car result = carService.findCarById(carId);

        // Assert
        assertEquals(car, result);
    }

    @Test
    public void testFindCarByIdWithInvalidId() {
        // Arrange
        Integer invalidCarId = -1;
        when(carRepository.findById(invalidCarId)).thenReturn(Optional.empty());

        // Act
        Car result = carService.findCarById(invalidCarId);

        // Assert
        assertNull(result);
    }
}
