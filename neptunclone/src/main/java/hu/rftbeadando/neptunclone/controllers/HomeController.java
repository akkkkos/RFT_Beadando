package hu.rftbeadando.neptunclone.controllers;

import hu.rftbeadando.neptunclone.User;
import hu.rftbeadando.neptunclone.services.hallgato.HallgatoServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class HomeController {

    private HallgatoServiceInterface hallgatoService;

    @Autowired
    public HomeController(HallgatoServiceInterface hallgatoService) {
        this.hallgatoService = hallgatoService;
    }

    @GetMapping("/")
    public String loginPage(Model model) {
        return "login";
    }

    @PostMapping("/")
    public RedirectView loginPageWithPost(@ModelAttribute("user") User user) {
        RedirectView redirectView = new RedirectView();

        if (user.getType().equals("tanar")) {
            redirectView.setUrl("/tanar");
        } else {
            Long hallgatoID = hallgatoService.getHallgatoIdByUsername(user.getUsername());
            String url = "/hallgato/" + hallgatoID.toString();
            redirectView.setUrl(url);
        }

        return redirectView;
    }
}
