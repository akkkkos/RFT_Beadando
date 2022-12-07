package hu.rftbeadando.neptunclone.services.hallgato;

import hu.rftbeadando.neptunclone.exceptions.UserNameAlreadyInUseException;
import hu.rftbeadando.neptunclone.exceptions.UserNotFoundException;
import hu.rftbeadando.neptunclone.entities.HallgatoEntity;
import hu.rftbeadando.neptunclone.repository.HallgatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class DefaultHallgatoService implements HallgatoServiceInterface{

    private final HallgatoRepository hallgatoRepository;

    @Autowired
    public DefaultHallgatoService(HallgatoRepository hallgatoRepository) {
        this.hallgatoRepository = hallgatoRepository;
    }

    @Override
    public void addHallgato(HallgatoEntity entity) {
        try{
            hallgatoRepository.save(entity);
        }catch (Exception e){
            throw new RuntimeException("Oops, something went wrong.");
        }
    }

    @Override
    public Collection<HallgatoEntity> getAllHallgato() {
        return hallgatoRepository.findAll();
    }

    @Override
    public HallgatoEntity getHallgatoById(Long id) {
        return hallgatoRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    @Override
    public Long getHallgatoIdByUsername(String userName) {
        return hallgatoRepository.findIdByUserName(userName);
    }

    @Override
    public boolean existsHallgatoByUserName(String userName) {
        return hallgatoRepository.existsHallgatoByUserName(userName);
    }

    @Override
    public void removeHallgatoById(Long id) {
        hallgatoRepository.deleteById(id);
    }
}
