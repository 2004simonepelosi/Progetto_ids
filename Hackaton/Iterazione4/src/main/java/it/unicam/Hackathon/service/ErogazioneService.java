package it.unicam.Hackathon.service;

import it.unicam.Hackathon.entity.*;
import it.unicam.Hackathon.entity.enums.StatoHackathon;
import it.unicam.Hackathon.exception.BusinessException;
import it.unicam.Hackathon.repository.RepositoryErogazione;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ErogazioneService {

    private final RepositoryErogazione repositoryErogazione;

    public String getRiepilogoPremio(Hackathon hackathon) {
        if (hackathon.getStato() != StatoHackathon.CONCLUSO)
            throw new BusinessException("L'hackathon non è ancora concluso!");
        if (hackathon.getVincitore() == null)
            throw new BusinessException("Nessun vincitore proclamato!");
        return "Team: " + hackathon.getVincitore().getNome() +
                " | Premio: €" + hackathon.getPremio();
    }

    @Transactional
    public Erogazione registraErogazione(Hackathon hackathon) {
        String transazioneId = "TXN-" + System.currentTimeMillis();
        Erogazione e = Erogazione.builder()
                .hackathon(hackathon)
                .importo(hackathon.getPremio())
                .transazioneId(transazioneId)
                .stato("COMPLETATA")
                .build();
        return repositoryErogazione.save(e);
    }
}