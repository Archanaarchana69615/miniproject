package com.adminpanel.miniproject.Controller;

import com.adminpanel.miniproject.security.CustomerUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/home")
    public String home(Authentication authentication, Model model) {

        CustomerUserDetails userDetails = (CustomerUserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        String name = userDetails.getName();
        String gender = userDetails.getGender();
        String phoneNumber = userDetails.getPhoneNumber();


        model.addAttribute("Email", username);
        model.addAttribute("Name", name);
        model.addAttribute("Gender", gender);
        model.addAttribute("PhoneNumber", phoneNumber);


        return "user/home";
    }



}
