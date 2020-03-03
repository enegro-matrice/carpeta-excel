package com.carpetaciudadana.app.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface ExcelService {
    /**
     * Obtiene la informacion de un Excel
     * @param files Archivo Excel
     * @param tipo Archivo el cual se desea tratar
     * @return
     * @throws IOException
     */
    public Map<String, Object> excelProcess(MultipartFile files, String tipo) throws IOException;
    

    /**
     * Obtiene la informacion de un csv
     * @param files Archivo Csv
     * @param tipo Archivo el cual se desea tratar
     * @return
     * @throws Exception
     * @throws ArrayIndexOutOfBoundsException
     * @throws NullPointerException
     */

    public Map<String, Object> csvProcess(MultipartFile files, String tipo) throws Exception, ArrayIndexOutOfBoundsException, NullPointerException; 
}
