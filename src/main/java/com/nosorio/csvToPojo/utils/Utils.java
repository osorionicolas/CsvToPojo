package com.nosorio.csvToPojo.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;

public class Utils {

	public static File getFileFromMultipartFile(MultipartFile uploadedFile) throws FileNotFoundException, IOException {
		File file = File.createTempFile("people", "csv");
		IOUtils.copy(uploadedFile.getInputStream(), new FileOutputStream(file));
		return file;
	}
	
	public static CsvMapper createCsvMapper() {
	    CsvMapper csvMapper = new CsvMapper();
	    csvMapper.disable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY);
	    csvMapper.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true);
	    return csvMapper;
	}
}
