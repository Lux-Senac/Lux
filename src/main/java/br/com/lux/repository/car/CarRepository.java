package br.com.lux.repository.car;

import br.com.lux.domain.car.Car;
import br.com.lux.domain.car.CarPageType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer>
{
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    long countByPage(CarPageType tipocarro);

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    Page<Car> findByNameContainingIgnoreCase(String name, Pageable pageable);
}