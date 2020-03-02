package com.carpetaciudadana.app.web.rest;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.carpetaciudadana.app.service.ExcelService;
import com.carpetaciudadana.app.service.dto.DocumentoDTO;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
     * Obtiene la informacion de un excel
     * @param files Archivo Excel
     * @return {@link String}
     * @throws IOException
     * @throws DocumentException
     */
    @ApiOperation(value = "Produce un documento pdf a partir de una lista de documentos pdf", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PostMapping(value = "/file/excel/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, Object>> getExcelToData(@RequestParam (value = "files", required = true ) @ApiParam(value = "files") final MultipartFile files,
                                                              @RequestParam (value = "tipo",required = true) String tipo) throws IOException{
        log.debug("Entro en getExcelToData POST /file/excel/");
        if (!files.isEmpty()){
            return ResponseEntity.accepted().body(excelService.excelProcess(files, tipo));
        }else{
            //throw new CustomParameterizedException("No se encuentra aliniacion");
            log.error("rompio");
        }
        return null;
    }


        /**
     * Obtiene la informacion de un csv
     * @param files Archivo csv
     * @return {@link String}
     * @throws IOException
     * @throws DocumentException
     */
    @ApiOperation(value = "Produce un documento pdf a partir de una lista de documentos pdf", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PostMapping(value = "/file/csv/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<Map<String, Object>>> getCsvToData(@RequestParam (value = "files", required = true ) @ApiParam(value = "files") final MultipartFile files) throws IOException{
        log.debug("Entro en getExcelToData POST /file/excel/");
        if (!files.isEmpty()){
            return ResponseEntity.accepted().body(excelService.csvProcess(files));
        }else{
            //throw new CustomParameterizedException("No se encuentra aliniacion");
            log.error("rompio");
        }
        return null;
    }
}