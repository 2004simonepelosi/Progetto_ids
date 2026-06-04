package it.unicam.Hackathon.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sottomissioni")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Sottomissione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "team_id")
    @JsonIgnore
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hackathon_id")
    @JsonIgnore
    private Hackathon hackathon;

    @Column(columnDefinition = "TEXT")
    private String contenuto;

    @OneToOne(mappedBy = "sottomissione", cascade = CascadeType.ALL)
    @JsonIgnore
    private Valutazione valutazione;
}