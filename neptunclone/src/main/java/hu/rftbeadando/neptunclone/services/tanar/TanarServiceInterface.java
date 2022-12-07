package hu.rftbeadando.neptunclone.services.tanar;

import java.util.Collection;

import hu.rftbeadando.neptunclone.entities.TanarEntity;

public interface TanarServiceInterface {
    void addTanar(TanarEntity request);
    Collection<TanarEntity> getAllTanar();
    TanarEntity getTanarById(Long id);
    Long getTanarIdByUsername(String userName);
    boolean existsTanarByUserName(String userName);
    void removeTanarById(Long id);
}
