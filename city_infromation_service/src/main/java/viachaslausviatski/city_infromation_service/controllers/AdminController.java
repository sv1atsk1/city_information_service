package viachaslausviatski.city_infromation_service.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import viachaslausviatski.city_infromation_service.entities.User;
import viachaslausviatski.city_infromation_service.entities.enums.Roles;
import viachaslausviatski.city_infromation_service.services.UserService;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {
    private final UserService userService;

    @GetMapping("/admin")
    public String admin(Model model, Principal principal) {
        model.addAttribute("users", userService.userList());
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "admin";
    }

    @PostMapping("/admin/user/ban/{id}")
    public String userBan(@PathVariable("id") Long id) {
        userService.banUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/user/edit/{userId}")
    public String userEdit(@PathVariable("userId") Long userId, Model model) {
        User userToEdit = userService.getUserById(userId);
        model.addAttribute("user", userToEdit);
        model.addAttribute("roles", Roles.values());
        return "user-edit";
    }

    @PostMapping("/admin/user/edit")
    public String userEdit(@RequestParam("userId") Long userId, @RequestParam("selectedRoles") String selectedRole) {
        User user = userService.getUserById(userId);

        if (user != null) {

            Set<Roles> newRoles = new HashSet<>();
            newRoles.add(Roles.valueOf(selectedRole));


            userService.changeUserRoles(user, newRoles);


            return "redirect:/admin";
        } else {
            return "error-page";
        }
    }
}
