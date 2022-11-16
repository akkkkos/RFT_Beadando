package hu.rftbeadando.neptunclone.services.tanar;

import hu.rftbeadando.neptunclone.entities.TanarEntity;
import hu.rftbeadando.neptunclone.exceptions.UserNameAlreadyInUseException;
import hu.rftbeadando.neptunclone.exceptions.UserNotFoundException;
import hu.rftbeadando.neptunclone.repository.TanarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class DefaultTanarService implements TanarServiceInterface {

    private final TanarRepository tanarRepository;

    @Autowired
    public DefaultTanarService(TanarRepository TanarRepository) {
        this.tanarRepository = TanarRepository;
    }

    @Override
    public void addTanar(TanarEntity entity) {
        try{
            tanarRepository.save(entity);
        }catch (Exception e){
            throw new RuntimeException("Oops, something went wrong.");
        }
    }

    @Override
    public Collection<TanarEntity> getAllTanar() {
        return tanarRepository.findAll();
    }

    @Override
    public TanarEntity getTanarById(Long id) {
        return tanarRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    @Override
    public Long getTanarIdByUsername(String userName) {
        return tanarRepository.findIdByUserName(userName);
    }

    @Override
    public boolean existsTanarByUserName(String userName) {
        return tanarRepository.existsTanarByUserName(userName);
    }
}
