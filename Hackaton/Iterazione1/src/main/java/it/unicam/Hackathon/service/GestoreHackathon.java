package it.unicam.Hackathon.service;

import it.unicam.Hackathon.dto.*;
import it.unicam.Hackathon.entity.*;
import it.unicam.Hackathon.entity.enums.RuoloUtente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GestoreHackathon {

    private final HackathonService hackathonService;
    private final SottomissioneService sottomissioneService;
    private final ValutazioneService valutazioneService;
    private final UtenteService utenteService;
    private final TeamService teamService;

    public Hackathon creaHackathon(CreaHackathonRequest dto) {
        Utente organizzatore = utenteService.findById(dto.getOrganizzatoreId());
        Hackathon h = hackathonService.creaHackathon(dto, organizzatore);
        utenteService.aggiornaRuolo(organizzatore.getId(), RuoloUtente.ORGANIZZATORE);
        return h;
    }

    public Hackathon nominaGiudice(Long hackathonId, Long utenteId) {
        Utente giudice = utenteService.findById(utenteId);
        utenteService.aggiornaRuolo(utenteId, RuoloUtente.GIUDICE);
        return hackathonService.aggiornaGiudice(hackathonId, giudice);
    }

    public Hackathon nominaMentore(Long hackathonId, Long utenteId) {
        Utente mentore = utenteService.findById(utenteId);
        utenteService.aggiornaRuolo(utenteId, RuoloUtente.MENTORE);
        return hackathonService.aggiornaMentori(hackathonId, mentore);
    }

    public Sottomissione salvaSottomissione(SottomissioneRequest dto) {
        Team team = teamService.findById(dto.getTeamId());
        Hackathon hackathon = hackathonService.findById(dto.getHackathonId());
        return sottomissioneService.salva(dto.getContenuto(), team, hackathon);
    }

    public Valutazione salvaValutazione(ValutazioneRequest dto) {
        Sottomissione s = sottomissioneService.findById(dto.getSottomissioneId());
        Utente giudice = utenteService.findById(dto.getGiudiceId());
        return valutazioneService.salvaValutazione(s, giudice, dto.getVoto(), dto.getTesto());
    }

    public Hackathon proclamaVincitore(Long hackathonId, Long teamId) {
        Team vincitore = teamService.findById(teamId);
        return hackathonService.aggiornaStatoHackathon(hackathonId, vincitore);
    }

    public List<Hackathon> getAllHackathon() {
        return hackathonService.findAll();
    }

    public Hackathon getDettagli(Long id) {
        return hackathonService.findById(id);
    }
}