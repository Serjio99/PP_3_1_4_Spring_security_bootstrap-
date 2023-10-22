package PP_3_1_4_Spring_security_bootstrap_version3.controllers;


import PP_3_1_4_Spring_security_bootstrap_version3.models.User;
import PP_3_1_4_Spring_security_bootstrap_version3.service.RoleService;
import PP_3_1_4_Spring_security_bootstrap_version3.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class AdminController {

    private final UserService userService;

    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String showAllUsers(Model model, Principal principal) {
        List<User> allUsers = userService.getAllUsers();
        User user = new User();
        User user1 = userService.findByUsername(principal.getName());
        model.addAttribute("newUser",user); //новый юзер для формы
        model.addAttribute("userPrincipal",user1); //авторизованный юзер
        model.addAttribute("allUsers", allUsers); //список всех юзеров
        model.addAttribute("allRoles", user1.getRoles()); //список всех ролей авторизованного юзера
        model.addAttribute("AllRolesBD", roleService.listRoles()); //список всех ролей в БД
        return "admin_all_users";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("newUser") User user){
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String deleteUser (@PathVariable("id") int id){
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @PatchMapping("/{id}")
    public String updateUser (@ModelAttribute("user") User user, @PathVariable("id") int id) {

        userService.updateUser(user, id);//передаю id и user-а в метод для обновления и проверки пароля
        return "redirect:/admin";
    }
    @GetMapping("/{id}")
    public String getUser(Model model, @PathVariable("id") int id){
        model.addAttribute("userGet", userService.getUser(id));
        model.addAttribute("roleList", roleService.listRoles());
        return "redirect:/admin";
    }

}