package it.unicam.Hackathon.repository;

import it.unicam.Hackathon.entity.Supporto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RepositorySupporto extends JpaRepository<Supporto, Long> {
    List<Supporto> findByHackathonId(Long hackathonId);
    List<Supporto> findByTeamId(Long teamId);
}