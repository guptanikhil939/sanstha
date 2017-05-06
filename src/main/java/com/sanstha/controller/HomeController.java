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

import com.sanstha.dto.DistrictDTO;
import com.sanstha.dto.SocietyDTO;
import com.sanstha.exception.ServiceException;
import com.sanstha.model.Society;
import com.sanstha.service.SocietyService;
import com.sanstha.service.TemplateService;

@Controller
public class HomeController
{
	@Autowired
	TemplateService templateService;

	@Autowired
	SocietyService societyService;

	private static final Logger log = Logger.getLogger(HomeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView getdata()
	{
		log.debug("HomeController :: getdata()");

		ModelAndView model = new ModelAndView("index");

		try
		{
			List<SocietyDTO> societyList = societyService.getSocietyList();
			List<DistrictDTO> districtList = societyService.getDistrictList();
			
			model.addObject("societyList", societyList);
			model.addObject("districtList", districtList);
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

	@RequestMapping(value = "/footer/{page}", method = RequestMethod.GET)
	public ModelAndView openPage(@PathVariable("page") String page)
	{
		log.debug("HomeController :: openPage()");

		ModelAndView model = new ModelAndView(page);
		return model;
	}

	@RequestMapping(value = "/society/{siteWebName}", method = RequestMethod.GET)
	@ResponseBody
	public String openWebSite(@PathVariable("siteWebName") String siteWebName)
	{
		log.debug("HomeController :: openWebSite");
		String templateContent = null;
		try
		{
			Society soceity = societyService.getSocietyDetails(siteWebName);
			templateContent = templateService.getTemplate(soceity);
		}
		catch (ServiceException exception)
		{
			log.error("Exception : " + exception);
		}
		catch (Exception exception)
		{
			log.error("Controller Exception : " + exception);
		}

		return templateContent;
	}

	@RequestMapping(value = "/society/{siteWebName}/{otherPage}", method = RequestMethod.GET)
	@ResponseBody
	public String openWebSiteOtherLinks(
			@PathVariable("siteWebName") String siteWebName,
			@PathVariable("otherPage") String otherPage,
			HttpServletRequest request)
	{
		log.debug("HomeController :: openWebSiteOtherLinks");
		String templateContent = null;

		try
		{
			Society soceity = societyService.getSocietyDetails(siteWebName);
			templateContent = templateService.getTemplate(soceity, otherPage);
		}
		catch (ServiceException exception)
		{
			log.error("Exception : " + exception);
		}
		catch (Exception exception)
		{
			log.error("Controller Exception : " + exception);
		}

		return templateContent;
	}
}