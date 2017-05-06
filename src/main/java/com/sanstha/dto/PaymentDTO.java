package com.sanstha.dto;

import java.util.Date;

public class PaymentDTO
{
	private Integer paymentId;

	private Integer societyId;

	private String societyName;

	private String depositorName;

	private Date paymentDate;

	private double amount;

	public Integer getPaymentId()
	{
		return paymentId;
	}

	public void setPaymentId(Integer paymentId)
	{
		this.paymentId = paymentId;
	}

	public Integer getSocietyId()
	{
		return societyId;
	}

	public void setSocietyId(Integer societyId)
	{
		this.societyId = societyId;
	}

	public String getSocietyName()
	{
		return societyName;
	}

	public void setSocietyName(String societyName)
	{
		this.societyName = societyName;
	}

	public String getDepositorName()
	{
		return depositorName;
	}

	public void setDepositorName(String depositorName)
	{
		this.depositorName = depositorName;
	}

	public Date getPaymentDate()
	{
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate)
	{
		this.paymentDate = paymentDate;
	}

	public double getAmount()
	{
		return amount;
	}

	public void setAmount(double amount)
	{
		this.amount = amount;
	}
}