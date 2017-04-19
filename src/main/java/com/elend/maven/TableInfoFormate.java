package com.elend.maven;

import com.elend.maven.data.TableProperty;
import com.elend.maven.exception.InitTableInfoException;

/**
 * 表结构获取
 * @author liyongquan
 *
 */
public interface TableInfoFormate {
	/**
	 * 获取表结构
	 * @return
	 */
	TableProperty initTable()throws InitTableInfoException;
}
