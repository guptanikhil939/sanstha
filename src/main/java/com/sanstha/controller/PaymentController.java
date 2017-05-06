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

import com.sanstha.dto.PaymentDTO;
import com.sanstha.dto.SocietyDTO;
import com.sanstha.exception.ServiceException;
import com.sanstha.service.MemberService;
import com.sanstha.service.PaymentService;
import com.sanstha.service.SocietyService;
import com.sanstha.util.Constants;

@Controller
public class PaymentController
{
	@Autowired
	PaymentService paymentService;

	@Autowired
	MemberService memberService;

	@Autowired
	SocietyService societyService;

	private static final Logger log = Logger.getLogger(PaymentController.class);

	@RequestMapping(value = "/addPaymentPage", method = { RequestMethod.GET })
	public ModelAndView addPaymentPage(HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView("addPaymentPage");

		List<SocietyDTO> societyList = null;
		try
		{
			societyList = new ArrayList<SocietyDTO>();
			societyList = societyService.getSocietyList();
			modelAndView.addObject("societyList", societyList);
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

	@RequestMapping(value = "/addPayment", method = { RequestMethod.POST })
	@ResponseBody
	public String addPayment(HttpServletRequest request)
	{
		String result = Constants.FAILURE;

		try
		{
			result = paymentService.addPayment(request);
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

	@RequestMapping(value = "/viewAllPayments", method = { RequestMethod.GET })
	public ModelAndView viewAllPayments(HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView("viewAllPayments");

		List<PaymentDTO> paymentList = null;
		try
		{
			paymentList = new ArrayList<PaymentDTO>();
			paymentList = paymentService.getAllPayments();
			modelAndView.addObject("paymentList", paymentList);
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

	@RequestMapping(value = "/viewPayments", method = { RequestMethod.GET })
	public ModelAndView viewPayments(HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView("viewAllPayments");

		List<PaymentDTO> paymentList = null;
		try
		{
			paymentList = new ArrayList<PaymentDTO>();
			paymentList = paymentService.getPayments(request);
			modelAndView.addObject("paymentList", paymentList);
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
}