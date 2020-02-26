package com.carpetaciudadana.app.service.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.carpetaciudadana.app.serializers.LocalDateDeserializer;
import com.carpetaciudadana.app.serializers.LocalDateSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/*
 * DocumentoDTO se utiliza para el servicio de crearDocumento
 */

public class DocumentoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	@JsonIgnore
	private String cuil;
	@JsonIgnore
	private String tipoDocumento;
	@JsonIgnore
	private String numeroDocumento;
	@JsonIgnore
	private String nacionalidadDocumento;
	
	@JsonIgnore
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate fechaNacimiento;

	private String tipoInformacion;

	private Object informacion;
	@JsonIgnore
	private String binarioEncode;

	public DocumentoDTO() {
		// Empty constructor needed for Jackson.
	}

	public DocumentoDTO(String cuil, String tipoDocumento, String numeroDocumento, String nombreArchivo,
			String nacionalidadDocumento, LocalDate fechaNacimiento, String tipoInformacion, String informacion,
			String emisor) {
		this.cuil = cuil;
		this.tipoDocumento = tipoDocumento;
		this.numeroDocumento = numeroDocumento;
		this.nacionalidadDocumento = nacionalidadDocumento;
		this.fechaNacimiento = fechaNacimiento;
		this.tipoInformacion = tipoInformacion;
		this.informacion = informacion;
	}
	
	//DTO Documento con binario encode
	public DocumentoDTO(String cuil, String tipoDocumento, String numeroDocumento, String nombreArchivo,
			String nacionalidadDocumento, LocalDate fechaNacimiento, String tipoInformacion, String informacion,
			String emisor,String binarioEncode) {
		this.cuil = cuil;
		this.tipoDocumento = tipoDocumento;
		this.numeroDocumento = numeroDocumento;
		this.nacionalidadDocumento = nacionalidadDocumento;
		this.fechaNacimiento = fechaNacimiento;
		this.tipoInformacion = tipoInformacion;
		this.informacion = informacion;
		this.binarioEncode=binarioEncode;
	}
	
	public String getCuil() {
		return cuil;
	}

	public void setCuil(String cuil) {
		this.cuil = cuil;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getNacionalidadDocumento() {
		return nacionalidadDocumento;
	}

	public void setNacionalidadDocumento(String nacionalidadDocumento) {
		this.nacionalidadDocumento = nacionalidadDocumento;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getTipoInformacion() {
		return tipoInformacion;
	}

	public void setTipoInformacion(String tipoInformacion) {
		this.tipoInformacion = tipoInformacion;
	}

	public Object getInformacion() {
		return informacion;
	}

	public void setInformacion(Object informacion) throws JsonProcessingException {
		this.informacion = informacion;
	}	
	

	public String getBinarioEncode() {
		return binarioEncode;
	}

	public void setBinarioEncode(String binarioEncode) {
		this.binarioEncode = binarioEncode;
	}

	@Override
	public String toString() {
		return "{cuil:" + cuil + ", tipoDocumento:" + tipoDocumento + ", numeroDocumento:"
				+ numeroDocumento + ", nacionalidadDocumento:" + nacionalidadDocumento + ", fechaNacimiento:"
				+ fechaNacimiento + ", tipoInformacion:" + tipoInformacion + ", informacion:" + informacion + "}";
	}

	public DocumentoDTO cuil(String cuil) {
		this.cuil=cuil;
		return this;
	}

	public DocumentoDTO tipoDocumento(String tipoDocumento) {
		this.tipoDocumento=tipoDocumento;
		return this;
	}
	
	public DocumentoDTO numeroDocumento(String numeroDocumento) {
		this.numeroDocumento=numeroDocumento;
		return this;
	}

	public DocumentoDTO nacionalidadDocumento(String nacionalidadDocumento) {
		this.nacionalidadDocumento=nacionalidadDocumento;
		return this;
	}

	public DocumentoDTO fechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento=fechaNacimiento;
		return this;
	}
	public DocumentoDTO informacion(Object informacion) throws JsonProcessingException {
		this.informacion=informacion;
		return this;
	}

	public DocumentoDTO tipoInformacion(String tipoInformacion) {
		this.tipoInformacion=tipoInformacion;
		return this;
	}
	
	public DocumentoDTO binarioEncode(String binarioEncode) {
		this.binarioEncode=binarioEncode;
		return this;
	}

}
