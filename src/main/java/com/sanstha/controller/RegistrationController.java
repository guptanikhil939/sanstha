package com.sanstha.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sanstha.exception.PersistenceException;
import com.sanstha.exception.ServiceException;
import com.sanstha.service.RegistarationService;

@Controller
public class RegistrationController
{
	@Autowired
	RegistarationService registrationService;
	
	private static final Logger log = Logger.getLogger(RegistrationController.class);
	
	@RequestMapping(value = "/registration", method = { RequestMethod.POST })
	@ResponseBody
	public String registaration(HttpServletRequest request)
	{
		log.debug("RegistrationController :: registaration(request)");
		
		String result;
		
		try
		{
			result = registrationService.registerAdmin(request);
		}
		catch (ServiceException exception)
		{
			log.error("Exception : " + exception);
			return "fail";
		}
		catch (Exception exception)
		{
			log.error("Controller Exception : " + exception);
			return "fail";
		}

		if (null!=result && result.equals("success"))
		{
			return "success";
		}
		else
		{
			return result;
		}
	}
}