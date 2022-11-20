package hu.rftbeadando.neptunclone.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tantargy")
@NoArgsConstructor
public class TantargyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long tantargyId;

    @Column(name = "name", nullable = false)
    public String name;

    @Column(name = "dayOfTheWeek", nullable = false)
    public String dayOfTheWeek;

    @Column(name = "startTime", nullable = false)
    public String startTime;

    @Column(name = "durationInMinutes", nullable = false)
    public int durationInMinutes;

    @Column(name = "maxHallgato", nullable = false)
    public int maxHallgato;

    @Column(name = "kredit", nullable = false)
    public int kredit;

    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "tanar_id",
            referencedColumnName = "tanarId"
    )
    public TanarEntity tanar;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "HallgatoTantargyMap",
            joinColumns = @JoinColumn(
                    name = "tantargy_id",
                    referencedColumnName = "tantargyId"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "hallgato_id",
                    referencedColumnName = "hallgatoId"
            )
    )
    private Set<HallgatoEntity> hallgatok = new HashSet<>();;

    public void addHallgato(HallgatoEntity hallgato) {
        this.hallgatok.add(hallgato);
        hallgato.getTantargyak().add(this);
    }

    public void removeHallgato(long hallgatoId) {
        HallgatoEntity hallgato = this.hallgatok.stream().filter(t -> t.hallgatoId == hallgatoId).findFirst().orElse(null);
        if (hallgato != null) {
            this.hallgatok.remove(hallgato);
            hallgato.getTantargyak().remove(this);
        }
    }


    public TantargyEntity(String name, String dayOfTheWeek, String startTime, int durationInMinutes, int maxHallgato, int kredit, TanarEntity tanar) {
        this.name = name;
        this.dayOfTheWeek = dayOfTheWeek;
        this.startTime = startTime;
        this.durationInMinutes = durationInMinutes;
        this.maxHallgato = maxHallgato;
        this.kredit = kredit;
        this.tanar = tanar;
    }
}
