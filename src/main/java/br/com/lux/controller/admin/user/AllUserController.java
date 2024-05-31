package br.com.lux.controller.admin.user;

import br.com.lux.domain.user.User;
import br.com.lux.services.user.UserService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin/all-users")
public class AllUserController
{
    @Autowired
    private final UserService userService;

    public AllUserController(UserService userService)
    {
        this.userService = userService;
    }

    private final Map<String, Object> response = new HashMap<>();

    @GetMapping
    public String allUsers(Model model, HttpSession session)
    {
            return "admin/user/griduser";
    }

    @GetMapping("/json")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> allUsersJson(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(value = "search[value]", required = false) String searchTerm)
    {
        Page<User> users;

        if (searchTerm != null && !searchTerm.isEmpty()) {users = userService.searchUsers(searchTerm, page, size);
        } else {
            users = userService.findAllUsers(page, size);
        }

        response.put("data", users.getContent());
        response.put("iTotalRecords", users.getTotalElements());
        response.put("iTotalDisplayRecords", users.getTotalElements());

        return ResponseEntity.ok(response);
    }
}
