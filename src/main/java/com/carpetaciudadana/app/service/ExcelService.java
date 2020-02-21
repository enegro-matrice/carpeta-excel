package com.carpetaciudadana.app.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface ExcelService {
    /**
     * Obtiene la informacion de un excel
     * @param files Archivo Excel
     * @return {@link String}
     * @throws IOException
     * @throws DocumentException
     */
	public List<Object> excelProcess(MultipartFile files) throws IOException;
	
}
