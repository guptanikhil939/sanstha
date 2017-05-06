package com.sanstha.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sanstha.dto.EventDTO;
import com.sanstha.dto.MemberDTO;
import com.sanstha.dto.UserDTO;
import com.sanstha.exception.PersistenceException;
import com.sanstha.exception.ServiceException;
import com.sanstha.model.Society;
import com.sanstha.service.LoginService;
import com.sanstha.service.MemberService;
import com.sanstha.service.ProfileService;
import com.sanstha.service.SocietyService;
import com.sanstha.util.Constants;

@Controller
public class LoginController
{
	@Autowired
	LoginService loginService;

	@Autowired
	ProfileService profileService;

	@Autowired
	MemberService memberService;
	
	@Autowired
	SocietyService societyService;

	private static final Logger log = Logger.getLogger(LoginController.class);

	@RequestMapping(value = "/loginAuth", method = { RequestMethod.POST })
	@ResponseBody
	public String loginAuth(HttpServletRequest request)
	{
		log.debug("LoginController :: loginAuth()");

		boolean result;

		try
		{
			result = loginService.loginAuth(request);

			if (result)
			{
				return "success";
			}
			else
			{
				return "fail";
			}
		}
		catch (PersistenceException exception)
		{
			log.error("Persistence Exception : " + exception);
		}
		catch (ServiceException exception)
		{
			log.error("Service Exception : " + exception);
		}
		catch (Exception exception)
		{
			log.error("Controller Exception : " + exception);
		}

		return "fail";
	}

	@RequestMapping(value = "/home", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView login(HttpServletRequest request)
	{
		log.debug("LoginController :: login()");

		UserDTO userDTO = null;
		ModelAndView modelAndView = null;
		List<MemberDTO> memberList = null;
		List<EventDTO> eventList = null;
		try
		{
			HttpSession session = request.getSession(false);
			userDTO = (UserDTO) session.getAttribute("userDTO");

			if (null != userDTO && null != userDTO.getRoleId()
					&& userDTO.getRoleId().equals(Constants.SUPER_ADMIN_ROLE))
			{
				userDTO = loginService.login(request, userDTO);
				modelAndView = new ModelAndView("superAdminHome");
			}
			else if (null != userDTO && null != userDTO.getRoleId()
					&& userDTO.getRoleId().equals(Constants.ADMIN_ROLE))
			{
				userDTO = loginService.login(request, userDTO);

				eventList = new ArrayList<EventDTO>();
				eventList = loginService.getEventList(request,
						userDTO.getSocietyId());

				memberList = new ArrayList<MemberDTO>();

				memberList = memberService.getMemberList(request,
						userDTO.getSocietyId(), userDTO.getUserId());

				modelAndView = new ModelAndView("adminHome");

				modelAndView.addObject("userDTO", userDTO);
				modelAndView.addObject("memberList", memberList);
				modelAndView.addObject("eventList", eventList);
			}
			else if (null != userDTO && null != userDTO.getRoleId()
					&& userDTO.getRoleId().equals(Constants.MEMBER_ROLE))
			{
				userDTO = loginService.login(request, userDTO);

				eventList = new ArrayList<EventDTO>();
				eventList = loginService.getEventList(request,
						userDTO.getSocietyId());

				memberList = new ArrayList<MemberDTO>();

				memberList = memberService.getMemberList(request,
						userDTO.getSocietyId(), userDTO.getUserId());

				modelAndView = new ModelAndView("memberHome");

				modelAndView.addObject("userDTO", userDTO);
				modelAndView.addObject("memberList", memberList);
				modelAndView.addObject("eventList", eventList);
			}
			else
			{
				modelAndView = new ModelAndView("error");
			}
		}
		catch (PersistenceException exception)
		{
			log.error("Persistence Exception : " + exception);
		}
		catch (ServiceException exception)
		{
			log.error("Service Exception : " + exception);
		}
		catch (Exception exception)
		{
			log.error("Controller Exception : " + exception);
		}

		return modelAndView;
	}
	
	@RequestMapping(value = "/editWebPortal", method = RequestMethod.GET)
	public String editWebsitePortal(HttpServletRequest request,Model model)
	{
		log.debug("LoginController :: editWebsitePortal()");

		UserDTO userDTO = null;
		ModelAndView modelAndView = null;
		List<MemberDTO> memberList = null;
		List<EventDTO> eventList = null;
		try
		{
			HttpSession session = request.getSession(false);
			userDTO = (UserDTO) session.getAttribute("userDTO");

			if (null != userDTO && null != userDTO.getRoleId()
					&& userDTO.getRoleId().equals(Constants.ADMIN_ROLE))
			{
				Society society = societyService.getSocietyDetails(userDTO.getSocietyId());
				if(society!=null)
				model.addAttribute("society", society);
				/*userDTO = loginService.login(request, userDTO);

				eventList = new ArrayList<EventDTO>();
				eventList = loginService.getEventList(request,
						userDTO.getSocietyId());

				memberList = new ArrayList<MemberDTO>();

				memberList = memberService.getMemberList(request,
						userDTO.getSocietyId(), userDTO.getUserId());*/

				modelAndView = new ModelAndView();

				modelAndView.addObject("userDTO", userDTO);
				modelAndView.addObject("memberList", memberList);
				modelAndView.addObject("eventList", eventList);
			}
			else
			{
				modelAndView = new ModelAndView("error");
			}
		}
		
		catch (ServiceException exception)
		{
			log.error("Service Exception : " + exception);
		}
		catch (Exception exception)
		{
			log.error("Controller Exception : " + exception);
		}

		return "editportal";
	}
	
	@RequestMapping(value = "/saveWebPortal", method = RequestMethod.POST)
	public String updateWebsitePortal(HttpServletRequest request,@Valid Society society, 
			BindingResult result, Model model)
	{
		log.debug("LoginController :: updateWebsitePortal()");
		
		if(result.hasErrors()){
			return "editportal";
		}
		
		else{
		UserDTO userDTO = null;
		ModelAndView modelAndView = null;
		List<MemberDTO> memberList = null;
		List<EventDTO> eventList = null;
		try
		{
			HttpSession session = request.getSession(false);
			userDTO = (UserDTO) session.getAttribute("userDTO");

			if (null != userDTO && null != userDTO.getRoleId()
					&& userDTO.getRoleId().equals(Constants.ADMIN_ROLE))
			{
				society.setUserId(userDTO.getUserId());
				society.setSocietyId(userDTO.getSocietyId());
				boolean value = societyService.saveSociety(society);
				if(value==true)
				model.addAttribute("message", "Web Portal updated sucessfully");
				else
				model.addAttribute("message", "Cannot update portal at this time. please try again later");
				modelAndView = new ModelAndView();
				model.addAttribute("society", society);
				modelAndView.addObject("userDTO", userDTO);
				modelAndView.addObject("memberList", memberList);
				modelAndView.addObject("eventList", eventList);
			}
			else
			{
				modelAndView = new ModelAndView("error");
			}
		}
		
		catch (Exception exception)
		{
			log.error("Controller Exception : " + exception);
		}

		return "editportal";
		}
	}
}