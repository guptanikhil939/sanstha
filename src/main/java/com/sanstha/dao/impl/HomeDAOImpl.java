package com.sanstha.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sanstha.dao.HomeDAO;

@Transactional
@Repository("homeDAO")
public class HomeDAOImpl implements HomeDAO
{
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger log = Logger.getLogger(HomeDAOImpl.class);

	public SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}

	private Session getCurrentSession()
	{
		return sessionFactory.getCurrentSession();
	}
}
