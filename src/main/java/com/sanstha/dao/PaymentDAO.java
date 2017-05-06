package com.sanstha.dao;

import java.util.List;

import com.sanstha.exception.PersistenceException;
import com.sanstha.model.Payment;

public interface PaymentDAO
{
	public boolean addPayment(Payment payment) throws PersistenceException;

	public List<Payment> getAllPayments() throws PersistenceException;

	public List<Payment> getPayments(Integer societyId)
			throws PersistenceException;

}
