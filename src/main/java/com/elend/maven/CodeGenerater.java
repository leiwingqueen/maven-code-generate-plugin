package com.elend.maven;

import com.elend.maven.exception.BuildTemplateException;
import com.elend.maven.exception.CopyFileException;

/**
 * 代码生成器
 * @author liyongquan
 *
 */
public interface CodeGenerater {
	void generate()throws BuildTemplateException,CopyFileException;
}
