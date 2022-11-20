package hu.rftbeadando.neptunclone.controllers;

import hu.rftbeadando.neptunclone.entities.HallgatoEntity;
import hu.rftbeadando.neptunclone.entities.TantargyEntity;
import hu.rftbeadando.neptunclone.services.hallgato.HallgatoServiceInterface;
import hu.rftbeadando.neptunclone.services.tantargy.TantargyServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;

@Controller
public class HallgatoController {

    @Autowired
    private HallgatoServiceInterface hallgatoService;

    @Autowired
    private TantargyServiceInterface tantargyService;

    @GetMapping("/hallgato/{id}")
    public String hallgatoPage(@PathVariable Long id, Model model) {
        HallgatoEntity hallgato = hallgatoService.getHallgatoById(id);
        Collection<TantargyEntity> tantargyak = tantargyService.getAllTantargyThatHasHallgato(hallgato);

        for (TantargyEntity tantargy:
             tantargyak) {
            System.out.println(tantargy);
        }

        return "hallgato";
    }
}
