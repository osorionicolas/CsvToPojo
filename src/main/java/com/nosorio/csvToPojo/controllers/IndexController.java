package com.nosorio.csvToPojo.controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.MappingIterator;
import com.nosorio.csvToPojo.models.Person;
import com.nosorio.csvToPojo.utils.Utils;

@RestController
@RequestMapping("/")
public class IndexController {
	
	private static Logger logger = LoggerFactory.getLogger(IndexController.class);

	@GetMapping("/")
	public ModelAndView index(Model model) throws IOException {
		return new ModelAndView("upload");
	}
	
	@PostMapping("/upload")
	public List<Person> submit(@RequestParam("file") MultipartFile uploadedFile) throws IOException {

	    File file = Utils.getFileFromMultipartFile(uploadedFile);
		MappingIterator<Person> person = Utils.createCsvMapper().readerWithTypedSchemaFor(Person.class)
												.readValues(file);
		List<Person> people = person.readAll();
		logger.info(people.toString());
		return people;
	}

}
