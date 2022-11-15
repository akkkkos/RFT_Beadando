package hu.rftbeadando.neptunclone.services.tantargy;

import hu.rftbeadando.neptunclone.entities.TantargyEntity;
import hu.rftbeadando.neptunclone.exceptions.UserNameAlreadyInUseException;
import hu.rftbeadando.neptunclone.exceptions.UserNotFoundException;
import hu.rftbeadando.neptunclone.repository.TantargyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class DefaultTantargyService implements TantargyServiceInterface {

    private final TantargyRepository TantargyRepository;

    @Autowired
    public DefaultTantargyService(TantargyRepository TantargyRepository) {
        this.TantargyRepository = TantargyRepository;
    }

    @Override
    public void addTantargy(TantargyEntity entity) {
        try{
            TantargyRepository.save(entity);
        } catch (Exception e){
            throw new RuntimeException("Oops, something went wrong.");
        }
    }

    @Override
    public Collection<TantargyEntity> getAllTantargy() {
        return TantargyRepository.findAll();
    }

    @Override
    public TantargyEntity getTantargyById(Long id) {
        return TantargyRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Tantargy not found with id: " + id));
    }
}
