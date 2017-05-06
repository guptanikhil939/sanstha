package com.sanstha.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sanstha.dao.PaymentDAO;
import com.sanstha.exception.PersistenceException;
import com.sanstha.model.Payment;

@Repository("paymentDAO")
public class PaymentDAOImpl implements PaymentDAO
{
	@Autowired
	private SessionFactory sessionFactory;

	private static final Logger log = Logger.getLogger(PaymentDAOImpl.class);

	public SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}

	private Session getCurrentSession()
	{
		return sessionFactory.getCurrentSession();
	}

	@Override
	public boolean addPayment(Payment payment) throws PersistenceException
	{
		try
		{
			getCurrentSession().save(payment);

			return true;
		}
		catch (HibernateException exception)
		{
			throw new PersistenceException(exception.getMessage(), exception);
		}
	}

	@Override
	public List<Payment> getAllPayments() throws PersistenceException
	{
		log.debug("PaymentDAOImpl :: getPaymentList");

		List<Payment> paymentList = null;

		try
		{
			Criteria cr = getCurrentSession().createCriteria(Payment.class);
			paymentList = (List<Payment>) cr.list();
		}
		catch (HibernateException exception)
		{
			throw new PersistenceException(exception.getMessage(), exception);
		}
		catch (Exception exception)
		{
			throw new PersistenceException(exception.getMessage(), exception);
		}

		return paymentList;
	}

	@Override
	public List<Payment> getPayments(Integer societyId)
			throws PersistenceException
	{
		log.debug("PaymentDAOImpl :: getPayments");

		List<Payment> paymentList = null;

		try
		{
			Criteria cr = getCurrentSession().createCriteria(Payment.class);
			cr.add(Restrictions.eq("societyId", societyId));
			paymentList = (List<Payment>) cr.list();
		}
		catch (HibernateException exception)
		{
			throw new PersistenceException(exception.getMessage(), exception);
		}
		catch (Exception exception)
		{
			throw new PersistenceException(exception.getMessage(), exception);
		}

		return paymentList;
	}
}