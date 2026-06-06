package it.unicam.Hackathon.service;

import it.unicam.Hackathon.entity.Utente;
import it.unicam.Hackathon.entity.enums.RuoloUtente;
import it.unicam.Hackathon.exception.BusinessException;
import it.unicam.Hackathon.exception.ResourceNotFoundException;
import it.unicam.Hackathon.repository.RepositoryUtente;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UtenteService {

    private final RepositoryUtente repositoryUtente;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Utente creaUtente(String nome, String cognome, String email, String password) {
        if (repositoryUtente.existsByEmail(email))
            throw new BusinessException("Email duplicata: " + email);
        Utente u = Utente.builder()
                .nome(nome).cognome(cognome).email(email)
                .password(passwordEncoder.encode(password))
                .ruolo(RuoloUtente.UTENTE).build();
        return repositoryUtente.save(u);
    }

    public Utente loginUtente(String email, String password) {
        Utente u = repositoryUtente.findByEmail(email)
                .orElseThrow(() -> new BusinessException("Email non trovata"));
        if (!passwordEncoder.matches(password, u.getPassword()))
            throw new BusinessException("Password errata");
        return u;
    }

    public void terminaSessione(Long utenteId) {
        findById(utenteId);
    }

    @Transactional
    public Utente aggiornaRuolo(Long utenteId, RuoloUtente ruolo) {
        Utente u = findById(utenteId);
        u.setRuolo(ruolo);
        return repositoryUtente.save(u);
    }

    public Utente findById(Long id) {
        return repositoryUtente.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utente non trovato: " + id));
    }
}