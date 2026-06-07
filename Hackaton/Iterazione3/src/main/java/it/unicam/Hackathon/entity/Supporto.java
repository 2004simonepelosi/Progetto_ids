package it.unicam.Hackathon.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "supporti")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Supporto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
}