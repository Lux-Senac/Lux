package br.com.lux.controller.admin.sales;

import br.com.lux.domain.sales.Sales;
import br.com.lux.domain.user.User;
import br.com.lux.services.exception.ServiceException;
import br.com.lux.services.sales.SalesService;

import jakarta.servlet.http.HttpSession;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/all-sales")
public class AllSalesController
{
    @Autowired
    private final SalesService saleService;

    public AllSalesController(SalesService saleService) {
        this.saleService = saleService;
    }

    private final Map<String, Object> response = new HashMap<>();

    @GetMapping("/json")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> allSalesJson(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(value = "search[value]", required = false) String searchTerm)
    {
        Page<Sales> sales;

        if (searchTerm != null && !searchTerm.isEmpty()) {
            sales = saleService.searchSales(searchTerm, page, size);
        } else {
            sales = saleService.findSaleAll(page, size);
        }

        response.put("data", sales.getContent());
        response.put("iTotalRecords", sales.getTotalElements());
        response.put("iTotalDisplayRecords", sales.getTotalElements());

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public String allSales(Model model, HttpSession session)
    {
        return "admin/sales/gridSales";
    }
}
