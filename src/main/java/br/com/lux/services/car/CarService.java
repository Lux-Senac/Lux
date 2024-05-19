package br.com.lux.services.car;

import br.com.lux.domain.car.Car;
import br.com.lux.domain.car.CarPageType;

import java.util.List;
import java.util.Map;

public interface CarService
{
    List<Car> findCarAll();

    void registerCar(Car car);

    Car findCarById(Integer id);

    void deleteCar(Integer id);

    long countCars();

    Map<CarPageType, Long> getCarTypeCounts();
}