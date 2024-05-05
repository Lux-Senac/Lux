package br.com.lux.repository.sales;

import br.com.lux.domain.sales.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Integer> {

    @Query("SELECT c.name AS CarName, s.data_venda AS SaleDate, s.preco_venda AS " +
            "SalePrice FROM Vendas s INNER JOIN s.carro c")
    List<Object[]> findCarSalesDetails();

    @Query("SELECT c.page AS CarModel, COUNT(s) AS TotalSales " +
            "FROM Vendas s INNER JOIN s.carro c " +
            "WHERE c.page IN ('TeslaModelS', 'TeslaModelX', 'Porsche', 'Bmw', 'BYDYuan', 'BYDTan') " +
            "GROUP BY c.page")
    List<Object[]> findTotalSalesPerCarModel();
}
