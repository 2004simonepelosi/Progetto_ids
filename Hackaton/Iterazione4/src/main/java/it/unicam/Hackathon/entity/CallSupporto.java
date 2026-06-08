package it.unicam.Hackathon.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "call_supporto")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CallSupporto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentore_id")
    @JsonIgnore
    private Utente mentore;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supporto_id")
    @JsonIgnore
    private Supporto supporto;

    private LocalDateTime data;
    private String ora;
    private int durata;
    private String note;
    private String stato;
}