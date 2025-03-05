package es.santander.ascender.ejerc008.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import es.santander.ascender.ejerc008.model.Persona;
import es.santander.ascender.ejerc008.model.Provincia;
import es.santander.ascender.ejerc008.service.PersonaService;
import es.santander.ascender.ejerc008.service.ProvinciaService;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @Autowired
    private ProvinciaService provinciaService;

    //Create
    @PostMapping
    public ResponseEntity<?> createPersona(@RequestBody Persona persona) {
        if (persona.getProvincia() == null || persona.getProvincia().getId() == null) {
            return ResponseEntity.badRequest().body("El ID de la provincia es obligatorio.");
        }
    
        Optional<Provincia> optionalProvincia = provinciaService.getProvinciaById(persona.getProvincia().getId());
        
        if (optionalProvincia.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Provincia con ID " + persona.getProvincia().getId() + " no encontrada.");
        }
    
        persona.setProvincia(optionalProvincia.get());
        Persona createdPersona = personaService.createPersona(persona);
    
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPersona);
    }

    // Read (all)
    @GetMapping
    public ResponseEntity<List<Persona>> getAllPersonas() {
        List<Persona> personas = personaService.getAllPersonas();
        return ResponseEntity.ok(personas);
    }

    // Read (by ID)
    @GetMapping("/{id}")
    public ResponseEntity<Persona> getPersonaById(@PathVariable Long id) {
        Optional<Persona> optionalPersona = personaService.getPersonaById(id);
        return optionalPersona.map(persona -> ResponseEntity.ok(persona))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<Persona> updatePersona(@PathVariable Long id, @RequestBody Persona personaDetails) {
        Persona updatedPersona = personaService.updatePersona(id, personaDetails);
        return updatedPersona != null ? ResponseEntity.ok(updatedPersona) : ResponseEntity.notFound().build();
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersona(@PathVariable Long id) {
        boolean deleted = personaService.deletePersona(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
