package hu.rftbeadando.neptunclone.services.tantargy;

import hu.rftbeadando.neptunclone.entities.HallgatoEntity;
import hu.rftbeadando.neptunclone.entities.TantargyEntity;

import java.util.Collection;

public interface TantargyServiceInterface {
    void addTantargy(TantargyEntity request);
    Collection<TantargyEntity> getAllTantargy();
    TantargyEntity getTantargyByTantargyId(Long id);
    Collection<TantargyEntity> getAllTantargyByTanarId(Long tanarId);
    void deleteByIdOnlyTantargy(Long id);
    Collection<TantargyEntity> getAllTantargyThatHasHallgatoId(Long id);
    Collection<TantargyEntity> getAllTantargyThatDoesNotHaveHallgatoId(Long id);
}
