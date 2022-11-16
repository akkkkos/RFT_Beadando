package hu.rftbeadando.neptunclone.services.hallgato;

import hu.rftbeadando.neptunclone.entities.HallgatoEntity;

import java.util.Collection;

public interface HallgatoServiceInterface {
    void addHallgato(HallgatoEntity request);
    Collection<HallgatoEntity> getAllHallgato();
    HallgatoEntity getHallgatoById(Long id);
    Long getHallgatoIdByUsername(String userName);
    boolean existsHallgatoByUserName(String userName);
}
