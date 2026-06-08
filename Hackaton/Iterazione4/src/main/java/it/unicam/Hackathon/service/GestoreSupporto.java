package it.unicam.Hackathon.service;

import it.unicam.Hackathon.dto.*;
import it.unicam.Hackathon.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GestoreSupporto {

    private final SupportoService supportoService;
    private final TeamService teamService;
    private final HackathonService hackathonService;
    private final UtenteService utenteService;

    public Supporto richiestaSupporto(SupportoRequest dto) {
        Team team = teamService.findById(dto.getTeamId());
        Hackathon hackathon = hackathonService.findById(dto.getHackathonId());
        return supportoService.creaRichiesta(team, hackathon, dto.getDescrizione());
    }

    public List<Supporto> getRichieste(Long hackathonId) {
        return supportoService.getRichieste(hackathonId);
    }

    public CallSupporto proponeCall(ProponeCallRequest dto) {
        Utente mentore = utenteService.findById(dto.getMentoreId());
        Supporto supporto = supportoService.findById(dto.getSupportoId());
        return supportoService.creaCallSupporto(
                mentore, supporto, dto.getData(),
                dto.getOra(), dto.getDurata(), dto.getNote());
    }

    public CallSupporto prenotaSlot(PrenotaSlotRequest dto) {
        return supportoService.prenotaSlot(dto.getCallId(), dto.getSlotConfermato());
    }
}