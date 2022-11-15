package hu.rftbeadando.neptunclone.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tanar")
@NoArgsConstructor
@Data
public class TanarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tanarId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "username", nullable = false)
    private String userName;

    @Column(name = "password", nullable = false)
    private String passWord;

    public TanarEntity(String name, String userName, String passWord) {
        this.name = name;
        this.userName = userName;
        this.passWord = passWord;
    }
}
