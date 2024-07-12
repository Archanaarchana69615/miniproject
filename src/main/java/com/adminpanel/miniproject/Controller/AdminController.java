package com.adminpanel.miniproject.Controller;



import com.adminpanel.miniproject.dto.UserDto;
import com.adminpanel.miniproject.entity.User;
import com.adminpanel.miniproject.security.CustomerUserDetails;
import com.adminpanel.miniproject.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminController {


    private UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/dashboard")
    public String listRegisteredUsers(Authentication authentication,Model model){
        CustomerUserDetails userDetails = (CustomerUserDetails) authentication.getPrincipal();
        String name = userDetails.getName();
        List<UserDto> users = userService.findAllByRole("USER");
        model.addAttribute("users", users);
        model.addAttribute("name", name);
        return "admin/dashboard";
    }

//    @GetMapping("/dashboard/update/{id}")
//    public String showUpdateUser(@PathVariable int id, Model model){
//        UserDto existing=userService.findById(id);
//        model.addAttribute("user",existing);
//        return "admin/updateuser";
//    }
    @GetMapping("/dashboard/update/{id}")
    public String showUpdateUser(@PathVariable int id, Model model){
        UserDto existing = userService.findById(id);
        if (existing == null) {
            throw new RuntimeException("User not found");
        }
        model.addAttribute("user", existing);
        return "admin/updateuser";
    }

    @PostMapping ("/dashboard/update/{id}")
    public String updateUser(@ModelAttribute("user") UserDto user){


        userService.updateUser(user);
        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard/delete/{id}")
    public String deleteUser(@PathVariable int id,Model model){

        userService.deleteUserById(id);
        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard/adduser")
    public String showAddUser(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "admin/adduser";
    }

    @PostMapping("/dashboard/adduser/save")
    public String addUser(@Valid @ModelAttribute("user") UserDto user,
                               BindingResult result,
                               Model model){

        User existing = userService.findByEmail(user.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "admin/adduser";
        }
        userService.saveUser(user);
        return "redirect:/dashboard/adduser?success";
    }

    @GetMapping("/dashboard/search")
    public String search(@RequestParam("name")String name,Model model){
        List<UserDto> users = userService.searchUser("USER",name);
        model.addAttribute("users",users);

        return "admin/dashboard";
    }






}
