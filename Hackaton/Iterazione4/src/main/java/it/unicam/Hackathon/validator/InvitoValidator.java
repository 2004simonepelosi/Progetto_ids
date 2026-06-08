package it.unicam.Hackathon.validator;

import it.unicam.Hackathon.dto.InvitoRequest;
import it.unicam.Hackathon.exception.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class InvitoValidator implements Validator<InvitoRequest> {

    @Override
    public void validate(InvitoRequest dto) {
        if (dto.getTeamId() == null)
            throw new ValidationException("TeamId obbligatorio");
        if (dto.getUtenteId() == null)
            throw new ValidationException("UtenteId obbligatorio");
    }
}