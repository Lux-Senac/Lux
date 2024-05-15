package br.com.lux.controller.admin.user;

import br.com.lux.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/delete-user")
public class DeleteUserController
{
    @Autowired
    private final UserService userService;

    public DeleteUserController(UserService userService)
    {
        this.userService = userService;
    }

    @DeleteMapping
    public String deleteUser(@RequestParam("id") Integer id)
    {
        if(id == null && id == 1)
        {
            return "admin/user/allusers";
        }

        userService.deleteById(id);

        return "admin/user/deleteuser";
    }
}
