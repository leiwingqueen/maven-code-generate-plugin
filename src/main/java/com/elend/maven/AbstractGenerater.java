package com.elend.maven;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.elend.maven.data.TableProperty;
import com.elend.maven.exception.BuildTemplateException;
import com.elend.maven.exception.CopyFileException;
import com.elend.maven.util.DataService;
import com.elend.maven.util.FileUtil;

/**
 * 代码生成器虚基类
 * @author liyongquan
 *
 */
public class AbstractGenerater implements CodeGenerater{
	/**生成目标目录*/
	protected String targetDir;
	/**文件名*/
	protected String fileName;
	/**模板名*/
	protected String template;
	/**需要组装的数据*/
	protected Map<String,Object> dataMap;
	/**表信息*/
	protected TableProperty tableProperty;
	/**基本包名,eg.com.elend*/
	protected String basePackageName;
	
	/**
	 * 
	 * @param targetDir
	 * @param fileName
	 * @param template
	 * @param basePackageName
	 * @param extendData--扩展的数据(针对不同的模板可以扩展)
	 */
	public AbstractGenerater(String targetDir,String fileName,String template,String basePackageName,Map<String,Object> extendData,TableProperty tableProperty){
		this.targetDir=targetDir;
		this.fileName=fileName;
		this.template=template;
		this.basePackageName=basePackageName;
		this.tableProperty=tableProperty;
		/**添加一些公共的数据*/
		dataMap=new HashMap<String, Object>();
		dataMap.put("table", tableProperty);
		dataMap.put("basePackageName", basePackageName);
		dataMap.put("extendData", extendData);
		
	}
	public void generate()throws BuildTemplateException,CopyFileException{
		String content;
		try {			
			content = DataService.buildData(template, dataMap);
		} catch (Exception e) {
			throw new BuildTemplateException(e);
		}
		//System.out.println("----转换后的内容----"+content);
		//特殊处理{\}"\\{\\}
		content = content.replace("ZZ", "");
		//写到目标目录
		try {
			String fullPath=targetDir + File.separator + fileName;
			File file = new File(fullPath);
			if(file.exists())throw new CopyFileException("文件"+fileName+"已存在，请删除后再生成");
			FileUtil.writIn(fullPath, content);
		} catch (IOException e) {
			throw new CopyFileException(e);
		}
		System.out.println(fileName+"生成成功！");
	}

	public String getTargetDir() {
		return targetDir;
	}

	public void setTargetDir(String targetDir) {
		this.targetDir = targetDir;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	public TableProperty getTableProperty() {
		return tableProperty;
	}

	public void setTableProperty(TableProperty tableProperty) {
		this.tableProperty = tableProperty;
	}

	public String getBasePackageName() {
		return basePackageName;
	}

	public void setBasePackageName(String basePackageName) {
		this.basePackageName = basePackageName;
	}
	
}
