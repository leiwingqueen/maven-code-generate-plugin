package com.elend.maven.data;

import java.util.HashMap;
import java.util.Map;

/**
 * 类型转换：mysql -> Java
 * 
 * @author liuzifeng
 *
 */
public class JavaType {
	
	private static Map<String, String> primitiveMap = new HashMap<String, String>();
	private static Map<String, String> referenceMap = new HashMap<String, String>();
	
	static {
		primitiveMap.put("int", "int");
		primitiveMap.put("bigint", "long");
		primitiveMap.put("varchar", "String");
		primitiveMap.put("text", "String");
		primitiveMap.put("longtext", "String");
		primitiveMap.put("datetime", "Date");
		primitiveMap.put("double", "double");
		primitiveMap.put("decimal", "BigDecimal");
		primitiveMap.put("tinyint", "int");
		primitiveMap.put("date", "Date");
		primitiveMap.put("char", "String");
		primitiveMap.put("smallint", "int");
		
		referenceMap.put("int", "Integer");
		referenceMap.put("bigint", "Long");
		referenceMap.put("varchar", "String");
		referenceMap.put("text", "String");
		referenceMap.put("longtext", "String");
		referenceMap.put("datetime", "Date");
		referenceMap.put("double", "Double");
		referenceMap.put("decimal", "BigDecimal");
		referenceMap.put("tinyint", "Integer");
		referenceMap.put("date", "Date");
		referenceMap.put("char", "String");
		referenceMap.put("smallint", "Integer");
	}
	/**
	 * 获取基本类型
	 * @param sqlType
	 * @return
	 */
	public static String getPrimitiveType(String sqlType) {
		return primitiveMap.get(sqlType.toLowerCase());
	}
	
	public static String getReferenceType(String sqlType) {
		return referenceMap.get(sqlType.toLowerCase());
	}

}
