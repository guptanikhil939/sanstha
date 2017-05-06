package com.sanstha.controller;

import java.io.File;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sanstha.util.Constants;

@Controller
public class ImageController
{
	public static final String BASE_PATH = Constants.COMMON_LOCATION;

	@RequestMapping(value = "/images/{fileName:.+}", method = { RequestMethod.GET })
	public ResponseEntity<FileSystemResource> getFile(
			@PathVariable("fileName") String fileName)
	{
		FileSystemResource resource = new FileSystemResource(new File(
				BASE_PATH, fileName));
		ResponseEntity<FileSystemResource> responseEntity = new ResponseEntity<FileSystemResource>(
				resource, HttpStatus.OK);
		return responseEntity;
	}
}