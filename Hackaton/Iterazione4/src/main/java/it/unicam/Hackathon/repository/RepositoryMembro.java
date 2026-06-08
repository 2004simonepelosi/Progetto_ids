package it.unicam.Hackathon.repository;

import it.unicam.Hackathon.entity.Membro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RepositoryMembro extends JpaRepository<Membro, Long> {
    List<Membro> findByTeamId(Long teamId);
}