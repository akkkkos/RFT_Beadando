package hu.rftbeadando.neptunclone.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tanar")
@NoArgsConstructor
public class TanarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long tanarId;

    @Column(name = "name", nullable = false)
    public String name;

    @Column(name = "username", nullable = false)
    public String userName;


    public TanarEntity(String name, String userName) {
        this.name = name;
        this.userName = userName;
    }
}
