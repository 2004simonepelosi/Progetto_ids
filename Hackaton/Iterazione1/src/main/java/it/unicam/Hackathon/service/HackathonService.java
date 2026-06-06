package it.unicam.Hackathon.service;

import it.unicam.Hackathon.dto.CreaHackathonRequest;
import it.unicam.Hackathon.entity.*;
import it.unicam.Hackathon.entity.enums.StatoHackathon;
import it.unicam.Hackathon.exception.ResourceNotFoundException;
import it.unicam.Hackathon.repository.RepositoryHackathon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HackathonService {

    private final RepositoryHackathon repositoryHackathon;

    @Transactional
    public Hackathon creaHackathon(CreaHackathonRequest dto, Utente organizzatore) {
        Hackathon h = Hackathon.builder()
                .nome(dto.getNome()).descrizione(dto.getDescrizione())
                .luogo(dto.getLuogo()).dataInizio(dto.getDataInizio())
                .dataFine(dto.getDataFine()).dataIscrizione(dto.getDataIscrizione())
                .numeroMassimoPersone(dto.getNumeroMassimoPersone())
                .numeroMinimoPersone(dto.getNumeroMinimoPersone())
                .premio(dto.getPremio()).organizzatore(organizzatore)
                .stato(StatoHackathon.IN_PREPARAZIONE)
                .build();
        return repositoryHackathon.save(h);
    }

    @Transactional
    public Hackathon aggiornaGiudice(Long hackathonId, Utente giudice) {
        Hackathon h = findById(hackathonId);
        h.setGiudice(giudice);
        return repositoryHackathon.save(h);
    }

    @Transactional
    public Hackathon aggiornaMentori(Long hackathonId, Utente mentore) {
        Hackathon h = findById(hackathonId);
        h.getMentori().add(mentore);
        return repositoryHackathon.save(h);
    }

    @Transactional
    public Hackathon aggiornaStatoHackathon(Long hackathonId, Team vincitore) {
        Hackathon h = findById(hackathonId);
        h.setVincitore(vincitore);
        h.setStato(StatoHackathon.CONCLUSO);
        return repositoryHackathon.save(h);
    }

    public Hackathon findById(Long id) {
        return repositoryHackathon.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hackathon non trovato: " + id));
    }

    public List<Hackathon> findAll() {
        return repositoryHackathon.findAll();
    }
}
