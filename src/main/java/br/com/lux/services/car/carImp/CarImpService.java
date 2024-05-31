package br.com.lux.services.car.carImp;

import br.com.lux.domain.car.Car;
import br.com.lux.domain.car.CarPageType;
import br.com.lux.repository.car.CarRepository;
import br.com.lux.services.car.CarService;

import br.com.lux.services.exception.ServiceException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        try
        {
            return carRepository.findAll();
        }
        catch (Exception e)
        {
            throw new ServiceException("Erro ao buscar todos os carros! " + e.getMessage());
        }
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<Car> findCarAll(int page, int size)
    {
        try
        {
            Pageable pageable = PageRequest.of(page, size);
            return carRepository.findAll(pageable);
        }
        catch (Exception e)
        {
            throw new ServiceException("Erro ao buscar todos os carros! " + e.getMessage());
        }
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<Car> searchCars(String searchTerm, int page, int size)
    {
        try
        {
            Pageable pageable = PageRequest.of(page, size);
            return carRepository.findByNameContainingIgnoreCase(searchTerm, pageable);
        }
        catch (Exception e)
        {
            throw new ServiceException("Erro ao buscar os carros! " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void registerCar(Car car)
    {
        try
        {
            Set<ConstraintViolation<Car>> violations = validator.validate(car);
            if(!violations.isEmpty())
                throw new ConstraintViolationException(violations);

            carRepository.save(car);
        }
        catch (DataIntegrityViolationException e)
        {
            throw new ServiceException("Erro ao cadastrar o carro! " + e.getMessage());
        }
        catch (ConstraintViolationException e)
        {
            throw new ServiceException("Erro ao validar o carro! " + e.getMessage());
        }
        catch (Exception e)
        {
            throw new ServiceException("Erro inesperado ao cadastrar o carro! " + e.getMessage());
        }
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Car findCarById(Integer id)
    {
        try
        {
            return carRepository.findById(id).orElse(null);
        }
        catch (Exception e)
        {
            throw new ServiceException("Erro ao buscar o carro! " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void deleteCar(Integer id)
    {
        try
        {
            if (id == null)
                throw new ServiceException("Id inválido!");

            carRepository.deleteById(id);
        }
        catch (ServiceException e)
        {
            throw new ServiceException("Erro ao deletar o carro por id! " + e.getMessage());
        }
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public long countCars()
    {
        try
        {
            return carRepository.count();
        }
        catch (Exception e)
        {
            throw new ServiceException("Erro ao contar os carros! " + e.getMessage());
        }
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Map<CarPageType, Long> getCarTypeCounts()
    {
        try
        {
            Map<CarPageType, Long> carTypeCounts = new HashMap<>();

            for (CarPageType type : CarPageType.values())
                carTypeCounts.put(type, carRepository.countByPage(type));


            return carTypeCounts;
        }
        catch (Exception e)
        {
            throw new ServiceException("Erro ao contar os tipos de carros! " + e.getMessage());
        }

    }
}
