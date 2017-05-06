package com.sanstha.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sanstha.dto.SocietyDTO;
import com.sanstha.exception.ServiceException;
import com.sanstha.model.Society;
import com.sanstha.service.SocietyService;
import com.sanstha.service.TemplateService;

@Controller
public class SocietyController
{

	@Autowired
	SocietyService societyService;

	private static final Logger log = Logger.getLogger(HomeController.class);

	@RequestMapping(value = "/viewAllSocieties", method = RequestMethod.GET)
	public ModelAndView viewAllSocieties()
	{
		log.debug("SocietyController :: viewAllSocieties()");

		ModelAndView model = new ModelAndView("viewAllSocieties");

		try
		{
			List<SocietyDTO> societyList = societyService.getSocietyList();
			model.addObject("societyList", societyList);
		}
		catch (ServiceException exception)
		{
			log.error("Exception : " + exception);
		}
		catch (Exception exception)
		{
			log.error("Controller Exception : " + exception);
		}
		return model;
	}
}