package com.sanstha.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FooterController
{

	@RequestMapping(value = "/footer", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView footer(HttpServletRequest request)
	{
		ModelAndView modelAndView = null;
		if(request==null){
			System.out.println(" rqst is null");
		}
		if (request.getParameter("footerButton").equals("A"))
		{
			modelAndView = new ModelAndView("aboutUs");
		} else
		{
			modelAndView = new ModelAndView("contactUs");
		}
		return modelAndView;
	}
}