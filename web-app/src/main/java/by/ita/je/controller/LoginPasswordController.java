package by.ita.je.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
@AllArgsConstructor
public class LoginPasswordController {
    @GetMapping
    public String loginPasswordForm(@RequestParam(value = "error", required = false) String error,
                                   @RequestParam(value = "logout", required = false) String logout, Model model){
        if(error != null){
            model.addAttribute("error", true);
        }
        if(logout != null){
            model.addAttribute("logout", true);
        }
        return "login.html";
    }

}
