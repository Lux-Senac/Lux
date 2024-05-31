package br.com.lux.repository.sales;

import br.com.lux.domain.sales.Sales;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Integer> {

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Query("SELECT c.name AS CarName, s.datavenda AS SaleDate, s.precovenda AS " +
            "SalePrice FROM Vendas s INNER JOIN s.carro c")
    List<Object[]> findCarSalesDetails();

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Query("SELECT c.page AS CarModel, COUNT(s) AS TotalSales " +
            "FROM Vendas s INNER JOIN s.carro c " +
            "WHERE c.page IN ('TeslaModelS', 'TeslaModelX', 'Porsche', 'Bmw', 'BYDYuan', 'BYDTan') " +
            "GROUP BY c.page")
    List<Object[]> findTotalSalesPerCarModel();

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Query("SELECT SUM(s.precovenda) FROM Vendas s WHERE FUNCTION('MONTH', s.datavenda) = FUNCTION('MONTH', :data) AND FUNCTION('YEAR', s.datavenda) = FUNCTION('YEAR', :data)")
    BigDecimal ganhosMensais(Date data);

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    List<Sales> findByDatavendaBetween(Date dataInicio, Date dataFim);

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Query("SELECT s FROM Vendas s WHERE s.usuario.username = :userName or s.cliente.nome = :clientName or s.carro.name = :carName")
    Page<Sales> findByUserNameAndClientNameAndCarName(String userName, String clientName, String carName, Pageable pageable);
 }