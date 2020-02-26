package com.carpetaciudadana.app.service;

import java.io.IOException;
import java.util.List;

import com.carpetaciudadana.app.service.dto.DocumentoDTO;

import org.springframework.web.multipart.MultipartFile;

public interface ExcelService {
    /**
     * Obtiene la informacion de un excel
     * @param files Archivo Excel
     * @return {@link String}
     * @throws IOException
     * @throws DocumentException
     */
	public List<DocumentoDTO> excelProcess(MultipartFile files) throws IOException;
	
}
