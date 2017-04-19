package com.elend.maven.util;

import java.io.StringWriter;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

/**
 * 生成静态页面父类
 * 
 * @author bill
 * 
 */
public class DataService {

  private static Logger logger = Logger.getLogger(DataService.class);  
	   
  /**
   * 生成合成后的XML文件
   * 
   * @param tpl
   *          模板文件，包括相对模板根目录的路径
   * @param dataInfo
   *          所需的数据
   * @return
   * @throws Exception 
   */
  public static  String  buildData(String tpl,Map<String,Object> dataInfo) throws Exception {
	  //logger.info("template file :"+tpl+" build data : "+dataInfo);
	  System.out.println("template file :"+tpl+" build data : "+dataInfo);
	  
	  Properties properties = new Properties();
	  //设置文件编码
      properties.setProperty(Velocity.INPUT_ENCODING,"UTF-8");
      properties.setProperty(Velocity.OUTPUT_ENCODING,"UTF-8");
      
      //设置日志不输出
      properties.setProperty(Velocity.RUNTIME_LOG,"false");
      properties.setProperty(Velocity.RUNTIME_LOG_ERROR_STACKTRACE,"true");
      properties.setProperty(Velocity.RUNTIME_LOG_INFO_STACKTRACE,"false");
      properties.setProperty(Velocity.RUNTIME_LOG_LOGSYSTEM,"false");
      properties.setProperty(Velocity.RUNTIME_LOG_LOGSYSTEM_CLASS,"org.apache.velocity.runtime.log.NullLogSystem");
      properties.setProperty(Velocity.RUNTIME_LOG_REFERENCE_LOG_INVALID,"false");
      properties.setProperty(Velocity.RUNTIME_LOG_WARN_STACKTRACE,"false");
      
	  Velocity velocity = new Velocity();
	  velocity.init(properties);
	  
      VelocityContext context=new VelocityContext(dataInfo);
      
      return convert(tpl,context);
  }
  /*
   * 采用Velocity，将传入的vm串，结合params对象，转换为字符串。
   */
  static public String convert(String vmFileName,VelocityContext context) throws Exception{
    StringWriter w = new StringWriter();
    w = new StringWriter();
    Velocity.evaluate( context, w, "util.velocity", Loader.getResourceAsStream(vmFileName) );
    //System.out.println(w.toString());
    return w.toString();
  }
}
