package com.pshop.exception_handling;

public class SqlException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6071155634694157418L;

	public SqlException() {
		super();
	}
	
	public SqlException(final String message) {
		super(message);
	}
}
