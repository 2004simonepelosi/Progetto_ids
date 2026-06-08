package it.unicam.Hackathon.service;

import it.unicam.Hackathon.dto.LoginRequest;
import it.unicam.Hackathon.dto.RegistrazioneRequest;
import it.unicam.Hackathon.entity.Utente;
import it.unicam.Hackathon.validator.ValidatorUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GestoreUtente {

    private final ValidatorUser validatorUser;
    private final UtenteService utenteService;

    public Utente registrazioneUtente(RegistrazioneRequest dto) {
        validatorUser.validate(dto);
        return utenteService.creaUtente(
                dto.getNome(), dto.getCognome(),
                dto.getEmail(), dto.getPassword());
    }

    public Utente loginUtente(LoginRequest dto) {
        validatorUser.verificaDati(dto);
        return utenteService.loginUtente(dto.getEmail(), dto.getPassword());
    }

    public void logout(Long utenteId) {
        utenteService.terminaSessione(utenteId);
    }

    public Utente findById(Long id) {
        return utenteService.findById(id);
    }
}