package org.superbiz.struts;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static javax.management.Query.value;

@Controller
public class UsersController {

    private final UserRepository userRepository;

    public UsersController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/addUser")
    public String addUserForm(@ModelAttribute User user,Model model) {
        userRepository.save(user);
        //model.addAttribute("successMessage", "User added successfully");
        return "addedUser";
    }

    @GetMapping ("/addUser")
    public String getUserForm() {
        return "addUserForm";
    }

    @GetMapping("/findUser")
    public String findUserForm() {
        return "findUserForm";
    }

    @PostMapping("/findUser")
    public String findUser(@RequestParam long id, Model model) {
        User user = userRepository.findOne(id);

        if (user == null) {
            model.addAttribute("errorMessage", "User not found");
            return "findUserForm";
        }

        model.addAttribute("user", user);
        return "displayUser";
    }

    @GetMapping("/list")
    public String listUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "displayUsers";
    }
}


