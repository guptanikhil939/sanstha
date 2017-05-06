package com.sanstha.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanstha.dao.HomeDAO;
import com.sanstha.service.HomeService;

@Service("homeService")
public class HomeServiceImpl implements HomeService
{
	@Autowired
	HomeDAO homeDAO;
	
	private static final Logger log = Logger.getLogger(HomeServiceImpl.class);

}
