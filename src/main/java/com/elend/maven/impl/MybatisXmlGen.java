package com.elend.maven.impl;

import java.io.File;

import com.elend.maven.AbstractGenerater;
import com.elend.maven.data.TableProperty;
/**
 * 生成mybatis的xml
 * @author liyongquan
 *
 */
public class MybatisXmlGen extends AbstractGenerater{
	public static String subPath="sql";
	
	public MybatisXmlGen(String projectDir,
			String basePackageName,TableProperty tableProperty) {
		super(getTargetDir(projectDir,basePackageName), tableProperty.getClassName()+".xml", "xml/sql.vm", basePackageName, null,tableProperty);
	}
	
	private static String getTargetDir(String projectDir,String basePackageName){
		int index=basePackageName.lastIndexOf(".");
		subPath+=File.separator+basePackageName.substring(index+1);
		String javaPath="src"+File.separator+"main"+File.separator+"resources";
		return projectDir+File.separator+javaPath+File.separator+subPath;
	}
}
