package it.unicam.Hackathon.service;

import it.unicam.Hackathon.entity.*;
import it.unicam.Hackathon.exception.ResourceNotFoundException;
import it.unicam.Hackathon.repository.RepositoryCallSupporto;
import it.unicam.Hackathon.repository.RepositorySupporto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SupportoService {

    private final RepositorySupporto repositorySupporto;
    private final RepositoryCallSupporto repositoryCallSupporto;

    @Transactional
    public Supporto creaRichiesta(Team team, Hackathon hackathon, String descrizione) {
        Supporto s = Supporto.builder()
                .team(team).hackathon(hackathon).descrizione(descrizione).build();
        return repositorySupporto.save(s);
    }

    public List<Supporto> getRichieste(Long hackathonId) {
        return repositorySupporto.findByHackathonId(hackathonId);
    }

    public Supporto findById(Long id) {
        return repositorySupporto.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Richiesta supporto non trovata: " + id));
    }

    @Transactional
    public CallSupporto creaCallSupporto(Utente mentore, Supporto supporto,
                                         String data, String ora, int durata, String note) {
        CallSupporto call = CallSupporto.builder()
                .mentore(mentore).supporto(supporto)
                .data(LocalDateTime.now()).ora(ora)
                .durata(durata).note(note)
                .stato("PROPOSTA")
                .build();
        return repositoryCallSupporto.save(call);
    }

    @Transactional
    public CallSupporto prenotaSlot(Long callId, String slotConfermato) {
        CallSupporto call = repositoryCallSupporto.findById(callId)
                .orElseThrow(() -> new ResourceNotFoundException("Call non trovata: " + callId));
        call.setOra(slotConfermato);
        call.setStato("PRENOTATA");
        return repositoryCallSupporto.save(call);
    }
}