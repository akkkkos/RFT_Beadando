package hu.rftbeadando.neptunclone.repository;

import hu.rftbeadando.neptunclone.entities.TanarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TanarRepository extends JpaRepository<TanarEntity, Long> {
    @Query(value = "SELECT TANAR_ID FROM TANAR WHERE USERNAME = ?1", nativeQuery = true)
    Long findIdByUserName(String userName);
}
