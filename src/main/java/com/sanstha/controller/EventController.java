package com.sanstha.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sanstha.dto.EventDTO;
import com.sanstha.dto.UserDTO;
import com.sanstha.exception.ServiceException;
import com.sanstha.service.EventService;
import com.sanstha.util.Constants;

@Controller
public class EventController
{
	@Autowired
	EventService eventService;
	
	@Autowired
	VelocityEngine velocity;
	
	private static final Logger log = Logger.getLogger(EventController.class);

	@RequestMapping(value = "/createEvent", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public String createEvent(HttpServletRequest request)
	{
		try
		{
			boolean result = eventService.createEvent(request);

			if (result)
			{
				return Constants.SUCCESS;
			}
		}
		catch (ServiceException exception)
		{
			log.error("Exception : " + exception);
		}
		catch (Exception exception)
		{
			log.error("Controller Exception : " + exception);
		}

		return Constants.FAILURE;
	}

	
	@RequestMapping(value = "/viewEvent", method = { RequestMethod.POST })
	public ModelAndView viewEvent(HttpServletRequest request, Model model)
	{
		UserDTO userDTO = null;
		ModelAndView modelAndView = null;
		EventDTO event = null;
		List<EventDTO> eventList = null;
		List<String> imagePaths = null;

		try
		{
			HttpSession session = request.getSession(false);
			userDTO = (UserDTO) session.getAttribute("userDTO");

			if (null != userDTO)
			{
				event = eventService.viewEvent(request);

				eventList = new ArrayList<EventDTO>();
				eventList = eventService.getEventList(request,
						userDTO.getSocietyId());
				File file = new File(Constants.EVENT_IMAGES_PATH + "/"
						+ event.getEventId());
				if (file.exists() && file.isDirectory())
				{
					imagePaths = new ArrayList<String>();
					File[] files = file.listFiles();
					Arrays.sort(files,
							LastModifiedFileComparator.LASTMODIFIED_REVERSE);
					for (File list : files)
					{
						String path = file.getPath() + "\\" + list.getName();
						path = path.replace("\\", "/");
						path = path.substring(1);
						if (path.contains(".png") || path.contains(".jpg")
								|| path.contains(".gif")
								|| path.contains(".jpeg"))
							imagePaths.add(path);
					}
				}

				modelAndView = new ModelAndView("viewEvent");
				modelAndView.addObject("userDTO", userDTO);
				modelAndView.addObject("event", event);
				modelAndView.addObject("eventList", eventList);
				modelAndView.addObject("imagePaths", imagePaths);
			}
		}
		catch (ServiceException exception)
		{
			log.error("Exception : " + exception);
		}
		catch (Exception exception)
		{
			log.error("Controller Exception : " + exception);
		}
		return modelAndView;
	}

	@RequestMapping(value = "/viewEvent/upload", method = { RequestMethod.POST })
	@ResponseBody
	public String viewEventImageUpload(HttpServletRequest request,
			@RequestParam(value = "files") List<MultipartFile> fileUpload,
			HttpSession session,
			@RequestParam(value = "eventobj") String eventId)
	{
		UserDTO userDTO = null;
		String resp = "";
		try
		{
			// HttpSession session = request.getSession(false);
			userDTO = (UserDTO) session.getAttribute("userDTO");

			if (null != userDTO)
			{
				File file = new File(Constants.EVENT_IMAGES_PATH + "/"
						+ eventId);
				if (!file.exists())
					file.mkdirs();
				if (null != fileUpload && fileUpload.size() > 0)
				{
					for (MultipartFile multipartFile : fileUpload)
					{

						String fileName = multipartFile.getOriginalFilename();
						byte[] fileBytes = multipartFile.getBytes();
						if (fileName.contains(".png")
								|| fileName.contains(".jpg")
								|| fileName.contains(".gif")
								|| fileName.contains(".jpeg"))
						{
							int index = fileName.lastIndexOf(".");
							fileName = new Random().nextInt()
									+ fileName.substring(index);
							File imageFile = new File(file.getAbsolutePath()
									+ "/" + fileName);
							if (imageFile.exists())
							{
								fileName = new Random().nextInt()
										+ fileName.substring(index);
								imageFile = new File(file.getAbsolutePath()
										+ "/" + fileName);
							}
							FileOutputStream stream = new FileOutputStream(
									imageFile);
							stream.write(fileBytes);
							stream.close();
						}
					}
				}

				// Now send the latest files from directory
				File[] filesInDirectory = file.listFiles();
				int size = filesInDirectory.length;
				String path = file.getPath();
				Arrays.sort(filesInDirectory,
						LastModifiedFileComparator.LASTMODIFIED_REVERSE);
				List<String> imageFiles = new ArrayList<String>();
				for (int i = 0; i < size && imageFiles.size() < 12; i++)
				{
					String imagePath = path + "\\"
							+ filesInDirectory[i].getName();
					imagePath = imagePath.replace("\\", "/");
					if (imagePath.contains(".png")
							|| imagePath.contains(".jpg")
							|| imagePath.contains(".gif")
							|| imagePath.contains(".jpeg"))
					{
						imageFiles.add(imagePath);
					}
				}

				// Build the image div tag template
				Template template = null;
				velocity.init();
				VelocityContext context = new VelocityContext();
				template = velocity.getTemplate("imagesDiv.vm");
				context.put("imagepaths", imageFiles);
				StringWriter writer = new StringWriter();
				template.merge(context, writer);
				return writer.toString();

			}
		}
//		catch (ServiceException exception)
//		{
//			log.error("Exception : " + exception);
//			return "error";
//		}
		catch (Exception exception)
		{
			log.error("Controller Exception : " + exception);
			return "error";
		}
		return resp;
	}

	@RequestMapping(value = "/viewAllEvents", method = { RequestMethod.GET })
	public ModelAndView viewAllEvents(HttpServletRequest request)
	{
		UserDTO userDTO = null;
		ModelAndView modelAndView = null;
		List<EventDTO> eventList = null;

		try
		{
			HttpSession session = request.getSession(false);
			userDTO = (UserDTO) session.getAttribute("userDTO");

			if (null != userDTO)
			{
				eventList = new ArrayList<EventDTO>();
				eventList = eventService.getEventList(request,
						userDTO.getSocietyId());

				modelAndView = new ModelAndView("viewAllEvents");

				modelAndView.addObject("userDTO", userDTO);
				modelAndView.addObject("eventList", eventList);
			}
		}
		catch (ServiceException exception)
		{
			log.error("Exception : " + exception);
		}
		catch (Exception exception)
		{
			log.error("Controller Exception : " + exception);
		}
		return modelAndView;
	}

	@RequestMapping(value = "/viewEvent/viewgallery", method = { RequestMethod.GET })
	@ResponseBody
	public String viewEventFullGallery(HttpServletRequest request,
			@RequestParam("eventid") String eventId)
	{

		String resp = "error";
		List<String> imagePaths = null;

		try
		{
			File file = new File(Constants.EVENT_IMAGES_PATH + "/" + eventId);
			if (file.exists() && file.isDirectory())
			{
				imagePaths = new ArrayList<String>();
				File[] files = file.listFiles();
				Arrays.sort(files,
						LastModifiedFileComparator.LASTMODIFIED_REVERSE);
				for (File list : files)
				{
					String path = file.getPath() + "\\" + list.getName();
					path = path.replace("\\", "/");
					path = path.substring(1);
					if (path.contains(".png") || path.contains(".jpg")
							|| path.contains(".gif") || path.contains(".jpeg"))
						imagePaths.add(path);
				}

				// Build the image div tag template
				Template template = null;
				velocity.init();
				VelocityContext context = new VelocityContext();
				template = velocity.getTemplate("imagesDiv.vm");
				context.put("imagepaths", imagePaths);
				StringWriter writer = new StringWriter();
				template.merge(context, writer);
				resp = writer.toString();
			}
		}
//		catch (ServiceException exception)
//		{
//			log.error("Exception : " + exception);
//		}
		catch (Exception exception)
		{
			log.error("Controller Exception : " + exception);
		}
		return resp;
	}
}