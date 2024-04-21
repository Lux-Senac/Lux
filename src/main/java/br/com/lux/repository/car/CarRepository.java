package br.com.lux.repository.car;

import br.com.lux.domain.car.Car;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer>
{

}
