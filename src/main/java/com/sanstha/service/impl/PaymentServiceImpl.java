package com.sanstha.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanstha.dao.PaymentDAO;
import com.sanstha.dao.SocietyDAO;
import com.sanstha.dto.PaymentDTO;
import com.sanstha.dto.UserDTO;
import com.sanstha.exception.PersistenceException;
import com.sanstha.exception.ServiceException;
import com.sanstha.model.Payment;
import com.sanstha.model.Society;
import com.sanstha.service.PaymentService;
import com.sanstha.util.CommonUtil;
import com.sanstha.util.Constants;

@Transactional
@Service("paymentService")
public class PaymentServiceImpl implements PaymentService
{
	@Autowired
	CommonUtil commonUtil;

	@Autowired
	PaymentDAO paymentDAO;

	@Autowired
	SocietyDAO societyDAO;

	private static final Logger log = Logger
			.getLogger(PaymentServiceImpl.class);

	public String addPayment(HttpServletRequest request)
			throws PersistenceException, ServiceException
	{
		log.debug("PaymentServiceImpl :: addPayment");

		PaymentDTO paymentDTO = null;
		String result = Constants.FAILURE;

		try
		{

			String societyId = request.getParameter("societyId");
			String from = request.getParameter("from");
			String paymentDate = request.getParameter("paymentDate");
			String amount = request.getParameter("amount");

			Date newPaymentDate = commonUtil.convertStringToDate(paymentDate);

			if (null != societyId)
			{
				paymentDTO = new PaymentDTO();
				paymentDTO.setSocietyId(Integer.parseInt(societyId));
				paymentDTO.setDepositorName(from);
				paymentDTO.setAmount(new Double(amount));
				paymentDTO.setPaymentDate(new Date(paymentDate));

				Payment payment = new Payment();
				payment.setSocietyId(paymentDTO.getSocietyId());
				payment.setDepositorName(paymentDTO.getDepositorName());
				payment.setPaymentDate(paymentDTO.getPaymentDate());
				payment.setAmount(paymentDTO.getAmount());

				boolean paymentAdded = paymentDAO.addPayment(payment);

				if (paymentAdded)
				{
					return result = Constants.SUCCESS;
				}
			}
		}
		catch (PersistenceException exception)
		{
			throw new PersistenceException(exception.getMessage(), exception);
		}
		catch (Exception exception)
		{
			throw new ServiceException(exception.getMessage(), exception);
		}

		return result;
	}

	@Override
	public List<PaymentDTO> getAllPayments() throws PersistenceException,
			ServiceException
	{
		log.debug("PaymentServiceImpl :: getPaymentList");

		List<PaymentDTO> paymentDTOList = new ArrayList<PaymentDTO>();

		try
		{
			List<Payment> paymentList = paymentDAO.getAllPayments();

			for (Payment payment : paymentList)
			{
				PaymentDTO paymentDTO = new PaymentDTO();
				paymentDTO.setPaymentId(payment.getPaymentId());
				paymentDTO.setDepositorName(payment.getDepositorName());
				paymentDTO.setPaymentDate(payment.getPaymentDate());
				paymentDTO.setAmount(payment.getAmount());
				paymentDTO.setSocietyId(payment.getSocietyId());

				Society societyDetails = societyDAO
						.getSocietyDetails(paymentDTO.getSocietyId());
				String societyName = societyDetails.getSocietyName();

				paymentDTO.setSocietyName(societyName);
				paymentDTOList.add(paymentDTO);
			}
		}
		catch (PersistenceException exception)
		{
			throw new ServiceException(exception.getMessage(), exception);
		}
		catch (Exception exception)
		{
			throw new ServiceException(exception.getMessage(), exception);
		}

		return paymentDTOList;
	}

	@Override
	public List<PaymentDTO> getPayments(HttpServletRequest request)
			throws PersistenceException, ServiceException
	{
		log.debug("PaymentServiceImpl :: getPayments");

		List<PaymentDTO> paymentDTOList = new ArrayList<PaymentDTO>();

		try
		{

			HttpSession session = request.getSession(false);
			UserDTO userDTO = (UserDTO) session.getAttribute("userDTO");

			if (null != userDTO)
			{

				List<Payment> paymentList = paymentDAO.getPayments(userDTO
						.getSocietyId());

				for (Payment payment : paymentList)
				{
					PaymentDTO paymentDTO = new PaymentDTO();
					paymentDTO.setPaymentId(payment.getPaymentId());
					paymentDTO.setDepositorName(payment.getDepositorName());
					paymentDTO.setPaymentDate(payment.getPaymentDate());
					paymentDTO.setAmount(payment.getAmount());
					paymentDTO.setSocietyId(payment.getSocietyId());

					Society societyDetails = societyDAO
							.getSocietyDetails(paymentDTO.getSocietyId());
					String societyName = societyDetails.getSocietyName();

					paymentDTO.setSocietyName(societyName);
					paymentDTOList.add(paymentDTO);
				}
			}
		}
		catch (PersistenceException exception)
		{
			throw new ServiceException(exception.getMessage(), exception);
		}
		catch (Exception exception)
		{
			throw new ServiceException(exception.getMessage(), exception);
		}

		return paymentDTOList;
	}

}
