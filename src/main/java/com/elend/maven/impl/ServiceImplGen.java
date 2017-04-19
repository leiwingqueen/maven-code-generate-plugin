package com.elend.maven.impl;

import java.io.File;

import com.elend.maven.AbstractGenerater;
import com.elend.maven.data.TableProperty;
/**
 * 生成Service
 * @author liyongquan
 *
 */
public class ServiceImplGen extends AbstractGenerater{
	public static final String subPath="service"+File.separator+"impl";
	
	public ServiceImplGen(String projectDir,
			String basePackageName,TableProperty tableProperty) {
		super(getTargetDir(projectDir,basePackageName), tableProperty.getClassName()+"ServiceImpl.java", "java/ServiceImpl.vm", basePackageName, null,tableProperty);
	}
	
	private static String getTargetDir(String projectDir,String basePackageName){
		String javaPath="src"+File.separator+"main"+File.separator+"java";
		return projectDir+File.separator+javaPath+File.separator+basePackageName.replace(".",File.separator)+File.separator+subPath;
	}
}
