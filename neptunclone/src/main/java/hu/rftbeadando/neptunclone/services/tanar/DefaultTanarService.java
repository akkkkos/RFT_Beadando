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

    private final TanarRepository TanarRepository;

    @Autowired
    public DefaultTanarService(TanarRepository TanarRepository) {
        this.TanarRepository = TanarRepository;
    }

    @Override
    public void addTanar(TanarEntity entity) {
        try{
            TanarRepository.save(entity);
        }catch (DataIntegrityViolationException e){
            throw new UserNameAlreadyInUseException("Username: "+ entity.getUserName() + " already in use.");
        }catch (Exception e){
            throw new RuntimeException("Oops, something went wrong.");
        }
    }

    @Override
    public Collection<TanarEntity> getAllTanar() {
        return TanarRepository.findAll();
    }

    @Override
    public TanarEntity getTanarById(Long id) {
        return TanarRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    @Override
    public Long getTanarIdByUsername(String userName) {
        return TanarRepository.findIdByUserName(userName);
    }
}
