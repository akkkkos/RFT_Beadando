package hu.rftbeadando.neptunclone.repository;

import hu.rftbeadando.neptunclone.entities.HallgatoEntity;
import hu.rftbeadando.neptunclone.entities.TantargyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Transactional
public interface TantargyRepository extends JpaRepository<TantargyEntity, Long> {
    @Query(value = "SELECT * FROM TANTARGY WHERE TANAR_ID = ?1", nativeQuery = true)
    Collection<TantargyEntity> getAllTantargyByTanarId(Long tanarId);

    @Modifying
    @Query(value = "DELETE FROM TANTARGY WHERE tantargy_Id = ?1", nativeQuery = true)
    void deleteByIdOnlyTantargy(Long id);

    @Query(value = "SELECT * FROM TANTARGY WHERE TANTARGY_ID IN (SELECT TANTARGY_ID FROM HALLGATO_TANTARGY_MAP WHERE HALLGATO_ID = ?1)", nativeQuery = true)
    Collection<TantargyEntity> getAllTantargyThatHasHallgatoId(Long id);

    @Query(value = "SELECT * FROM TANTARGY WHERE TANTARGY_ID NOT IN (SELECT TANTARGY_ID FROM HALLGATO_TANTARGY_MAP WHERE HALLGATO_ID = ?1)", nativeQuery = true)
    Collection<TantargyEntity> getAllTantargyThatDoesNotHaveHallgatoId(Long id);
}
