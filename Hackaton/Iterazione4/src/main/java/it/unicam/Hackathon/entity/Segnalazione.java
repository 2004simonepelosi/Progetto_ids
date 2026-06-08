package it.unicam.Hackathon.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "segnalazioni")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Segnalazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentore_id")
    @JsonIgnore
    private Utente mentore;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizzatore_id")
    @JsonIgnore
    private Utente organizzatore;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false)
    @JsonIgnore
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hackathon_id", nullable = false)
    @JsonIgnore
    private Hackathon hackathon;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String descrizione;

    private String sanzione;
}