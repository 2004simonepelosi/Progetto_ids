package it.unicam.Hackathon.service;

import it.unicam.Hackathon.entity.*;
import it.unicam.Hackathon.exception.BusinessException;
import it.unicam.Hackathon.exception.ResourceNotFoundException;
import it.unicam.Hackathon.repository.RepositoryTeam;
import it.unicam.Hackathon.repository.RepositoryMembro;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final RepositoryTeam repositoryTeam;
    private final RepositoryMembro repositoryMembro;

    @Transactional
    public Team salvaTeam(String nome, Utente capoGruppo, Hackathon hackathon) {
        if (repositoryTeam.existsByNomeAndHackathonId(nome, hackathon.getId()))
            throw new BusinessException("Nome team già usato in questo hackathon");
        Team t = Team.builder().nome(nome).capoGruppo(capoGruppo).hackathon(hackathon).build();
        return repositoryTeam.save(t);
    }

    @Transactional
    public Team modificaTeam(Long teamId, String nuovoNome) {
        Team t = findById(teamId);
        if (nuovoNome != null && !nuovoNome.isBlank()) t.setNome(nuovoNome);
        return repositoryTeam.save(t);
    }

    public List<Membro> getMembri(Long teamId) {
        return repositoryMembro.findByTeamId(teamId);
    }

    @Transactional
    public void rimuoviMembro(Long membroId) {
        if (!repositoryMembro.existsById(membroId))
            throw new ResourceNotFoundException("Membro non trovato: " + membroId);
        repositoryMembro.deleteById(membroId);
    }

    public Team findById(Long id) {
        return repositoryTeam.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team non trovato: " + id));
    }
}