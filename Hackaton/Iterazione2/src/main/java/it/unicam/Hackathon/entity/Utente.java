package it.unicam.Hackathon.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.unicam.Hackathon.entity.enums.RuoloUtente;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "utenti")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cognome;

    @Column(nullable = false, unique = true)
    private String email;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private RuoloUtente ruolo = RuoloUtente.UTENTE;
}