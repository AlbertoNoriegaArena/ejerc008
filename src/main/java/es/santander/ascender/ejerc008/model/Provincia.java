package es.santander.ascender.ejerc008.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;

@Entity
public class Provincia {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Length(max = 30)
    private String nombre;

    @Length(max = 200)
    private String descripcion;

    @Min(0)
    private int poblacion;

    @JsonIgnore
    @OneToMany(mappedBy = "provincia", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
    private List<Persona> persona = new ArrayList<>();

    public Provincia() {
    }

    public Provincia(Long id, String nombre, String descripcion, int poblacion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.poblacion = poblacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(int poblacion) {
        this.poblacion = poblacion;
    }

    public List<Persona> getPersona() {
        return persona;
    }

    public void setPersona(List<Persona> persona) {
        this.persona = persona;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Provincia other = (Provincia) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

}
