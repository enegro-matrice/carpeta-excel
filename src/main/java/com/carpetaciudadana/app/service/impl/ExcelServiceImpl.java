package com.carpetaciudadana.app.service.impl;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.carpetaciudadana.app.service.ExcelService;
import com.carpetaciudadana.app.service.dto.DocumentoDTO;
import com.carpetaciudadana.app.service.util.Funciones;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
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
     * Obtiene la informacion de un Excel
     * @param files Archivo Excel
     * @param tipo Archivo el cual se desea tratar
     * @return
     * @throws IOException
     */
	public Map<String, Object> excelProcess(MultipartFile files, String tipo)
			throws IOException, ArrayIndexOutOfBoundsException, NullPointerException {
		log.info("excelProcess");
		XSSFWorkbook book = new XSSFWorkbook(files.getInputStream());
		XSSFSheet b_sheet = book.getSheetAt(0);
		List<DocumentoDTO> lDto = new ArrayList<DocumentoDTO>();
		List<Map<String, Object>> erroMaps = new ArrayList<Map<String, Object>>();
		Map<String, Object> errorList = new HashMap<String, Object>();
		Map<String, Object> inputInformacion = new HashMap<String, Object>();
		// log.error(" numeros "+ b_sheet.getRow(0).getPhysicalNumberOfCells());
		inputInformacion.put("Error", new ArrayList<>());
		String[] preDto = new String[b_sheet.getRow(0).getPhysicalNumberOfCells()];
		b_sheet.forEach(a -> {
			if (a.getRowNum() != 0) {
				a.forEach(action -> {
					// log.info(Funciones.obtenerDato(action));
					try {
						preDto[action.getColumnIndex()] = Funciones.obtenerDato(action);
					} catch (Exception e) {
						errorList.put("msg", e.getClass());
						errorList.put("fila", a.getRowNum() + 1);
						errorList.put("columna", action.getColumnIndex());
					}
					if (!errorList.isEmpty()) {
						erroMaps.add(new HashMap<String, Object>(errorList));
						errorList.clear();
					}
				});
				try {
					lDto.add(Funciones.sombreroSeleccionador(preDto, tipo));
				} catch (Exception e) {
					errorList.put("msg", e.getMessage());
					errorList.put("fila", a.getRowNum() + 1);
					errorList.put("columna", null);
				}
			}
			if (!errorList.isEmpty()) {
				erroMaps.add(new HashMap<String, Object>(errorList));
				errorList.clear();
			}
			// errorList.clear();
		});
		book.close();
		inputInformacion.put("Error", erroMaps);
		inputInformacion.put("Informacion", lDto);
		// lDto
		return inputInformacion;
	}
    /**
     * Obtiene la informacion de un csv
     * @param files Archivo Csv
     * @param tipo Archivo el cual se desea tratar
     * @return
     * @throws Exception
     * @throws ArrayIndexOutOfBoundsException
     * @throws NullPointerException
     */
	public Map<String, Object> csvProcess(MultipartFile files, String tipo)
			throws Exception {
		CSVParser records = CSVFormat.DEFAULT.parse(new InputStreamReader(files.getInputStream()));
		Map<String, Object> listInformacion = new HashMap<String, Object>();
		List<DocumentoDTO> inputInformacion = new ArrayList<DocumentoDTO>();
		for (CSVRecord csvRecord : records) {
			if(csvRecord.getRecordNumber() > 1){
				String[] name = csvRecord.get(0).split(";");
				try {
					inputInformacion.add( Funciones.sombreroSeleccionador(name, tipo));
				} catch (Exception e) {
					log.error(e.getMessage());
				}
					
					//inputInformacion.clear();
			}
		}
		listInformacion.put("Informacion", inputInformacion);
		return listInformacion;
	}


}
