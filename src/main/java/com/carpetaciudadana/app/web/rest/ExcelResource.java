package com.carpetaciudadana.app.web.rest;

import java.util.Map;

import com.carpetaciudadana.app.service.ExcelService;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api")
@Api(value = "CarpetaCiudadanaTemplate",description = "Endpoints")
public class ExcelResource {
    private final Logger log = LoggerFactory.getLogger(ExcelResource.class);
    private final ExcelService excelService;
    public ExcelResource(ExcelService excelService) {
        this.excelService = excelService;
    }
    
    /**
     * Obtiene la informacion de un excel o csv
     * 
     * @param files Archivo Excel o csv
     * @return {@link ResponseEntity}
     * @throws Exception
     * @throws NullPointerException
     * @throws ArrayIndexOutOfBoundsException
     * @throws DocumentException
     */
    @PostMapping(value = "/file/excel/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, Object>> getExcelToData(
            @RequestParam(value = "files", required = true) @ApiParam(value = "files") final MultipartFile files,
            @RequestParam(value = "tipo", required = true) String tipo)
            throws ArrayIndexOutOfBoundsException, NullPointerException, Exception {
        log.debug("Entro en getExcelToData POST /file/excel/");
        if (!files.isEmpty()){
            if(FilenameUtils.getExtension(files.getOriginalFilename()).equals("xlsx")){
                return ResponseEntity.accepted().body(excelService.excelProcess(files, tipo));
            }else if(FilenameUtils.getExtension(files.getOriginalFilename()).equals("csv")){
                return ResponseEntity.accepted().body(excelService.csvProcess(files,tipo));
            }

        }else{
            log.error("rompio");
        }
        return null;
    }
}