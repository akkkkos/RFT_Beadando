package hu.rftbeadando.neptunclone.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TanarController {
    @GetMapping("/tanar")
    public String tanarPage(Model model) {
        return "tanar";
    }
}
