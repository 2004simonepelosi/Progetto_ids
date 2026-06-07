package it.unicam.Hackathon.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teams")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "capo_gruppo_id")
    @JsonIgnore
    private Utente capoGruppo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hackathon_id")
    @JsonIgnore
    private Hackathon hackathon;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    @JsonIgnore
    @Builder.Default
    private List<Membro> membri = new ArrayList<>();

    @OneToOne(mappedBy = "team", cascade = CascadeType.ALL)
    @JsonIgnore
    private Sottomissione sottomissione;
}