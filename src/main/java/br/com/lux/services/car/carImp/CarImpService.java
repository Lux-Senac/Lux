package br.com.lux.services.car.carImp;

import br.com.lux.domain.car.Car;
import br.com.lux.repository.car.CarRepository;
import br.com.lux.services.car.CarService;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
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
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Car findCarById(Integer id)
    {
        return carRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteCar(Integer id)
    {
        carRepository.deleteById(id);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public long countCars()
    {
        return carRepository.count();
    }
}
