package com.sanstha.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sanstha.dto.SocietyRequestDTO;
import com.sanstha.exception.ServiceException;
import com.sanstha.service.SocietyService;
import com.sanstha.util.Constants;

@Controller
public class SuperAdminController
{
	@Autowired
	SocietyService societyService;

	private static final Logger log = Logger.getLogger(LoginController.class);

	@RequestMapping(value = "/authorizeSociety", method = { RequestMethod.GET })
	public ModelAndView viewSocietyRequest(HttpServletRequest request)
	{
		List<SocietyRequestDTO> societyRequestList = null;

		ModelAndView modelAndView = new ModelAndView("authorizeSociety");

		try
		{
			societyRequestList = new ArrayList<SocietyRequestDTO>();
			societyRequestList = societyService.getSocietyRequestList();
			modelAndView.addObject("societyRequestList", societyRequestList);
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

	@RequestMapping(value = "/authorize", method = { RequestMethod.POST })
	@ResponseBody
	public String authorize(HttpServletRequest request)
	{
		String result = Constants.FAILURE;

		try
		{
			result = societyService.authorize(request);
		}
		catch (ServiceException exception)
		{
			log.error("Exception : " + exception);
		}
		catch (Exception exception)
		{
			log.error("Controller Exception : " + exception);
		}
		return result;
	}
}