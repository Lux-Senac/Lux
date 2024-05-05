package br.com.lux.controller.admin;

import br.com.lux.domain.car.CarPageType;
import br.com.lux.services.sales.SalesService;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SequencedCollection;

@Controller
@RequestMapping("/admin")
public class AdminController
{

    private final SalesService salesService;

    @Autowired
    public AdminController(SalesService salesService)
    {
        this.salesService = salesService;
    }

    @RequestMapping
    public String adminHome(Model model)
    {
        List<Object[]> salesData = salesService.findTotalSalesPerCarModel();

        Map<String, Integer> salesMap = new HashMap<>();
        for (Object[] row : salesData) {
            CarPageType carPageType = (CarPageType) row[0];
            salesMap.put(carPageType.name(), ((Long) row[1]).intValue());
        }
        System.out.println(salesMap);

        model.addAttribute("sales", salesMap);

        return "admin/adminHome";
    }
}
