package hu.rftbeadando.neptunclone.services.tantargy;

import hu.rftbeadando.neptunclone.entities.TantargyEntity;

import java.util.Collection;

public interface TantargyServiceInterface {
    void addTantargy(TantargyEntity request);
    Collection<TantargyEntity> getAllTantargy();
    TantargyEntity getTantargyById(Long id);
}
