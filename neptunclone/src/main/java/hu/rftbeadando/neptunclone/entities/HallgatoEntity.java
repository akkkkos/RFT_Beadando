package hu.rftbeadando.neptunclone.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "hallgato")
@NoArgsConstructor
@Data
public class HallgatoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long hallgatoId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "username", nullable = false)
    private String userName;

    @Column(name = "password", nullable = true)
    private String passWord;

    public HallgatoEntity(String name, String userName, String passWord) {
        this.name = name;
        this.userName = userName;
        this.passWord = passWord;
    }
}
