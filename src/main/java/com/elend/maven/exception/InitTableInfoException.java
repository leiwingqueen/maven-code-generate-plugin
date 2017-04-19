package com.elend.maven.exception;

public class InitTableInfoException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -164489861696999547L;
	public InitTableInfoException(){}
	public InitTableInfoException(String msg){
		super(msg);
	}
	public InitTableInfoException(Exception e){
		super(e);
	}

}
