package it.unicam.Hackathon.repository;

import it.unicam.Hackathon.entity.Erogazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RepositoryErogazione extends JpaRepository<Erogazione, Long> {
    List<Erogazione> findByHackathonId(Long hackathonId);
}