package it.unicam.Hackathon.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "valutazioni")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Valutazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int voto;

    @Column(columnDefinition = "TEXT")
    private String testo;

    @OneToOne
    @JoinColumn(name = "sottomissione_id")
    @JsonIgnore
    private Sottomissione sottomissione;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "giudice_id")
    @JsonIgnore
    private Utente giudice;
}