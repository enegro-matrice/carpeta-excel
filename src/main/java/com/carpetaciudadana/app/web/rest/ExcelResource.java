package com.carpetaciudadana.app.web.rest;

import java.io.IOException;

import org.springframework.http.MediaType;
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
    /**
     * Obtiene la informacion de un excel
     * @param files Archivo Excel
     * @return {@link String}
     * @throws IOException
     * @throws DocumentException
     */
    @ApiOperation(value = "Produce un documento pdf a partir de una lista de documentos pdf", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PostMapping(value = "/file/excel/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String getExcelToData(@RequestParam (value = "files", required = true ) @ApiParam(value = "files") MultipartFile files) throws IOException{
        log.debug("Entro en getExcelToData POST /file/excel/");
        if (!files.isEmpty()){
            //return pdfService.excelProcess(files);
            return "arg0";
        }else{
            //throw new CustomParameterizedException("No se encuentra aliniacion");
            log.error("rompio");
        }
        return "rompio";
    }
}