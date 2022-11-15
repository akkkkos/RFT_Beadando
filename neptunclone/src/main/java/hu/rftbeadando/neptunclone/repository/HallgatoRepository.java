package hu.rftbeadando.neptunclone.repository;

import hu.rftbeadando.neptunclone.model.HallgatoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HallgatoRepository extends JpaRepository<HallgatoModel, Long> {
    @Query(value = "SELECT ID FROM HALLGATO WHERE USERNAME = ?1", nativeQuery = true)
    Long findIdByUserName(String userName);
}
