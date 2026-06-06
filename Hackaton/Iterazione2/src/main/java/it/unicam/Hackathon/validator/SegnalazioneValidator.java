package it.unicam.Hackathon.validator;

import it.unicam.Hackathon.dto.SegnalazioneRequest;
import it.unicam.Hackathon.exception.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class SegnalazioneValidator implements Validator<SegnalazioneRequest> {

    @Override
    public void validate(SegnalazioneRequest dto) {
        if (dto.getMentoreId() == null)
            throw new ValidationException("MentoreId obbligatorio");
        if (dto.getTeamId() == null)
            throw new ValidationException("TeamId obbligatorio");
        if (dto.getHackathonId() == null)
            throw new ValidationException("HackathonId obbligatorio");
        if (dto.getDescrizione() == null || dto.getDescrizione().isBlank())
            throw new ValidationException("Descrizione obbligatoria");
    }
}