package it.unicam.Hackathon.repository;

import it.unicam.Hackathon.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RepositoryTeam extends JpaRepository<Team, Long> {
    List<Team> findByHackathonId(Long hackathonId);
    boolean existsByNomeAndHackathonId(String nome, Long hackathonId);
}
