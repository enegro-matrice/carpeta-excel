package com.carpetaciudadana.app.service.util;

import java.io.EOFException;
import java.io.IOException;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.carpetaciudadana.app.domain.Ccd;
import com.carpetaciudadana.app.service.dto.DocumentoDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Funciones {

    private static final ObjectMapper mapper = new ObjectMapper();
    private final static Logger log = LoggerFactory.getLogger(Funciones.class);

    public static String obtenerDato(Cell celda) throws Exception {
        String dato = "";
        if (celda.getCellType().toString().equals("NUMERIC")) {
            if (DateUtil.isCellDateFormatted(celda)) {
                try {
                    dato = DateFormat.getDateInstance(DateFormat.SHORT).format(celda.getDateCellValue());
                } catch (Exception e) {
                    log.error("esto anda????");
                    throw new Exception("no se pudo procesar"+ e);
                }

            } else {
                try {
                    DataFormatter formatter = new DataFormatter();
                    String val = formatter.formatCellValue(celda);
                    dato = val;
                } catch (Exception e) {
                    throw new Exception("no se pudo procesar"+ e);
                }

            }
        } else {
            try {
                dato = celda.getRichStringCellValue().getString();
            } catch (Exception e) {
                throw new Exception("no se pudo procesar"+ e);
            }
            
        }
        return dato;
    }

    public static DocumentoDTO sombreroSeleccionador(String[] dato) throws Exception, JsonProcessingException,NullPointerException {
        int index = 0;
        DocumentoDTO temp = new DocumentoDTO();
        Ccd ccd = new Ccd();
        for (String string : dato) {
            try {
                checkNull(string);
            } catch (NullPointerException e) {
                throw new NullPointerException("Dato vacio " + index);
            }
            switch (index) {
                case 1:
                    ccd.setEspecialidad(string);
                    break;
                case 2:
                    ccd.setApellido(string);
                    break;
                case 3:
                    ccd.setNombre(string);
                    break;
                case 4:
                    temp.setTipoDocumento(string);                 
                    break;
                case 5:
                    temp.setNumeroDocumento(string);                   
                    break;
                case 6:
                    temp.setNacionalidadDocumento(string);                 
                    break;
                case 7:
                try {
                    temp.setFechaNacimiento(LocalDate.parse(string));
                } catch (Exception e) {
                    throw new Exception("no se pudo procesar Fecha '"+ string + "'" );
                }
                    break;
                case 8:  
                    ccd.setEmail(string);                               
                    break;
            }
                index++;

           
        }
        temp.setTipoInformacion("CERTIFICADO_CMD");
        temp.informacion(ccd);
        return temp;
    }

    public static void checkNull(String dato){
        dato.getBytes();
    }
//Ccd ccd = new Ccd().nombre("Maximiliano").apellido("Pizarro").escuelaTecnica("Albert thomas").email("asdf").especialidad("electronica");
//document.cuil("2336771843").fechaNacimiento(LocalDate.parse("2019-05-05")).tipoDocumento("DNI").nacionalidadDocumento("ARG").numeroDocumento("36771843").tipoInformacion("CERTIFICADO_CMD").informacion(ccd);
}

