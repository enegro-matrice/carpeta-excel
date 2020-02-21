package com.carpetaciudadana.app.service.impl;

import java.io.IOException;
import java.text.DateFormat;
import java.util.List;

import com.carpetaciudadana.app.service.ExcelService;

import org.apache.poi.ss.usermodel.DateUtil;
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
     * @param files Archivo Excel
     * @return {@link String}
     * @throws IOException
     * @throws DocumentException
     */
	public List<Object> excelProcess(MultipartFile files) throws IOException{
		log.info("excelProcess");
		XSSFWorkbook book = new XSSFWorkbook(files.getInputStream());
		book.close();
		log.info("book");
		book.forEach( e -> {
			System.out.println("=> " + e.getSheetName());
		});
		//String fecha = "mm\-dd\-yyyy";
		XSSFSheet b_sheet = book.getSheetAt(0);
		b_sheet.forEach(a -> {
			if(a.getRowNum() != 0){
				a.forEach(action -> {
					//System.out.println("=> " + action.getCellType());
					System.out.println("Style " + action.getCellType());
					if(action.getCellType().toString().equals("NUMERIC") ){
						if(DateUtil.isCellDateFormatted(action)){
							System.out.println("Date " +  DateFormat.getDateInstance(DateFormat.SHORT).format(action.getDateCellValue()));
						}else{
							System.out.println("Numeric " +action.getNumericCellValue());
						}
					}else{
						System.out.println("String " +action.getRichStringCellValue());
					}
				});
			}
		});


		return null;
	}


}
