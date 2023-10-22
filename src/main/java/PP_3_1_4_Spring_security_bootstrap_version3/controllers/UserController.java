package PP_3_1_4_Spring_security_bootstrap_version3.controllers;


import PP_3_1_4_Spring_security_bootstrap_version3.models.User;
import PP_3_1_4_Spring_security_bootstrap_version3.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String userPage(Model model, Principal principal){
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("userPrincipal", user);
        return "user_page";
    }

}