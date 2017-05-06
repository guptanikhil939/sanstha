package com.sanstha.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;

import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanstha.controller.HomeController;
import com.sanstha.exception.ServiceException;
import com.sanstha.model.Society;
import com.sanstha.service.SocietyService;
import com.sanstha.service.TemplateService;
import com.sanstha.util.Constants;

@Service("templateService")
public class TemplateServiceImpl implements TemplateService
{

	private static final Logger log = Logger.getLogger(HomeController.class);
	@Autowired
	VelocityEngine velocity;
	@Autowired
	SocietyService societyService;


	public boolean createTemplate(String societyName) throws ServiceException
	{
		log.debug("TemplateServiceImpl :: createTemplate");

		try
		{
			//String content = Constants.COMMON_CONTENT;
			//VelocityEngine ve = new VelocityEngine();
			velocity.init();
			Template t = velocity.getTemplate( "siteTemplate.vm" );
			VelocityContext context = new VelocityContext();
			context.put("name", "World");
			context.put("address", "value");
			StringWriter writer = new StringWriter();
			t.merge( context, writer );
			System.out.println( writer.toString() );
			return updateTemplateContent(writer.toString(), societyName);
		}
		catch (Exception exception)
		{
			throw new ServiceException(exception.getMessage(),exception); 
		}
	}

	public boolean updateTemplateContent(String content, String fileName) throws ServiceException
	{
		log.debug("TemplateServiceImpl :: updateTemplateContent");

		try
		{
			fileName = fileName.replaceAll("[^a-zA-Z]", "");
			fileName = fileName + ".htm";
			String fileLocation = Constants.COMMON_LOCATION;
			log.debug("File Location: "+fileLocation);
			String tmpFileName = fileName + "_tmp";

			File oldDatafile = new File(fileLocation, fileName);
			File newfile = new File(fileLocation, tmpFileName);

			if (newfile.createNewFile())
			{
				log.debug("New file " + tmpFileName + " created successfully at " + fileLocation + ".");
				FileWriter file = new FileWriter(newfile);
				file.write(content);
				file.flush();
				file.close();
				log.debug("Content has been written successfully to the file" + tmpFileName + " created successfully at " + fileLocation + ".");
			}

			if (oldDatafile.exists())
			{
				oldDatafile.delete();
				log.debug("Deleteing old file " + fileName + " from common Location (" + fileLocation + ").");
			}
			newfile.renameTo(new File(fileLocation, fileName));
		}
		catch (Exception exception)
		{
			throw new ServiceException(exception.getMessage(),exception); 
		}

		return true;
	}

	public String getTemplate(Society society)
	{
		log.debug("TemplateServiceImpl :: getTemplate for site home");

		//String fileLocation = Constants.COMMON_LOCATION + fileName + ".htm";
		//StringBuilder out = null;
		Template template = null;
		velocity.init();
		VelocityContext context = new VelocityContext();
		if(society == null){
			template = velocity.getTemplate("default.vm");
		}
		else{
		 template = velocity.getTemplate("siteTemplate.vm");
			context.put("name", society.getSocietyName());
			context.put("address", society.getSocietyWebAddress());
			context.put("webname", society.getSocietyWebName());
		}
	
		StringWriter writer = new StringWriter();
		template.merge( context, writer );
		return writer.toString();


		/*try
		{
			InputStream in = new FileInputStream(fileLocation);
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		    out = new StringBuilder();
		    String line;
		    while ((line = reader.readLine()) != null) {
		        out.append(line);
		    }
		    reader.close();
		}
		catch (Exception e) 
		{
			return readErrorFileContent();
		}

		return out.toString();*/
	}
	
	public String getTemplate(Society society, String pageName)
	{
		log.debug("TemplateServiceImpl :: getTemplate for other pages of sites");
		velocity.init();
		VelocityContext context = new VelocityContext();
		Template template = null;
		
		if(society == null)
			template = velocity.getTemplate("default.vm");
		else{
			if(pageName.equalsIgnoreCase("aboutus")){
			template = velocity.getTemplate("aboutUsTemplate.vm");
			context.put("name", society.getSocietyName());
			context.put("about", society.getSocietyAbout());
			context.put("webname", society.getSocietyWebName());
		}
		else if(pageName.equalsIgnoreCase("contact")){
			template = velocity.getTemplate("contactTemplate.vm");
			context.put("name", society.getSocietyName());
			context.put("contact",society.getSocietyContact());
			context.put("webname", society.getSocietyWebName());
		}
	}
		StringWriter writer = new StringWriter();
		template.merge( context, writer );
		return writer.toString();
	}

	public String readErrorFileContent()
	{
		log.debug("TemplateServiceImpl :: readErrorFileContent");

		String fileLocation = Constants.COMMON_LOCATION + "error.htm";
		StringBuilder out = null;

		try
		{
			InputStream in = new FileInputStream(fileLocation);
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			out = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				out.append(line);
			}
			reader.close();
		}
		catch (Exception e)
		{
			return "";
		}

		return out.toString();
	}
}
