package com.elend.maven.util;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;

import org.apache.log4j.Logger;

public class Loader {
	private static Logger logger = Logger.getLogger(Loader.class);

	/**
	 * 获取资源的URL地址
	 * @param resource
	 * @return
	 */
	static public URL getResource(String resource) {
		ClassLoader classLoader = null;
		URL url = null;
		try {
			classLoader = getTCL();
			if (classLoader != null) {
				url = classLoader.getResource(resource);
				if (url != null) {
					return url;
				}
			}
			classLoader = Loader.class.getClassLoader();
			if (classLoader != null) {
				url = classLoader.getResource(resource);
				if (url != null) {
					return url;
				}
			}
		} catch (Throwable t) {
			logger.warn("无法获取资源！", t);
		}
		return ClassLoader.getSystemResource(resource);
	}

	/**
	 * Get the Thread Context Loader which is a JDK 1.2 feature. If we are
	 * running under JDK 1.1 or anything else goes wrong the method returns
	 * <code>null<code>.
	 *
	 */
	private static ClassLoader getTCL() 
		throws IllegalAccessException,InvocationTargetException {
		Method method = null;
		try {
			method = Thread.class.getMethod("getContextClassLoader", null);
		} catch (NoSuchMethodException e) {
			return null;
		}
		return (ClassLoader) method.invoke(Thread.currentThread(), null);
	}

	/**
	 * 加载指定类下面的配置文件
	 * 
	 * @param resourceClass
	 * @param resource
	 * @return
	 * @throws Exception
	 */
	public static InputStream getResourceAsStream(Class resourceClass,String resource) 
		throws Exception {
		logger.debug("Configuration resource: " + resource);
		InputStream stream = resourceClass.getResourceAsStream(resource);
		if (stream == null)
			stream = Thread.currentThread()
						   .getContextClassLoader()
						   .getResourceAsStream(resource);
		if (stream == null) {
			logger.warn(resource + " not found");
			throw new Exception(resource + " not found");
		}
		return stream;
	}
	/**
	 * 获取当前CLASSPATH路径下面的资源配置文件
	 * @param resource
	 * @return
	 * @throws Exception
	 */
	public static InputStream getResourceAsStream(String resource) throws Exception {
		logger.debug("Configuration resource: " + resource);
		InputStream stream = Thread.currentThread()
								   .getContextClassLoader()
								   .getResourceAsStream(resource);
		if (stream == null) {
			logger.warn(resource + " not found");
			throw new Exception(resource + " not found");
		}
		return stream;
	}
}
