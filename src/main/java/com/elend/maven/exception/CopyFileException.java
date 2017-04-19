package com.elend.maven.exception;

/**
 * 拷贝文件
 * @author liyongquan
 *
 */
public class CopyFileException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4776884528646896976L;
	public CopyFileException(){
	}
	public CopyFileException(Exception e){
		super(e);
	}
	public CopyFileException(String msg){
		super(msg);
	}
}
