package br.com.lux.services.car.carImp;

import br.com.lux.domain.car.Car;
import br.com.lux.repository.car.CarRepository;
import br.com.lux.services.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarImpService implements CarService
{
    @Autowired
    private CarRepository carRepository;

    @Override
    public void registerCar(Car car)
    {
        carRepository.save(car);
    }

    @Override
    public Car findCarById(Integer id)
    {
        return carRepository.findById(id).orElse(null);
    }
}
