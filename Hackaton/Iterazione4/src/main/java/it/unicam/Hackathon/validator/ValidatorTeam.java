package it.unicam.Hackathon.validator;

import it.unicam.Hackathon.dto.CreaTeamRequest;
import it.unicam.Hackathon.exception.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class ValidatorTeam implements Validator<CreaTeamRequest> {

    @Override
    public void validate(CreaTeamRequest dto) {
        if (dto.getNome() == null || dto.getNome().isBlank())
            throw new ValidationException("Il nome del team non può essere vuoto");
        if (dto.getCapoGruppoId() == null)
            throw new ValidationException("CapoGruppoId obbligatorio");
        if (dto.getHackathonId() == null)
            throw new ValidationException("HackathonId obbligatorio");
    }
}