package br.com.lux.services.car.admin;

import br.com.lux.domain.car.Car;
import br.com.lux.repository.car.CarRepository;
import br.com.lux.services.car.CarAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarAdminImpService implements CarAdminService
{
    @Autowired
    private CarRepository carRepository;

    @Override
    public void registerCar(Car car)
    {
        carRepository.save(car);
    }
}
