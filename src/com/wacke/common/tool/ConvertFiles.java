package com.wacke.common.tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

public class ConvertFiles {
	private static Logger logger = Logger.getLogger(ConvertFiles.class);

	public static final String OpenOfficePath = "OpenOfficePath"; 
	public static final String OpenOfficeHost = "OpenOfficeHost";
	public static final String OpenOfficePort = "OpenOfficePort";
	public static final String PDF2SWFPath = "PDF2SWFPath";
	public static final String PDF2SWFLanguageDir = "PDF2SWFLanguageDir";
	
	public static boolean conver(File source,File pdf,File swf) throws IOException{
		if (!source.exists()) return false;
		
		if(com.wacke.common.tool.Tool.getExtension(source).equals(".pdf")){
			try {
				FileUtils.copyFile(source, pdf);
			} catch (IOException e) {
				e.getMessage();
				return false;
			}
		}else{
			if(!File2PDF(source, pdf))
			return false;
		}
		if(!PDF2SWF(pdf, swf))
			return false;
		source.delete();
		return true;
	}

	private static boolean File2PDF(File source,File dest){
		int OpenOfficePort = Integer.parseInt(System.getProperty(ConvertFiles.OpenOfficePort));
		OpenOfficeConnection connection = new SocketOpenOfficeConnection(OpenOfficePort);
		try
		{
			connection.connect();
		}
		catch(ConnectException e)
		{
			logger.error(e.getMessage());
			return false;
		}
		DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
		converter.convert(source, dest);
		connection.disconnect();
		logger.info("File2PDF -- from: "+source.toString()+" to: "+dest.toString());
		return true;
	}
	
	private static boolean PDF2SWF(File source, File dest) throws IOException{
		String PDF2SWFPath = System.getProperty(ConvertFiles.PDF2SWFPath);
		String PDF2SWFLanguageDir = System.getProperty(ConvertFiles.PDF2SWFLanguageDir);
		String command = PDF2SWFPath
						+"pdf2swf.exe"
						+ " -o \"" 
						+ dest
						+ "\" -s languagedir="
						+PDF2SWFLanguageDir
						+" -s flashversion=9 \"" 
						+ source
						+"\"";   
		Process pro = Runtime.getRuntime().exec(command);   
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(pro.getInputStream()));   
		String line;
		while ((line=bufferedReader.readLine() )!= null)
		{
			logger.debug(line);
		}
		try {   
			pro.waitFor();  
			logger.info("PDF2SWF -- from: "+source.toString()+" to: "+dest.toString());
		} catch (InterruptedException e) {   
			logger.error(e.getMessage());   
		}   
		return true;   
	}
}
