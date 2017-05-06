package com.sanstha.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MessageController
{

	@RequestMapping(value = "/inbox", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView inbox(HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView("inbox");

		return modelAndView;
	}
	
	@RequestMapping(value = "/sendMessage", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView sendMessage(HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView("sendMessage");

		return modelAndView;
	}
}