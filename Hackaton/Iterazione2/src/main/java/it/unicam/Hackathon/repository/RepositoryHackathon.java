package it.unicam.Hackathon.repository;

import it.unicam.Hackathon.entity.Hackathon;
import it.unicam.Hackathon.entity.enums.StatoHackathon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RepositoryHackathon extends JpaRepository<Hackathon, Long> {
    List<Hackathon> findByStato(StatoHackathon stato);
    List<Hackathon> findByOrganizzatoreId(Long organizzatoreId);
}