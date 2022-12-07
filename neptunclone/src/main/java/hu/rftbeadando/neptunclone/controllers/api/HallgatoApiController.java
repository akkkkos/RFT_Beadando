package hu.rftbeadando.neptunclone.controllers.api;

import hu.rftbeadando.neptunclone.entities.HallgatoEntity;
import hu.rftbeadando.neptunclone.services.hallgato.HallgatoServiceInterface;
import hu.rftbeadando.neptunclone.services.tantargy.TantargyServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class HallgatoApiController {

    @Autowired
    private HallgatoServiceInterface hallgatoService;

    @Autowired
    private TantargyServiceInterface tantargyService;

    @GetMapping("/api/hallgatos")
    public Collection<HallgatoEntity> getAllHallgatos() {
        Collection<HallgatoEntity> hallgatoEntities = hallgatoService.getAllHallgato();
        return hallgatoEntities;
    }

    @DeleteMapping("/api/hallgato/remove/{id}")
    public void deleteHallgato(@PathVariable Long id) {
        hallgatoService.removeHallgatoById(id);
    }
}
