package hu.rftbeadando.neptunclone.controllers.web;

import hu.rftbeadando.neptunclone.entities.HallgatoEntity;
import hu.rftbeadando.neptunclone.entities.TantargyEntity;
import hu.rftbeadando.neptunclone.services.hallgato.HallgatoServiceInterface;
import hu.rftbeadando.neptunclone.services.tantargy.TantargyServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;

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
        Collection<TantargyEntity> tantargyak = tantargyService.getAllTantargyThatHasHallgatoId(hallgato.hallgatoId);

        model.addAttribute("hallgato", hallgato);
        model.addAttribute("tantargyak", tantargyak);

        return "hallgato";
    }

    @GetMapping("/hallgato/{id}/enrollTantargy")
    public String enrollTantargyPage(@PathVariable Long id, Model model) {
        Collection<TantargyEntity> allTantargy = tantargyService.getAllTantargyThatDoesNotHaveHallgatoId(id);

        model.addAttribute("id", id);
        model.addAttribute("tantargyak", allTantargy);
        return "enrollTantargy";
    }

    @GetMapping("/hallgato/{id}/enrollTantargy/{tantargyId}")
    public RedirectView enrollTantargy(@PathVariable Long id, @PathVariable Long tantargyId) {
        HallgatoEntity hallgato = hallgatoService.getHallgatoById(id);
        TantargyEntity tantargy = tantargyService.getTantargyByTantargyId(tantargyId);
        tantargy.addHallgato(hallgato);
        tantargyService.addTantargy(tantargy);

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/hallgato/{id}/enrollTantargy");
        return redirectView;
    }

    @GetMapping("/hallgato/{id}/leaveTantargy/{tantargyId}")
    public RedirectView leaveTantargy(@PathVariable Long id, @PathVariable Long tantargyId) {
        TantargyEntity tantargy = tantargyService.getTantargyByTantargyId(tantargyId);
        tantargy.removeHallgato(id);
        tantargyService.addTantargy(tantargy);

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/hallgato/{id}");
        return redirectView;
    }
}
