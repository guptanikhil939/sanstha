package com.sanstha.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sanstha.dto.PaymentDTO;
import com.sanstha.exception.PersistenceException;
import com.sanstha.exception.ServiceException;

public interface PaymentService
{
	public String addPayment(HttpServletRequest request)
			throws PersistenceException, ServiceException;

	public List<PaymentDTO> getAllPayments() throws PersistenceException,
			ServiceException;

	public List<PaymentDTO> getPayments(HttpServletRequest request)
			throws PersistenceException, ServiceException;
}
