package br.com.lux.services.car.carImp;

import br.com.lux.domain.car.Car;
import br.com.lux.repository.car.CarRepository;
import br.com.lux.services.car.CarService;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CarImpService implements CarService
{
    @Autowired
    private final CarRepository carRepository;

    @Autowired
    private final Validator validator;

    public CarImpService(CarRepository carRepository, Validator validator)
    {
        this.carRepository = carRepository;
        this.validator = validator;
    }

    @Override
    public List<Car> findCarAll()
    {
        return carRepository.findAll();
    }

    @Override
    public void registerCar(Car car)
    {
        Set<ConstraintViolation<Car>> violations = validator.validate(car);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        carRepository.save(car);
    }

    @Override
    public Car findCarById(Integer id)
    {
        return carRepository.findById(id).orElse(null);
    }
}
