package br.com.lux.services.car;

import br.com.lux.domain.car.Car;
import br.com.lux.domain.car.CarPageType;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface CarService
{
    Page<Car> findCarAll(int page, int size);

    Page<Car> searchCars(String searchTerm, int page, int size);

    List<Car> findCarAll();

    void registerCar(Car car);

    Car findCarById(Integer id);

    void deleteCar(Integer id);

    long countCars();

    Map<CarPageType, Long> getCarTypeCounts();
}