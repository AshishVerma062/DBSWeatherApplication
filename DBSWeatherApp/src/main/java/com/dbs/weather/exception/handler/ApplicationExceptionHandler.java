package com.dbs.weather.exception.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.dbs.weather.exception.ApplicationException;

@ControllerAdvice(basePackages = "com.maltem.controllers")
public class ApplicationExceptionHandler {

	private static final Logger logger = LogManager.getLogger(ApplicationExceptionHandler.class);

	@ExceptionHandler(ApplicationException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ModelAndView handleApplicationException(ApplicationException ex) {
		logger.error("ApplicationExceptionHandler --> " + ex.getMessage());
		ModelAndView model = new ModelAndView();
		model.setViewName("error");
		logger.info("ApplicationExceptionHandler --> redirecting to error page.");
		return model;
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ModelAndView handleException(Exception ex) {
		logger.error("ApplicationExceptionHandler --> " + ex.getMessage());
		ModelAndView model = new ModelAndView();
		model.setViewName("error");
		logger.info("ApplicationExceptionHandler --> redirecting to error page.");
		return model;
	}

}
