package com.sanstha.util;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sanstha.exception.ServiceException;

@Service("commonUtil")
public class CommonUtil
{
	private static final Logger log = Logger.getLogger(CommonUtil.class);

	private static final Random RANDOM = new SecureRandom();
	/** Length of password. @see #generateRandomPassword() */
	public static final int PASSWORD_LENGTH = 8;

	public Date convertStringToDate(String dateString) throws ServiceException
	{
		log.debug("In Method convertStringToDate()");

		Date date = null;
		String finlaString = dateString;

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		try
		{
			date = sdf.parse(finlaString);
		}
		catch (Exception exception)
		{
			throw new ServiceException(exception.getMessage(), exception);
		}

		return date;
	}

	public String convertDateToString(Date date) throws ServiceException
	{
		log.debug("In Method formatDateToString()");

		String dateString = "";

		try
		{
			dateString = new SimpleDateFormat("dd/MM/yyyy hh:mm").format(date);
		}
		catch (Exception exception)
		{
			throw new ServiceException(exception.getMessage(), exception);
		}

		return dateString;
	}

	public boolean validPhoneNumber(String phoneNumber) throws ServiceException
	{
		log.debug("In Method validPhoneNumber()");

		if (null != phoneNumber && !"".equals(phoneNumber)
				&& phoneNumber.length() == 10)
		{
			if (phoneNumber.matches("\\d{10}")) // to verify that characters
												// entered are only digits
			{
				return true;
			}
		}

		return false;
	}

	/**
	 * Generate a random String suitable for use as a temporary password.
	 *
	 * @return String suitable for use as a temporary password
	 * @since 2.4
	 */
	public String generateRandomPassword()
	{
		// Pick from some letters that won't be easily mistaken for each
		// other. So, for example, omit o O and 0, 1 l and L.
		String letters = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789+@";

		String pw = "";
		for (int i = 0; i < PASSWORD_LENGTH; i++)
		{
			int index = (int) (RANDOM.nextDouble() * letters.length());
			pw += letters.substring(index, index + 1);
		}
		return pw;
	}
}