package br.com.lux.services.car;

import br.com.lux.domain.car.Car;

public interface CarService
{
    void registerCar(Car car);

    Car findCarById(Integer id);
}