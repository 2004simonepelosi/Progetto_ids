package it.unicam.Hackathon.repository;

import it.unicam.Hackathon.entity.Invito;
import it.unicam.Hackathon.entity.enums.StatoInvito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface RepositoryInvito extends JpaRepository<Invito, Long> {
    List<Invito> findByUtenteId(Long utenteId);
    List<Invito> findByTeamId(Long teamId);
    Optional<Invito> findByTeamIdAndUtenteId(Long teamId, Long utenteId);
}