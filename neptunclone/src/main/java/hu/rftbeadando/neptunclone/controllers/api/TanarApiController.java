package hu.rftbeadando.neptunclone.controllers.api;

import hu.rftbeadando.neptunclone.entities.HallgatoEntity;
import hu.rftbeadando.neptunclone.entities.TanarEntity;
import hu.rftbeadando.neptunclone.entities.TantargyEntity;
import hu.rftbeadando.neptunclone.services.hallgato.HallgatoServiceInterface;
import hu.rftbeadando.neptunclone.services.tanar.TanarServiceInterface;
import hu.rftbeadando.neptunclone.services.tantargy.TantargyServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class TanarApiController {
    @Autowired
    private HallgatoServiceInterface hallgatoService;

    @Autowired
    private TantargyServiceInterface tantargyService;

    @Autowired
    private TanarServiceInterface tanarService;

    @GetMapping("/api/tanars")
    public Collection<TanarEntity> getAllTanars() {
        return tanarService.getAllTanar();
    }

    @DeleteMapping("/api/tanar/remove/{id}")
    public void deleteTanar(@PathVariable Long id) {
        tanarService.removeTanarById(id);
    }

    @GetMapping("/api/tantargys")
    public Collection<TantargyEntity> getAllTantargys() {
        return tantargyService.getAllTantargy();
    }

    @DeleteMapping("/api/tantargy/remove/{id}")
    public void deleteTantargy(@PathVariable Long id) {
        tantargyService.deleteByIdOnlyTantargy(id);
    }

}
