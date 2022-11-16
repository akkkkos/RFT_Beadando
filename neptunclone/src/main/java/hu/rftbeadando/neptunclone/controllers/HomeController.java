package hu.rftbeadando.neptunclone.controllers;

import hu.rftbeadando.neptunclone.entities.HallgatoEntity;
import hu.rftbeadando.neptunclone.entities.TanarEntity;
import hu.rftbeadando.neptunclone.formmodels.LoginFormValues;
import hu.rftbeadando.neptunclone.formmodels.RegisterFormValues;
import hu.rftbeadando.neptunclone.services.hallgato.HallgatoServiceInterface;
import hu.rftbeadando.neptunclone.services.tanar.TanarServiceInterface;
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
    @Autowired
    private HallgatoServiceInterface hallgatoService;
    @Autowired
    private TanarServiceInterface tanarService;

    @GetMapping("/")
    public String loginPage(@RequestParam(name = "badLogin", required = false, defaultValue = "0") int badLogin, Model model) {
        model.addAttribute("badLogin", badLogin);
        return "login";
    }

    @PostMapping("/")
    public RedirectView loginPageWithPost(@ModelAttribute("user") LoginFormValues loginFormValues) {
        RedirectView redirectView = new RedirectView();

        if (loginFormValues.getType().equals("tanar")) {
            Long tanarID = tanarService.getTanarIdByUsername(loginFormValues.getUsername());
            if (tanarID == null) {
                redirectView.setUrl("/?badLogin=1");
            } else {
                String url = "/tanar/" + tanarID.toString();
                redirectView.setUrl(url);
            }
        } else {
            Long hallgatoID = hallgatoService.getHallgatoIdByUsername(loginFormValues.getUsername());
            if (hallgatoID == null) {
                redirectView.setUrl("/?badLogin=1");
            } else {
                String url = "/hallgato/" + hallgatoID.toString();
                redirectView.setUrl(url);
            }
        }

        return redirectView;
    }

    @GetMapping("/register")
    public String register(@RequestParam(name = "missing", required = false, defaultValue = "0") int missing,
                           @RequestParam(name = "exists", required = false, defaultValue = "0") int exists,
                           Model model) {
        model.addAttribute("exists", exists);
        model.addAttribute("missing", missing);
        return "register";
    }

    @PostMapping("/register")
    public RedirectView registerUserToDb(@ModelAttribute("user") RegisterFormValues registerFormValues) {
        RedirectView redirectView = new RedirectView();

        if (registerFormValues.getName().equals("") || registerFormValues.getUsername().equals("")) {
            redirectView.setUrl("/register?missing=1");
        } else {
            if (registerFormValues.getType().equals("tanar")) {
                if (!tanarService.existsTanarByUserName(registerFormValues.getUsername())) {
                    TanarEntity tanar = new TanarEntity(
                            registerFormValues.getName(),
                            registerFormValues.getUsername()
                    );

                    tanarService.addTanar(tanar);
                    redirectView.setUrl("/?registerSuccess=1");
                } else {
                    redirectView.setUrl("/register?exists=1");
                }

            } else {
                if (!hallgatoService.existsHallgatoByUserName(registerFormValues.getUsername())) {
                    HallgatoEntity hallgato = new HallgatoEntity(
                            registerFormValues.getName(),
                            registerFormValues.getUsername()
                    );

                    hallgatoService.addHallgato(hallgato);
                    redirectView.setUrl("/?registerSuccess=1");
                } else {
                    redirectView.setUrl("/register?exists=1");
                }
            }
        }

        return redirectView;
    }
}
