package hu.rftbeadando.neptunclone.controllers;

import hu.rftbeadando.neptunclone.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {


    @GetMapping("/")
    public String indexFuggveny(Model model) {
        return "index";
    }

    @PostMapping("/login")
    public String loginFuggveny(@ModelAttribute("user") User user) {
        if(user.getType().equals("hallgato")){
            return "hallgato";
        }else {
           return "tanar";
        }
    }
}
