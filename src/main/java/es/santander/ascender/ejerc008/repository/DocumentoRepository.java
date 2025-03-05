package es.santander.ascender.ejerc008.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.santander.ascender.ejerc008.model.Documento;

public interface DocumentoRepository extends JpaRepository<Documento, Long> {

}
