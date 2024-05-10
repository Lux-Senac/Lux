package br.com.lux.car;

import br.com.lux.domain.car.Car;
import br.com.lux.repository.car.CarRepository;
import br.com.lux.services.car.CarService;
import br.com.lux.services.car.carImp.CarImpService;
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

    private CarService carService;

    @Before
    public void setUp() {
        carService = new CarImpService(carRepository, validator); // Substitua pela sua implementação
    }

    @Test
    public void testRegisterCarSuccess() {
        // Arrange
        Car car = new Car();
        car.setName("Example Car");
        car.setMotor("V6");
        car.setPrice(BigDecimal.valueOf(50000));
        // ... defina outros campos obrigatórios ...

        // Act
        carService.registerCar(car);

        // Assert
        ArgumentCaptor<Car> carCaptor = ArgumentCaptor.forClass(Car.class);
        verify(carRepository).save(carCaptor.capture());
        Car savedCar = carCaptor.getValue();
        assertEquals(car, savedCar);
    }

    @Test(expected = ConstraintViolationException.class)
    public void testRegisterCarWithInvalidData() {
        // Arrange
        Car car = new Car();
        // Deixe campos obrigatórios vazios ou com valores inválidos

        // Simulando o comportamento do Validator para retornar violações
        Set<ConstraintViolation<Car>> violations = Collections.singleton(mock(ConstraintViolation.class));
        when(validator.validate(car)).thenReturn(violations);

        // Act
        carService.registerCar(car);

        // Assert - A exceção ConstraintViolationException deve ser lançada
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
