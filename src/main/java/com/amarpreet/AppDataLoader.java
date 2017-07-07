package com.amarpreet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.amarpreet.model.CollocationModel;
import com.amarpreet.model.WordList;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@Component
public class AppDataLoader implements ApplicationRunner{
	
	@Autowired
	CollocationRepository collocationRepo;
	
	private ConcurrentHashMap<String, WordList> concurrentHashMap = new ConcurrentHashMap<String, WordList>();

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
			
			concurrentHashMap.putIfAbsent(row.getCell(2).getStringCellValue(), new WordList());
			concurrentHashMap.putIfAbsent(row.getCell(4).getStringCellValue(), new WordList());
			
		}
		System.out.println("number of unqiue words\t" + concurrentHashMap.size());
		workbook.close();
		populateSynonyms();
	}
	
	private void populateSynonyms(){
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept",MediaType.APPLICATION_JSON_VALUE);
		headers.set("app_id", "e5da5288");
		headers.set("app_key", "1c9c9a30ff1b70082a9e5cd2ddff6af4");
		HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
		//ObjectMapper objectMapper = new ObjectMapper();
		//try {
			//JsonNode readValue = objectMapper.readValue(new File(getClass().getClassLoader().getResource("static/sampleresponse.json").getFile()), JsonNode.class);
	/*	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	*/	
	//	restTemplate.getMessageConverters().add(0,new MappingJackson2HttpMessageConverter(objectMapper));
		ResponseEntity<JsonNode> result = restTemplate.exchange("https://od-api.oxforddictionaries.com:443/api/v1/entries/en/ace/synonyms;antonyms",HttpMethod.GET, requestEntity,JsonNode.class);
		JsonNode readValue = result.getBody();
		System.out.println("--->" + readValue.get("results").get(0).get("id"));
		System.out.println("--->" + readValue.get("results").get(0).get("lexicalEntries").get(0).get("entries").get(0).get("senses").get(0).get("synonyms").toString());
	
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
