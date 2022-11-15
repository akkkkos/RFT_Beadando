package hu.rftbeadando.neptunclone.repository;

import hu.rftbeadando.neptunclone.entities.TantargyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface TantargyRepository extends JpaRepository<TantargyEntity, Long> {
    @Query(value = "SELECT * FROM TANTARGY WHERE TANAR_ID = ?1", nativeQuery = true)
    Collection<TantargyEntity> getAllTantargyByTanarId(Long tanarId);
}
