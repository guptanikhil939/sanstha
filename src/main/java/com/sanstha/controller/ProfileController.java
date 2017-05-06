package com.sanstha.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sanstha.dto.UserDTO;
import com.sanstha.exception.PersistenceException;
import com.sanstha.exception.ServiceException;
import com.sanstha.service.MemberService;
import com.sanstha.service.ProfileService;
import com.sanstha.util.Constants;

@Controller
public class ProfileController
{
	@Autowired
	ProfileService profileService;

	@Autowired
	MemberService memberService;

	private static final Logger log = Logger.getLogger(ProfileController.class);

	@RequestMapping(value = "/updateProfile", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public String updateProfile(HttpServletRequest request)
	{
		try
		{
			boolean result = profileService.updateInfo(request);

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

	@RequestMapping(value = "/updatePhoto", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public String updatePhoto(HttpServletRequest request,
			@RequestParam(value = "fileUpload") MultipartFile fileUpload)
	{
		try
		{
			boolean result = profileService.updatePhoto(request, fileUpload);

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

	@RequestMapping(value = "/updateAbout", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public String updateAbout(HttpServletRequest request)
	{
		try
		{
			boolean result = profileService.updateAbout(request);

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

	@RequestMapping(value = "/updateHobbies", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public String updateHobbies(HttpServletRequest request)
	{
		try
		{
			boolean result = profileService.updateHobbies(request);

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
	
	@RequestMapping(value = "/saveArticle", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public String saveArticle(HttpServletRequest request)
	{
		try
		{
			boolean result = profileService.saveArticle(request);

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

	@RequestMapping(value = "/myProfile", method = { RequestMethod.GET })
	public ModelAndView myProfile(HttpServletRequest request)
	{
		UserDTO userDTO = null;
		ModelAndView modelAndView = new ModelAndView("myProfile");

		try
		{
			userDTO = profileService.myProfile(request);
			modelAndView.addObject("userDTO", userDTO);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return modelAndView;
	}
}