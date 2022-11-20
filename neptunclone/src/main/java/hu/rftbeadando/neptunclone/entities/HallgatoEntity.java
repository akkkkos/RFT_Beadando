package hu.rftbeadando.neptunclone.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "hallgato")
@NoArgsConstructor
public class HallgatoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long hallgatoId;

    @Column(name = "name", nullable = false)
    public String name;

    @Column(name = "username", nullable = false)
    public String userName;

    public HallgatoEntity(String name, String userName) {
        this.name = name;
        this.userName = userName;
    }

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "hallgatok")
    @JsonIgnore
    private Set<TantargyEntity> tantargyak = new HashSet<>();

    public Set<TantargyEntity> getTantargyak() {
        return tantargyak;
    }

    public void setTantargyak(Set<TantargyEntity> tantargyak) {
        this.tantargyak = tantargyak;
    }
}
