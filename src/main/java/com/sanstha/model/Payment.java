package com.sanstha.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "payment")
public class Payment
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "payment_id")
	private Integer paymentId;

	@Column(name = "society_id")
	private Integer societyId;

	@Column(name = "depositor_name")
	private String depositorName;

	@Column(name = "payment_date")
	private Date paymentDate;
	
	@Column(name = "amount")
	private Double amount;

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

	public Double getAmount()
	{
		return amount;
	}

	public void setAmount(Double amount)
	{
		this.amount = amount;
	}
}