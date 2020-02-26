package com.carpetaciudadana.app.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.carpetaciudadana.app.service.ExcelService;
import com.carpetaciudadana.app.service.dto.DocumentoDTO;
import com.carpetaciudadana.app.service.util.Funciones;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ExcelServiceImpl implements ExcelService {

	private final Logger log = LoggerFactory.getLogger(ExcelServiceImpl.class);

	/**
	 * Obtiene la informacion de un excel
	 * 
	 * @param files Archivo Excel
	 * @return {@link String}
	 * @throws IOException
	 * @throws DocumentException
	 */
	public List<DocumentoDTO> excelProcess(MultipartFile files) throws IOException {
		log.info("excelProcess");
		XSSFWorkbook book = new XSSFWorkbook(files.getInputStream());
		XSSFSheet b_sheet = book.getSheetAt(0);
		List<DocumentoDTO> lDto = new ArrayList<DocumentoDTO>();
		b_sheet.forEach(a -> {
			if (a.getRowNum() != 0) {
				String[] preDto = new String[9];
				a.forEach(action -> {
					//log.info(Funciones.obtenerDato(action));
					preDto[action.getColumnIndex()] = Funciones.obtenerDato(action);
				});
				try {
					lDto.add(Funciones.sombreroSeleccionador(preDto));
				} catch (JsonProcessingException e1) {
					log.error("No se pudo procesar fila " + a.getRowNum() +"columna ");
				}
				System.out.println("ROW " +a.getRowNum() );
			}
		});
		book.close();
		return lDto;
	}


}
