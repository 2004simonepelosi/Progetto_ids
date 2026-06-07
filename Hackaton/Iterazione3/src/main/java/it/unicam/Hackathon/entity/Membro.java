package it.unicam.Hackathon.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "membri")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Membro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utente_id")
    @JsonIgnore
    private Utente utente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    @JsonIgnore
    private Team team;

    public String getNome() {
        return utente != null ? utente.getNome() : null;
    }

    public String getCognome() {
        return utente != null ? utente.getCognome() : null;
    }

    public String getEmail() {
        return utente != null ? utente.getEmail() : null;
    }

    public String getRuolo() {
        return utente != null ? utente.getRuolo().name() : null;
    }
}