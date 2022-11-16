package hu.rftbeadando.neptunclone.repository;

import hu.rftbeadando.neptunclone.entities.HallgatoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HallgatoRepository extends JpaRepository<HallgatoEntity, Long> {
    @Query(value = "SELECT HALLGATO_ID FROM HALLGATO WHERE USERNAME = ?1", nativeQuery = true)
    Long findIdByUserName(String userName);

    boolean existsHallgatoByUserName(String userName);
}
