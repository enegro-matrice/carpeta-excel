package com.carpetaciudadana.app.service.util;

import java.text.DateFormat;
import java.time.LocalDate;

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
    private final Logger log = LoggerFactory.getLogger(Funciones.class);

    public static String obtenerDato(Cell celda) {
        String dato = "";
        if (celda.getCellType().toString().equals("NUMERIC")) {
            if (DateUtil.isCellDateFormatted(celda)) {
                dato = DateFormat.getDateInstance(DateFormat.SHORT).format(celda.getDateCellValue());
            } else {
                DataFormatter formatter = new DataFormatter();
                String val = formatter.formatCellValue(celda);
                dato = val;
            }
        } else {
            dato = celda.getRichStringCellValue().getString();
        }
        return dato;
    }

    public static DocumentoDTO sombreroSeleccionador(String[] dato) throws JsonProcessingException {
        int index = 0;
        DocumentoDTO temp = new DocumentoDTO();
        Ccd ccd = new Ccd();
        for (String string : dato) {
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
                temp.setFechaNacimiento(LocalDate.parse(string));
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
//Ccd ccd = new Ccd().nombre("Maximiliano").apellido("Pizarro").escuelaTecnica("Albert thomas").email("asdf").especialidad("electronica");
//document.cuil("2336771843").fechaNacimiento(LocalDate.parse("2019-05-05")).tipoDocumento("DNI").nacionalidadDocumento("ARG").numeroDocumento("36771843").tipoInformacion("CERTIFICADO_CMD").informacion(ccd);
}
