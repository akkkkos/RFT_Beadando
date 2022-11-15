package hu.rftbeadando.neptunclone.services.hallgato;

import hu.rftbeadando.neptunclone.model.HallgatoModel;

import java.util.Collection;

public interface HallgatoServiceInterface {
    void addHallgato(HallgatoModel request);
    Collection<HallgatoModel> getAllHallgato();
    HallgatoModel getHallgatoById(Long id);
    Long getHallgatoIdByUsername(String userName);
}
