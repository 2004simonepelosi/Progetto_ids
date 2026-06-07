package it.unicam.Hackathon.repository;

import it.unicam.Hackathon.entity.Sottomissione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface RepositorySottomissione extends JpaRepository<Sottomissione, Long> {
    List<Sottomissione> findByHackathonId(Long hackathonId);
    Optional<Sottomissione> findByTeamId(Long teamId);
}