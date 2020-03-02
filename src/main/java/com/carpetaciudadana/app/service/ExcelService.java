package com.carpetaciudadana.app.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface ExcelService {
    /**
     * Obtiene la informacion de un excel
     * @param files Archivo Excel
     * @return {@link String}
     * @throws IOException
     * @throws DocumentException
     */
    public Map<String, Object> excelProcess(MultipartFile files, String tipo) throws IOException;
    

    public Map<String, Object> csvProcess(MultipartFile files, String tipo) throws Exception, ArrayIndexOutOfBoundsException, NullPointerException; 
}
