package it.unicam.Hackathon.service;

import it.unicam.Hackathon.dto.*;
import it.unicam.Hackathon.entity.*;
import it.unicam.Hackathon.entity.enums.RuoloUtente;
import it.unicam.Hackathon.exception.BusinessException;
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
    private final SegnalazioneService segnalazioneService;
    private final ErogazioneService erogazioneService;

    public Hackathon creaHackathon(CreaHackathonRequest dto) {
        Utente organizzatore = utenteService.findById(dto.getOrganizzatoreId());
        Hackathon h = hackathonService.creaHackathon(dto, organizzatore);
        utenteService.aggiornaRuolo(organizzatore.getId(), RuoloUtente.ORGANIZZATORE);
        return h;
    }

    public Hackathon modificaHackathon(Long hackathonId, ModificaHackathonRequest dto) {
        return hackathonService.modificaHackathon(hackathonId, dto);
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

    public Sottomissione aggiornaSottomissione(AggiornaSottomissioneRequest dto) {
        sottomissioneService.findByTeamId(dto.getTeamId())
                .ifPresent(s -> sottomissioneService.elimina(s.getId()));
        Team team = teamService.findById(dto.getTeamId());
        Hackathon hackathon = hackathonService.findById(dto.getHackathonId());
        return sottomissioneService.salva(dto.getContenuto(), team, hackathon);
    }

    public List<Sottomissione> getSottomissioni(Long hackathonId) {
        return sottomissioneService.findByHackathon(hackathonId);
    }

    public Valutazione salvaValutazione(ValutazioneRequest dto) {
        Sottomissione s = sottomissioneService.findById(dto.getSottomissioneId());
        Utente giudice = utenteService.findById(dto.getGiudiceId());
        return valutazioneService.salvaValutazione(s, giudice, dto.getVoto(), dto.getTesto());
    }

    public Valutazione modificaValutazione(Long valutazioneId, ModificaValutazioneRequest dto) {
        Utente giudice = utenteService.findById(dto.getGiudiceId());
        return valutazioneService.modificaValutazione(valutazioneId, giudice, dto.getVoto(), dto.getTesto());
    }

    public Segnalazione applicaSanzione(SanzioneRequest dto) {
        Utente organizzatore = utenteService.findById(dto.getOrganizzatoreId());
        return segnalazioneService.applicaSanzione(dto.getSegnalazioneId(), organizzatore, dto.getSanzione());
    }

    public String avviaErogazionePremio(Long hackathonId, Long organizzatoreId) {
        Hackathon h = hackathonService.findById(hackathonId);
        Utente org = utenteService.findById(organizzatoreId);
        if (!h.getOrganizzatore().getId().equals(org.getId()))
            throw new BusinessException("Solo l'organizzatore può avviare l'erogazione!");
        return erogazioneService.getRiepilogoPremio(h);
    }

    public Erogazione erogaPremio(Long hackathonId) {
        Hackathon h = hackathonService.findById(hackathonId);
        return erogazioneService.registraErogazione(h);
    }

    public Hackathon proclamaVincitore(Long hackathonId, Long teamId) {
        Team vincitore = teamService.findById(teamId);
        return hackathonService.aggiornaStatoHackathon(hackathonId, vincitore);
    }

    public List<Hackathon> getAllHackathon() { return hackathonService.findAll(); }
    public Hackathon getDettagli(Long id) { return hackathonService.findById(id); }
}