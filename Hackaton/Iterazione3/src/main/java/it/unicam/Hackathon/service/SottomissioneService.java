package it.unicam.Hackathon.service;

import it.unicam.Hackathon.entity.*;
import it.unicam.Hackathon.exception.ResourceNotFoundException;
import it.unicam.Hackathon.repository.RepositorySottomissione;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SottomissioneService {

    private final RepositorySottomissione repositorySottomissione;

    @Transactional
    public Sottomissione salva(String contenuto, Team team, Hackathon hackathon) {
        Sottomissione s = repositorySottomissione.findByTeamId(team.getId())
                .orElse(Sottomissione.builder().team(team).hackathon(hackathon).build());
        s.setContenuto(contenuto);
        return repositorySottomissione.save(s);
    }

    @Transactional
    public void elimina(Long id) {
        if (!repositorySottomissione.existsById(id))
            throw new ResourceNotFoundException("Sottomissione non trovata: " + id);
        repositorySottomissione.deleteById(id);
    }

    public Optional<Sottomissione> findByTeamId(Long teamId) {
        return repositorySottomissione.findByTeamId(teamId);
    }

    public List<Sottomissione> findByHackathon(Long hackathonId) {
        return repositorySottomissione.findByHackathonId(hackathonId);
    }

    public Sottomissione findById(Long id) {
        return repositorySottomissione.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sottomissione non trovata: " + id));
    }
}