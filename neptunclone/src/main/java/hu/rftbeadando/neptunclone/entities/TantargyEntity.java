package hu.rftbeadando.neptunclone.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tantargy")
@NoArgsConstructor
@Data
public class TantargyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tantargyId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "dayOfTheWeek", nullable = false)
    private String dayOfTheWeek;

    @Column(name = "startTime", nullable = false)
    private String startTime;

    @Column(name = "durationInMinutes", nullable = false)
    private int durationInMinutes;

    @Column(name = "maxHallgato", nullable = false)
    private int maxHallgato;

    @Column(name = "kredit", nullable = false)
    private int kredit;

    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "tanar_id",
            referencedColumnName = "tanarId"
    )
    private TanarEntity tanar;

    @ManyToMany(
            cascade = CascadeType.ALL
    )
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
    private List<HallgatoEntity> hallgatok;

    public void addHallgato(HallgatoEntity hallgato){
        if(hallgatok == null) hallgatok = new ArrayList<>();
        hallgatok.add(hallgato);
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
