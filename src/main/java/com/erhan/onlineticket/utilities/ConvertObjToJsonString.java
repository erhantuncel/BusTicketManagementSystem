package com.erhan.onlineticket.utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertObjToJsonString {
	
	public static void main(String[] args) {
		ObjectMapper mapper = new ObjectMapper();
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		mapper.setDateFormat(df);
		
		
		ConvertObjToJsonString example = new ConvertObjToJsonString();
		ObjForConvertToJson obj = example.createObject();
		
		try {
			String jsonString = mapper.writeValueAsString(obj);
			System.out.println(jsonString);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private ObjForConvertToJson createObject() {
		ObjForConvertToJson obj = new ObjForConvertToJson();
		
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(new Date());
		
		obj.setId(10);
		obj.setName("Erhan");
		obj.setRegisterDate(gc.getTime());
		
		return obj;
	}
}
