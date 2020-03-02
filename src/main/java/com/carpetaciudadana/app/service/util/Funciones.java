package com.carpetaciudadana.app.service.util;

import java.text.DateFormat;

import com.carpetaciudadana.app.domain.Ccd;
import com.carpetaciudadana.app.domain.EstudianteCedel;
import com.carpetaciudadana.app.service.dto.DocumentoDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Funciones {

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

    public static DocumentoDTO sombreroSeleccionador(String[] dato, String tipo) throws Exception, JsonProcessingException,NullPointerException {
        int index = 0;
        for (String string : dato) {
            try {
                index++;
                checkNull(string);
            } catch (NullPointerException e) {
                throw new NullPointerException("Dato vacio " + index);
            }          
        }
        DocumentoDTO temp = new DocumentoDTO();
        if(tipo.equals("CERTIFICADO_CMD")){

            temp.setTipoDocumento(dato[4]); 
            temp.setNumeroDocumento(dato[5]); 
            temp.setNacionalidadDocumento(dato[6]); 
            Ccd ccd = new Ccd();

            ccd.setEspecialidad(dato[1]);
            ccd.setApellido(dato[2]);
            ccd.setNombre(dato[3]);
            ccd.setEmail(dato[8]); 
            temp.informacion(ccd);  
            // try {
            //     temp.setFechaNacimiento(LocalDate.parse(dato[7]));
            // } catch (Exception e) {
            //     throw new Exception("no se pudo procesar Fecha '"+ dato[7] + "'" );
            // }
            
        }else if (tipo.equals("CERTIFICADO_CEDEL")) {
            EstudianteCedel cedel = new EstudianteCedel();
            cedel.apellido(dato[2]);
            cedel.nombre(dato[1]);
            cedel.dni(dato[0]);
            cedel.curso(dato[3]);
            cedel.fechaFin(dato[4]);
            temp.informacion(cedel);
        }else{
            throw new Exception("Tipo inconrrecto" );
        }
        temp.setTipoInformacion(tipo);
        return temp;
    }

    public static void checkNull(String dato){
        dato.getBytes();
    }
//Ccd ccd = new Ccd().nombre("Maximiliano").apellido("Pizarro").escuelaTecnica("Albert thomas").email("asdf").especialidad("electronica");
//document.cuil("2336771843").fechaNacimiento(LocalDate.parse("2019-05-05")).tipoDocumento("DNI").nacionalidadDocumento("ARG").numeroDocumento("36771843").tipoInformacion("CERTIFICADO_CMD").informacion(ccd);
}

