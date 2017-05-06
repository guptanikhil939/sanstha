package com.sanstha.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sanstha.dto.MemberDTO;
import com.sanstha.dto.MemberTypeDTO;
import com.sanstha.dto.SocietyDTO;
import com.sanstha.dto.TitleDTO;
import com.sanstha.dto.UserDTO;
import com.sanstha.exception.ServiceException;
import com.sanstha.service.MemberService;
import com.sanstha.service.SocietyService;

@Controller
public class MemberController
{
	@Autowired
	MemberService memberService;

	@Autowired
	SocietyService societyService;

	private static final Logger log = Logger.getLogger(MemberController.class);

	@RequestMapping(value = "/viewAllMembers", method = { RequestMethod.GET })
	public ModelAndView viewAllMembers(HttpServletRequest request)
	{
		UserDTO userDTO = null;
		ModelAndView modelAndView = null;
		List<MemberDTO> memberList = null;
		try
		{
			HttpSession session = request.getSession(false);
			userDTO = (UserDTO) session.getAttribute("userDTO");

			if (null != userDTO)
			{
				memberList = new ArrayList<MemberDTO>();
				memberList = memberService.getMemberList(request,
						userDTO.getSocietyId(), userDTO.getUserId());
				modelAndView = new ModelAndView("viewAllMembers");
				modelAndView.addObject("userDTO", userDTO);
				modelAndView.addObject("memberList", memberList);
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
		return modelAndView;
	}

	@RequestMapping(value = "/newMember", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView newMember(HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView("newMember");

		List<SocietyDTO> societyList = null;
		List<TitleDTO> titleList = null;
		List<MemberTypeDTO> memberTypeList = null;
		
		try
		{
			societyList = new ArrayList<SocietyDTO>();
			societyList = societyService.getSocietyList();
			titleList = memberService.getTitleList();
			memberTypeList = memberService.getMemberTypeList();
			
			modelAndView.addObject("societyList", societyList);
			modelAndView.addObject("titleList", titleList);
			modelAndView.addObject("memberTypeList", memberTypeList);
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

	@RequestMapping(value = "/createMember", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public String createMember(HttpServletRequest request)
	{
		String result = "false";

		try
		{
			boolean memberCreated = memberService.createMember(request);

			if (memberCreated)
			{
				result = "success";
			}
			else
			{
				result = "fail";
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

		return result;
	}

	@RequestMapping(value = "/viewMember", method = { RequestMethod.POST })
	public ModelAndView viewMember(HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView("viewMember");
		UserDTO member = null;

		try
		{
			Integer userId = Integer.parseInt((String) (request
					.getParameter("memberId") == null ? 0 : request
					.getParameter("memberId")));

			member = new UserDTO();
			member = memberService.viewMember(request, userId);
			modelAndView.addObject("member", member);
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