package es.santander.ascender.ejerc008.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.santander.ascender.ejerc008.model.Expediente;

public interface ExpedienteRepository extends JpaRepository<Expediente, Long> {

}
