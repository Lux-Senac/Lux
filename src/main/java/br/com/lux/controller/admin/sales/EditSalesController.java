package br.com.lux.controller.admin.sales;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/edit-sales")
public class EditSalesController
{
    @GetMapping
    public String editSales(@RequestParam("id") Integer id, Model model, HttpSession session)
    {
        return "admin/sales/editSales";
    }
}
