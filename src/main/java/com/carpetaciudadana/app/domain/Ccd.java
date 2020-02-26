package com.carpetaciudadana.app.domain;

import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.boot.configurationprocessor.json.JSONException;

@JsonTypeName("CERTIFICADO_CMD")
public class Ccd implements Serializable {

    private static final long serialVersionUID = 1L;
	private final static ObjectMapper mapper = new ObjectMapper();


    private Long id;


    private String escuelaTecnica;


    private String especialidad;


    private String apellido;


    private String nombre;

    private String email;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEscuelaTecnica() {
        return escuelaTecnica;
    }

    public Ccd escuelaTecnica(String escuelaTecnica) {
        this.escuelaTecnica = escuelaTecnica;
        return this;
    }

    public void setEscuelaTecnica(String escuelaTecnica) {
        this.escuelaTecnica = escuelaTecnica;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public Ccd especialidad(String especialidad) {
        this.especialidad = especialidad;
        return this;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getApellido() {
        return apellido;
    }

    public Ccd apellido(String apellido) {
        this.apellido = apellido;
        return this;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public Ccd nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public Ccd email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ccd ccd = (Ccd) o;
        if (ccd.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ccd.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "{" +
            " escuelaTecnica:'" + getEscuelaTecnica() + "'" +
            ", especialidad:'" + getEspecialidad() + "'" +
            ", apellido:'" + getApellido() + "'" +
            ", nombre:'" + getNombre() + "'" +
            ", email:'" + getEmail() + "'" +
            "}";
    }
    
	public String toJson() throws JsonProcessingException {
		return mapper.writeValueAsString(this);
	}

	public Object toJsonObject() throws JSONException, IOException {
		return mapper.readValue(mapper.writeValueAsString(this), Object.class); 
    }

}
