package it.unicam.Hackathon.service;

import it.unicam.Hackathon.entity.*;
import it.unicam.Hackathon.entity.enums.RuoloUtente;
import it.unicam.Hackathon.exception.BusinessException;
import it.unicam.Hackathon.exception.ResourceNotFoundException;
import it.unicam.Hackathon.repository.RepositoryValutazione;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ValutazioneService {

    private final RepositoryValutazione repositoryValutazione;

    @Transactional
    public Valutazione salvaValutazione(Sottomissione sottomissione, Utente giudice, int voto, String testo) {
        if (giudice.getRuolo() != RuoloUtente.GIUDICE)
            throw new BusinessException("Solo il Giudice può valutare le sottomissioni!");
        if (!sottomissione.getHackathon().getGiudice().getId().equals(giudice.getId()))
            throw new BusinessException("Sei il giudice di un altro hackathon!");
        if (sottomissione.getValutazione() != null)
            throw new BusinessException("Sottomissione già valutata!");
        if (voto < 0 || voto > 10)
            throw new BusinessException("Il voto deve essere tra 0 e 10!");
        Valutazione v = Valutazione.builder()
                .sottomissione(sottomissione).giudice(giudice).voto(voto).testo(testo)
                .build();
        return repositoryValutazione.save(v);
    }

    public Valutazione findById(Long id) {
        return repositoryValutazione.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Valutazione non trovata: " + id));
    }

    @Transactional
    public Valutazione modificaValutazione(Long valutazioneId, Utente giudice, int voto, String testo) {
        Valutazione v = findById(valutazioneId);
        if (!v.getGiudice().getId().equals(giudice.getId()))
            throw new BusinessException("Solo il giudice che ha valutato può modificare!");
        if (voto < 0 || voto > 10)
            throw new BusinessException("Il voto deve essere tra 0 e 10!");
        v.setVoto(voto);
        v.setTesto(testo);
        return repositoryValutazione.save(v);
    }
}