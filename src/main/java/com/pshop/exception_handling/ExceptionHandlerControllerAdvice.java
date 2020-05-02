package com.pshop.exception_handling;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {
	
	@ExceptionHandler(SqlException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ExceptionResponse handleSqlException(final SqlException exception,
			final HttpServletRequest request) {
	ExceptionResponse error = new ExceptionResponse();
	error.setErrorMessage(exception.getMessage());
	error.callerURL(request.getRequestURI());

	return error;
	}
}
