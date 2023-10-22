package PP_3_1_4_Spring_security_bootstrap_version3.controllers;


import PP_3_1_4_Spring_security_bootstrap_version3.models.Role;
import PP_3_1_4_Spring_security_bootstrap_version3.models.User;
import PP_3_1_4_Spring_security_bootstrap_version3.service.RoleService;
import PP_3_1_4_Spring_security_bootstrap_version3.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class RegistrationController {

    private final UserService userService;

    private final RoleService roleService;

    public RegistrationController(UserService userService, RoleService roleService) {
        this.userService = userService;

        this.roleService = roleService;
    }
    @GetMapping("/registration")
    public String registrationList(@ModelAttribute("user") User user, Model model){
        List<Role> roles = roleService.listRoles();
        model.addAttribute("allRoles", roles);
        return "registration";
    }


    @PostMapping("/registration")
    public String userRegistration(@ModelAttribute("user") User user){

        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/homePage")
    public String homePage(@RequestParam(value = "error", required = false) String error,
                           @RequestParam(value = "logout", required = false) String logout,
                           Model model) {
        model.addAttribute("error", error!=null);
        model.addAttribute("logout", logout!=null);

        return "homePage";
    }
}