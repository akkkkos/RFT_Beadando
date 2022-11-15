package hu.rftbeadando.neptunclone.controllers;

import hu.rftbeadando.neptunclone.entities.TanarEntity;
import hu.rftbeadando.neptunclone.entities.TantargyEntity;
import hu.rftbeadando.neptunclone.formmodels.LoginFormValues;
import hu.rftbeadando.neptunclone.formmodels.TantargyFormValues;
import hu.rftbeadando.neptunclone.services.tanar.TanarServiceInterface;
import hu.rftbeadando.neptunclone.services.tantargy.TantargyServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Collection;

@Controller
public class TanarController {

    @Autowired
    private TanarServiceInterface tanarService;
    @Autowired
    private TantargyServiceInterface tantargyService;

    @GetMapping("/tanar/{id}")
    public String tanarPage(@PathVariable Long id, Model model) {
        Collection<TantargyEntity> tantargyak = tantargyService.getAllTantargyByTanarId(id);
        TanarEntity tanarEntity = tanarService.getTanarById(id);

        model.addAttribute("tantargyak", tantargyak.toArray());
        model.addAttribute("id", id);
        model.addAttribute("tanar", tanarEntity);

        return "tanar";
    }

    @GetMapping("/tanar/{id}/addTantargy")
    public String addTantargyPage(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        return "addTantargy";
    }

    @PostMapping("/tanar/{id}/addTantargy")
    public RedirectView addTantargyToDb(@ModelAttribute("tantargy") TantargyFormValues tantargyFormValues, @PathVariable Long id) {

        TantargyEntity tantargy = new TantargyEntity(
                tantargyFormValues.getName(),
                tantargyFormValues.getDayOfTheWeek(),
                tantargyFormValues.getStartTime(),
                tantargyFormValues.getDurationInMinutes(),
                tantargyFormValues.getMaxHallgato(),
                tantargyFormValues.getKredit(),
                tanarService.getTanarById(id)
        );

        tantargyService.addTantargy(tantargy);

        return new RedirectView("/tanar/" + id + "");
    }

}
