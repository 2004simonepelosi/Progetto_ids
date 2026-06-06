package it.unicam.Hackathon.repository;

import it.unicam.Hackathon.entity.Segnalazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RepositorySegnalazione extends JpaRepository<Segnalazione, Long> {
    List<Segnalazione> findByTeamId(Long teamId);
    List<Segnalazione> findByHackathonId(Long hackathonId);
}