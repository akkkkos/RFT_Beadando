package hu.rftbeadando.neptunclone.controllers;

import hu.rftbeadando.neptunclone.services.hallgato.HallgatoServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HallgatoController {

    @Autowired
    private HallgatoServiceInterface hallgatoService;

    @GetMapping("/hallgato/{id}")
    public String hallgatoPage(@PathVariable Long id, Model model) {
        return "hallgato";
    }
}
