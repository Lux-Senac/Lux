package br.com.lux.controller.admin.sales;

import br.com.lux.services.exception.ServiceException;
import br.com.lux.services.sales.SalesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/delete-sales")
public class DeleteSalesController
{
    @Autowired
    private final SalesService salesService;

    public DeleteSalesController(SalesService salesService) {
        this.salesService = salesService;
    }

    @DeleteMapping
    public String deleteSales(@RequestParam("id") Integer id, RedirectAttributes redirectAttributes)
    {
        try
        {
            if(id == null)
            {
                return "redirect:/admin/all-cars";
            }

            salesService.deleteSales(id);

            return "redirect:/admin/all-sales";
        }
        catch(ServiceException e)
        {
            redirectAttributes.addAttribute("error", e.getMessage());

            return "redirect:/admin/all-sales";
        }
        catch (Exception e)
        {
            redirectAttributes.addAttribute("error", "Erro ao deletar a venda!");

            return "redirect:/admin/all-sales";
        }
    }
}
