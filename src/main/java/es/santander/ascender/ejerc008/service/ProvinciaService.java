package es.santander.ascender.ejerc008.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.santander.ascender.ejerc008.model.Persona;
import es.santander.ascender.ejerc008.model.Provincia;
import es.santander.ascender.ejerc008.repository.ProvinciaRepository;

@Service
@Transactional
public class ProvinciaService {
    @Autowired
    private ProvinciaRepository provinciaRepository;


    // Buscar provincias por ID
    public Optional<Provincia> getProvinciaById(Long id) {
        return provinciaRepository.findById(id);
    }

    // CREATE
    public Provincia createProvincia(Provincia provincia) {
        return provinciaRepository.save(provincia);
    }

    // READ ALL
    @Transactional(readOnly = true)
    public List<Provincia> getAllprovincias() {
        return provinciaRepository.findAll();
    }

    // READ ONE 
    @Transactional(readOnly = true)
    public Optional<Provincia> getprovinciaById(Long id) {
        return provinciaRepository.findById(id);
    }

    // UPDATE
    public Provincia updateprovincia(Long id, Provincia provinciaDetails) {
        Optional<Provincia> provinciaOptional = provinciaRepository.findById(id);
        if (provinciaOptional.isPresent()) {
            Provincia provincia = provinciaOptional.get();
            provincia.setNombre(provinciaDetails.getNombre());
            provincia.setDescripcion(provinciaDetails.getDescripcion());
            provincia.setPoblacion(provinciaDetails.getPoblacion());
            List<Persona> personas = provincia.getPersona();

            personas.clear();
            personas.addAll(provinciaDetails.getPersona());
            personas.stream().forEach(d -> d.setProvincia(provincia));

            return provinciaRepository.save(provincia);
        }
        return null;
    }

    // // Delete
    // public boolean deleteProvincia(Long id) {
    //     Optional<Provincia> provinciaOptional = provinciaRepository.findById(id);
    //     if (provinciaOptional.isPresent()) {
    //         provinciaRepository.deleteById(id);
    //         return true;
    //     }
    //     return false;
    // }
}
