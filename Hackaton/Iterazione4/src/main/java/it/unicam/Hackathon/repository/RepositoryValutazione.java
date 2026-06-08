package it.unicam.Hackathon.repository;

import it.unicam.Hackathon.entity.Valutazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryValutazione extends JpaRepository<Valutazione, Long> {
}