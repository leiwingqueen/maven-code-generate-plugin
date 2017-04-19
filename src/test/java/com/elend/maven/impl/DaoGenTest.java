package com.elend.maven.impl;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.elend.maven.data.TableProperty;
import com.elend.maven.exception.BuildTemplateException;
import com.elend.maven.exception.CopyFileException;
import com.elend.maven.exception.InitTableInfoException;

public class DaoGenTest {
	private MappperGen gen;
	private TableProperty tableProperty;
	@Before
	public void init() throws InitTableInfoException{
		String url="jdbc:mysql://192.168.1.240:3306/sp2p_p?useUnicode=true&amp;amp;characterEncoding=utf8&amp;amp;autoReconnect=true";
		String username="root";
		String password="123456";
		String tableName="t_award_moth";
		tableProperty = new TableProperty(url,username,password,tableName,null);
		gen=new MappperGen("D:", "com.elend.user", tableProperty);
	}
	@Test
	public void testGen(){
		System.out.println("init");
		try {
			gen.generate();
		} catch (BuildTemplateException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			Assert.assertTrue(false);
		} catch (CopyFileException e) {
			System.out.println(e.getMessage());
			Assert.assertTrue(false);
			e.printStackTrace();
		}
		Assert.assertTrue(true);
	}
}
