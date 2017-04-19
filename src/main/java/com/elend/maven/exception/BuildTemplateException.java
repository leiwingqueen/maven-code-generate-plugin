package com.elend.maven.exception;

/**
 * 构建模板异常	
 * @author liyongquan
 *
 */
public class BuildTemplateException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6092377066098019849L;
	
	public BuildTemplateException(){
		
	}
	public BuildTemplateException(Exception e){
		super(e);
	}
	public BuildTemplateException(String msg){
		super(msg);
	}

}
