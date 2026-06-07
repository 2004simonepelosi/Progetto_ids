package it.unicam.Hackathon.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.unicam.Hackathon.entity.enums.StatoHackathon;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hackathon")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Hackathon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descrizione;

    private String luogo;
    private String dataIscrizione;
    private LocalDate dataInizio;
    private LocalDate dataFine;
    private int numeroMassimoPersone;
    private int numeroMinimoPersone;
    private float premio;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private StatoHackathon stato = StatoHackathon.IN_PREPARAZIONE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizzatore_id")
    @JsonIgnore
    private Utente organizzatore;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "giudice_id")
    @JsonIgnore
    private Utente giudice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vincitore_id")
    @JsonIgnore
    private Team vincitore;

    @ManyToMany
    @JoinTable(name = "hackathon_mentori",
            joinColumns = @JoinColumn(name = "hackathon_id"),
            inverseJoinColumns = @JoinColumn(name = "mentore_id"))
    @JsonIgnore
    @Builder.Default
    private List<Utente> mentori = new ArrayList<>();

    @OneToMany(mappedBy = "hackathon", cascade = CascadeType.ALL)
    @JsonIgnore
    @Builder.Default
    private List<Team> teams = new ArrayList<>();

    @OneToMany(mappedBy = "hackathon", cascade = CascadeType.ALL)
    @JsonIgnore
    @Builder.Default
    private List<Sottomissione> sottomissioni = new ArrayList<>();
}