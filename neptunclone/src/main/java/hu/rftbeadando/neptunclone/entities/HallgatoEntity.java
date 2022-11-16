package hu.rftbeadando.neptunclone.entities;

import lombok.NoArgsConstructor;

import javax.persistence.*;

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
}
