package it.unicam.Hackathon.validator;

import it.unicam.Hackathon.dto.LoginRequest;
import it.unicam.Hackathon.dto.RegistrazioneRequest;
import it.unicam.Hackathon.exception.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class ValidatorUser implements Validator<RegistrazioneRequest> {

    @Override
    public void validate(RegistrazioneRequest dto) {
        if (dto.getNome() == null || dto.getNome().isBlank())
            throw new ValidationException("Il nome non può essere vuoto");
        if (dto.getEmail() == null || !dto.getEmail().contains("@"))
            throw new ValidationException("Email non valida");
        if (dto.getPassword() == null || dto.getPassword().length() < 8)
            throw new ValidationException("La password deve avere almeno 8 caratteri");
    }

    public void verificaDati(LoginRequest dto) {
        if (dto.getEmail() == null || !dto.getEmail().contains("@"))
            throw new ValidationException("Email non valida");
        if (dto.getPassword() == null || dto.getPassword().isBlank())
            throw new ValidationException("Password obbligatoria");
    }
}