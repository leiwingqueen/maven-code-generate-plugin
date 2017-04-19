package com.elend.maven.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.elend.maven.TableInfoFormate;
import com.elend.maven.exception.InitTableInfoException;
import com.elend.maven.util.NameFormator;

/**
 * 表信息
 * @author liyongquan
 *
 */
public class TableProperty implements TableInfoFormate{
	/** 表名 */
	private String tableName;
	/** 类名 */
	private String className;
	/** 表注释 */
	private String comment;
	/** 表字段 */
	private List<ItemProperty> items;
	/** 用于搜索的表字段 */
	private List<ItemProperty> search;
	
	/** 主键 */
	private ItemProperty prikey;
	
	/**数据库连接*/
	private String   url ;
	/**用户名*/
	private String  username ;
	/**密码*/
	private String  password;
	/**数据库连接驱动*/
	private String driver="com.mysql.jdbc.Driver";
	/**字段筛选(只保留一部分的字段信息)*/
	private List<String> searchParams=new ArrayList<String>();
	
	public TableProperty(){}
	
	public TableProperty(String url,String username,String password,String tableName,List<String> searchParams)throws InitTableInfoException{
		this(url,username,password,tableName,searchParams,"com.mysql.jdbc.Driver");
	}
	
	public TableProperty(String url,String username,String password,String tableName,List<String> searchParams,String driver)throws InitTableInfoException{
		this.url=url;
		this.username=username;
		this.password=password;
		this.driver=driver;
		this.tableName=tableName;
		search=new ArrayList<ItemProperty>();
		items=new ArrayList<ItemProperty>();
		this.searchParams=searchParams;
		initTable();
	}
	public TableProperty initTable()throws InitTableInfoException{
		ResultSet rs=null;
		String comm = "";
		try{
			Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, username, password);
		rs = conn.prepareStatement("show table STATUS").executeQuery();
		prikey = new ItemProperty();
		rs = conn.prepareStatement("show full fields from " + tableName).executeQuery();
		
		while(rs.next()) {
			ItemProperty item = new ItemProperty();
			String name = rs.getString("Field");
			String type = rs.getString("Type");
			String comment = rs.getString("Comment");
			String key = rs.getString("Key");
			item.setName(name);
			item.setType(type.split("\\(")[0]);
			item.setJtype(JavaType.getPrimitiveType(item.getType()));
			item.setReferenceType(JavaType.getReferenceType(item.getType()));
			item.setComment(comment);
			if("PRI".equals(key)) {
				prikey = item;
				item.setPriKey(true);
			} else {
				item.setPriKey(false);
			}
			items.add(item);
			
			if(searchParams == null || searchParams.size() == 0) continue;
			for(String s : searchParams) {
				if(name.equals(s)) {
					search.add(item);
					break;
				}
			}
		}
		}catch(Exception e){
			throw new InitTableInfoException(e);
		}
		if(searchParams==null||searchParams.size() == 0) {
			search.addAll(items);
		}
		
		this.setTableName(tableName);
		this.setComment(comm.split(";")[0]);
		this.setItems(items);
		this.setPrikey(prikey);
		this.setSearch(search);
		return this;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String name) {
		this.tableName = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<ItemProperty> getItems() {
		return items;
	}

	public void setItems(List<ItemProperty> items) {
		this.items = items;
	}

	public ItemProperty getPrikey() {
		return prikey;
	}

	public void setPrikey(ItemProperty prikey) {
		this.prikey = prikey;
	}

	public String getClassName() {
		if(tableName != null && tableName.trim().length()!=0){
			className = NameFormator.toUUCase(tableName);
		}
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public List<ItemProperty> getSearch() {
		return search;
	}

	public void setSearch(List<ItemProperty> search) {
		this.search = search;
	}
	/**首字母小写的类名*/
	public String getlClassName() {
		return NameFormator.toLFirst(getClassName());
	}
}
