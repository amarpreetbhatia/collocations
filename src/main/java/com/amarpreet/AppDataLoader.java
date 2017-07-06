package com.amarpreet;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.amarpreet.model.CollocationModel;


@Component
public class AppDataLoader implements ApplicationRunner{
	
	@Autowired
	CollocationRepository collocationRepo;

	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		
		Workbook workbook = new HSSFWorkbook(new FileInputStream(new File(getClass().getClassLoader().getResource("static/AcademicCollocationList.xls").getFile())));
		Sheet sheet = workbook.getSheet("ACL");
		for(int i=2;i<=2470;i++){
			Row row = sheet.getRow(i);
			collocationRepo.save(new CollocationModel(
								row.getCell(1).getStringCellValue(),
								row.getCell(2).getStringCellValue(), 
								getValue(row.getCell(3).getStringCellValue()),
								row.getCell(4).getStringCellValue(),
								getValue(row.getCell(5).getStringCellValue()),
								row.getCell(6).getStringCellValue()));
		}
		workbook.close();
	}
	
	
	private String getValue(String nonWord){
		switch (nonWord) {
		case "n":
			return "noun";
		case "v":
			return "verb";
		case "adv":
			return "adverb";
		case "adj":
			return "adjective";

		default:
			return nonWord;
		}
		
	}
		
}
