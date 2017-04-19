package com.elend.maven;

import java.util.ArrayList;
import java.util.List;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import com.elend.maven.data.TableProperty;
import com.elend.maven.exception.BuildTemplateException;
import com.elend.maven.exception.CopyFileException;
import com.elend.maven.exception.InitTableInfoException;
import com.elend.maven.impl.ControllerGen;
import com.elend.maven.impl.MappperGen;
import com.elend.maven.impl.ModelGen;
import com.elend.maven.impl.MybatisXmlGen;
import com.elend.maven.impl.SearchVoGen;
import com.elend.maven.impl.ServiceGen;
import com.elend.maven.impl.ServiceImplGen;
import com.elend.maven.impl.VoGen;

/**
 * @goal generate
 * @author liyongquan
 *
 */
public class GeneraterMojo extends AbstractMojo{
	/**
	 * @parameter expression="${generate.packageName}" default-value="${packageName}"
	 * @required
	 */
	private String packageName;
	/**
	 * @parameter expression="${generate.url}" default-value="${url}"
	 * @required
	 */
	private String url;
	/**
	 * @parameter expression="${generate.username}" default-value="${username}"
	 * @required
	 */
	private String username;
	/**
	 * @parameter expression="${generate.password}" default-value="${password}"
	 * @required
	 */
	private String password;
	/**
	 * @parameter expression="${generate.tableName}" default-value="${tableName}"
	 * @required
	 */
	private String tableName;
	
	/**
	 * @parameter expression="${generate.projectDir}" default-value="${basedir}"
	 */
	private String projectDir;
	
	/**
	 * @parameter expression="${generate.searchParams}" default-value="${searchParams}"
	 */
	private String searchParams;
	
	/**
	 * @parameter expression="${generate.mapperFlag}" default-value="${mapperFlag}"
	 */
	private boolean daoFlag;
	/**
	 * @parameter expression="${generate.mybatisXmlFlag}" default-value="${mybatisXmlFlag}"
	 */
	private boolean mybatisXmlFlag;
	/**
	 * @parameter expression="${generate.serviceFlag}" default-value="${serviceFlag}"
	 */
	private boolean serviceFlag;
	/**
	 * @parameter expression="${generate.serviceImplFlag}" default-value="${serviceImplFlag}"
	 */
	private boolean serviceImplFlag;
	/**
	 * @parameter expression="${generate.modelFlag}" default-value="${modelFlag}"
	 */
	private boolean modelFlag;
	/**
	 * @parameter expression="${generate.searchVoFlag}" default-value="${searchVoFlag}"
	 */
	private boolean searchVoFlag;
	/**
	 * @parameter expression="${generate.voFlag}" default-value="${voFlag}"
	 */
	private boolean voFlag;
	/**
	 * @parameter expression="${generate.controllerFlag}" default-value="${controllerFlag}"
	 */
	private boolean controllerFlag;
	
	
	public void execute() throws MojoExecutionException, MojoFailureException {
		TableProperty tableProperty;
		List<String> searchParamsList=new ArrayList<String>();
		if(!isEmpty(searchParams)){
			for(String item:searchParams.split(",")){
				searchParamsList.add(item);
			}
		}
		try {
			tableProperty = new TableProperty(url,username,password,tableName,searchParamsList);
		} catch (InitTableInfoException e1) {
			getLog().info("get table info fail:"+e1.getMessage());
			e1.printStackTrace();
			return;
		}
		MappperGen mapperGen=new MappperGen(projectDir,packageName,tableProperty);
		MybatisXmlGen mybatisXmlGen=new MybatisXmlGen(projectDir,packageName,tableProperty);
		ServiceGen serviceGen=new ServiceGen(projectDir,packageName,tableProperty);
		ServiceImplGen serviceImplGen=new ServiceImplGen(projectDir,packageName,tableProperty);
		ModelGen modelGen=new ModelGen(projectDir,packageName,tableProperty);
		SearchVoGen searchVoGen= new SearchVoGen(projectDir,packageName,tableProperty);
		VoGen voGen= new VoGen(projectDir,packageName,tableProperty);
		ControllerGen controllerGen= new ControllerGen(projectDir,packageName,tableProperty);
		try {
			if(daoFlag)
				mapperGen.generate();
			if(mybatisXmlFlag)
				mybatisXmlGen.generate();
			if(serviceFlag)
				serviceGen.generate();
			if(serviceImplFlag)
				serviceImplGen.generate();
			if(modelFlag)
				modelGen.generate();
			if(searchVoFlag)
				searchVoGen.generate();
			if(voFlag)
				voGen.generate();
			if(controllerFlag)
				controllerGen.generate();
			
		} catch (BuildTemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CopyFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getLog().info("code generate finish!");
	}
	
	private static boolean isEmpty(String str) {
	        return str == null || str.length() == 0;
	   }

}
