package it.unicam.Hackathon.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "erogazioni")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Erogazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hackathon_id")
    @JsonIgnore
    private Hackathon hackathon;

    private float importo;
    private String transazioneId;
    private String stato;
}