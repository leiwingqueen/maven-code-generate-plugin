package com.elend.maven.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class IbatisAutoUtil {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
	     try { 
	          Class.forName("com.mysql.jdbc.Driver"); 
	     } catch(ClassNotFoundException e) { 
	          System.out.println("找不到驱动程序"); 
	     }	
         String url = "jdbc:mysql://61.142.247.25:3306/imip?useUnicode=true&characterEncoding=UTF8&autoReconnect=true"; 
         Connection conn = DriverManager.getConnection(url,"root","idtn!@#mysql098"); 	
         CreateDomain(conn,"process_info");   
         CreateInsert(conn,"process_info",null);   
//         CreateUpdate(conn,"article","id");    
	}
	public static void CreateInsert(Connection conn,String tableName,String primaryKey) throws SQLException {
		String sql = "select * from "+tableName;
		ResultSet result = conn.createStatement().executeQuery(sql);
		ResultSetMetaData metaData = result.getMetaData();
		int count = metaData.getColumnCount();
		StringBuilder strBuff = new StringBuilder("");
		StringBuilder strInsertBuff = new StringBuilder("insert into "+tableName+"(");
		for(int i=1;i<count+1;i++){
			if(primaryKey!=null&&primaryKey.equals(metaData.getColumnName(i))){
				continue;
			}
			strInsertBuff.append(metaData.getColumnName(i));
			strBuff.append("#{"+metaData.getColumnName(i)+"}");
			if(i<count){
				strInsertBuff.append(",");
				strBuff.append(",");
			}
		}
		strInsertBuff.append(") values(");
		strBuff.append(")");
		System.out.println(strInsertBuff.toString()+strBuff);
	}
	public static void CreateUpdate(Connection conn,String tableName,String primaryKey) throws SQLException {
		String sql = "select * from "+tableName;
		ResultSet result = conn.createStatement().executeQuery(sql);
		ResultSetMetaData metaData = result.getMetaData();
		int count = metaData.getColumnCount();
		StringBuilder strBuff = new StringBuilder("update "+tableName+" set ");
		for(int i=1;i<count+1;i++){
			if(primaryKey!=null&&primaryKey.equals(metaData.getColumnName(i))){
				continue;
			}
			strBuff.append(metaData.getColumnName(i)+"=#{"+metaData.getColumnName(i)+"}");
			if(i<count){
				strBuff.append(",");
			}
		}
		if(primaryKey!=null){
			strBuff.append(" where "+primaryKey+"=#{"+primaryKey+"}");
		}
		System.out.println(strBuff.toString());
	}
	public static void CreateDomain(Connection conn,String tableName) throws SQLException {
		String sql = "show full fields from "+tableName;
		ResultSet result = conn.createStatement().executeQuery(sql);
		while(result.next()){
			String name = result.getString("Field");
			String type = result.getString("Type");
			String comm = result.getString("Comment");
//			String key  = result.getString("Key");
			
			System.out.println("/**"+comm+"**/");
			if(type.startsWith("bigint")||type.startsWith("bigint")||type.startsWith("int")||type.startsWith("tinyint")){
				System.out.println("private int "+name+";");
			}
			else if(type.startsWith("varchar")||type.startsWith("text")){
				System.out.println("private String "+name+";");
			}
			else if(type.startsWith("decimal")){
				System.out.println("private float "+name+";");
			}
			else if(type.startsWith("datetime")){
				System.out.println("private String "+name+";");
			}
			else{
				System.out.println("+++++++++++++++++++++++++="+type);
			}			
		}
		//System.out.println(strBuff.toString());
	}
	public static void CreateTestData(Connection conn,String tableName) throws SQLException {
		String sql = "select * from "+tableName;
		ResultSet result = conn.createStatement().executeQuery(sql);
		ResultSetMetaData metaData = result.getMetaData();
		int count = metaData.getColumnCount();
		for(int i=1;i<count+1;i++){
			String type = metaData.getColumnTypeName(i);
			if("INT UNSIGNED".equals(type)||"INT".equals(type)){
				System.out.println("row.put(\""+metaData.getColumnName(i)+"\",2);");
			}
			if("VARCHAR".equals(type)){
				System.out.println("row.put(\""+metaData.getColumnName(i)+"\",\""+metaData.getColumnName(i)+"\");");
			}
			if("DECIMAL".equals(type)){
				System.out.println("row.put(\""+metaData.getColumnName(i)+"\",23.22);");
			}
			if("DATETIME".equals(type)){
				System.out.println("row.put(\""+metaData.getColumnName(i)+"\",\"2012-02-11 10:17:12\");");
			}
		}
		//System.out.println(strBuff.toString());
	}
}
