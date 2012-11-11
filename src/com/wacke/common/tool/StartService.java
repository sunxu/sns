package com.wacke.common.tool;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

public class StartService implements ServletContextListener {

	private static final long serialVersionUID = 2087370159808672460L;
	private static Logger logger = Logger.getLogger(StartService.class);
	
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("stop");
	}

	public void contextInitialized(ServletContextEvent event) {
		ServletContext context = event.getServletContext();
		
		Properties props =System.getProperties();
		props.put(ConvertFiles.OpenOfficePath, context.getInitParameter(ConvertFiles.OpenOfficePath));
		props.put(ConvertFiles.OpenOfficeHost, context.getInitParameter(ConvertFiles.OpenOfficeHost));
		props.put(ConvertFiles.OpenOfficePort, context.getInitParameter(ConvertFiles.OpenOfficePort));
		props.put(ConvertFiles.PDF2SWFPath, context.getInitParameter(ConvertFiles.PDF2SWFPath));
		props.put(ConvertFiles.PDF2SWFLanguageDir, context.getInitParameter(ConvertFiles.PDF2SWFLanguageDir));

		System.setProperties(props);
		start();
	}
	
	public boolean start(){
		if(com.wacke.common.tool.Tool.hasProcess("soffice.bin"))
		{
			logger.info("OpenOffice.org服务正在运行中");
			return true;
		}
		
		String OpenOfficePath = System.getProperty(ConvertFiles.OpenOfficePath);
		String OpenOfficeHost = System.getProperty(ConvertFiles.OpenOfficeHost);
		int OpenOfficePort = Integer.parseInt(System.getProperty(ConvertFiles.OpenOfficePort));
		
		@SuppressWarnings("unused")
		Process process;
		int time = 0;
		String command = null;
		
		try
		{
			command = OpenOfficePath
					  +"OpenOfficePortable -headless -accept=\"socket,host="
					  +OpenOfficeHost
					  +",port="
					  +OpenOfficePort
					  +";urp;\" -nofirststartwizard ";
			logger.info("OpenOffice.org服务开启中...");
			process = Runtime.getRuntime().exec(command);
		}
		catch (IOException e) {   
			logger.error(e.getMessage());
			logger.info(command);
			logger.error("OpenOffice.org服务启动失败");
			return false;
		}   
          
		while(!com.wacke.common.tool.Tool.hasProcess("soffice.bin") && time < 100)
		{
			logger.debug("等待...");
			try { 
				Thread.sleep(100); 
				time++;
			} 
			catch (InterruptedException ie)
			{
				ie.getMessage();
			}
		}
		
		logger.info("OpenOffice.org服务启动成功");
		return time < 100?true:false;
	}
}
