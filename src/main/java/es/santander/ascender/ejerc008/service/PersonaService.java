package es.santander.ascender.ejerc008.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.santander.ascender.ejerc008.model.Persona;
import es.santander.ascender.ejerc008.repository.PersonaRepository;

@Service
@Transactional
public class PersonaService {
    @Autowired
    private PersonaRepository personaRepository;

    // Create
    public Persona createPersona(Persona persona) {
        return personaRepository.save(persona);
    }

    // Read (all)
    @Transactional(readOnly = true)
    public List<Persona> getAllPersonas() {
        return personaRepository.findAll();
    }

    // Read (by ID)
    @Transactional(readOnly = true)
    public Optional<Persona> getPersonaById(Long id) {
        return personaRepository.findById(id);
    }

    // Update
    public Persona updatePersona(Long id, Persona personaDetails) {
        Optional<Persona> PersonaOptional = personaRepository.findById(id);
        if (PersonaOptional.isPresent()) {
            Persona persona = PersonaOptional.get();
            persona.setNombre(personaDetails.getNombre());
            persona.setApellido(personaDetails.getApellido());
            persona.setEdad(personaDetails.getEdad());
            persona.setEstadoCivil(personaDetails.getEstadoCivil());
            persona.setProvincia(personaDetails.getProvincia());

            return personaRepository.save(persona);
        }
        return null;
    }

    // Delete
    public boolean deletePersona(Long id) {
        Optional<Persona> personaOptional = personaRepository.findById(id);
        if (personaOptional.isPresent()) {
            personaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
