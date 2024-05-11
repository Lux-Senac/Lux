package br.com.lux.services.car;

import br.com.lux.domain.car.Car;

import java.util.List;

public interface CarService
{
    List<Car> findCarAll();

    void registerCar(Car car);

    Car findCarById(Integer id);
}