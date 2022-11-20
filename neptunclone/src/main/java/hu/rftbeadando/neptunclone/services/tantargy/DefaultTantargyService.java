package hu.rftbeadando.neptunclone.services.tantargy;

import hu.rftbeadando.neptunclone.entities.HallgatoEntity;
import hu.rftbeadando.neptunclone.entities.TantargyEntity;
import hu.rftbeadando.neptunclone.exceptions.UserNotFoundException;
import hu.rftbeadando.neptunclone.repository.TantargyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class DefaultTantargyService implements TantargyServiceInterface {

    private final TantargyRepository tantargyRepository;

    @Autowired
    public DefaultTantargyService(TantargyRepository TantargyRepository) {
        this.tantargyRepository = TantargyRepository;
    }

    @Override
    public void addTantargy(TantargyEntity entity) {
        try{
            tantargyRepository.save(entity);
        } catch (Exception e){
            throw new RuntimeException("Oops, something went wrong.");
        }
    }

    @Override
    public Collection<TantargyEntity> getAllTantargy() {
        return tantargyRepository.findAll();
    }

    @Override
    public TantargyEntity getTantargyById(Long id) {
        return tantargyRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Tantargy not found with id: " + id));
    }

    @Override
    public Collection<TantargyEntity> getAllTantargyByTanarId(Long tanarId) {
        return tantargyRepository.getAllTantargyByTanarId(tanarId);
    }

    @Override
    public void deleteByIdOnlyTantargy(Long id) {
        tantargyRepository.deleteByIdOnlyTantargy(id);
    }

    @Override
    public Collection<TantargyEntity> getAllTantargyThatHasHallgato(HallgatoEntity hallgatoEntity) {
        return tantargyRepository.getAllTantargyThatHasHallgato(hallgatoEntity);
    }
}
