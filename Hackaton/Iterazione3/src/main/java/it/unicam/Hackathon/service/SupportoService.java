package it.unicam.Hackathon.service;

import it.unicam.Hackathon.entity.*;
import it.unicam.Hackathon.repository.RepositorySupporto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SupportoService {

    private final RepositorySupporto repositorySupporto;

    @Transactional
    public Supporto creaRichiesta(Team team, Hackathon hackathon, String descrizione) {
        Supporto s = Supporto.builder()
                .team(team).hackathon(hackathon).descrizione(descrizione)
                .build();
        return repositorySupporto.save(s);
    }

    public List<Supporto> getRichieste(Long hackathonId) {
        return repositorySupporto.findByHackathonId(hackathonId);
    }
}