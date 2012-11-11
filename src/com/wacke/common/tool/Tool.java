package com.wacke.common.tool;

import info.monitorenter.cpdetector.io.ASCIIDetector;
import info.monitorenter.cpdetector.io.CodepageDetectorProxy;
import info.monitorenter.cpdetector.io.JChardetFacade;
import info.monitorenter.cpdetector.io.ParsingDetector;
import info.monitorenter.cpdetector.io.UnicodeDetector;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.wacke.entity.User;

public class Tool {

	public static String getRandomString(int size) {
        char[] c = { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'q',
                'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'a', 's', 'd',
                'f', 'g', 'h', 'j', 'k', 'l', 'z', 'x', 'c', 'v', 'b', 'n', 'm' };
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < size; i++){
            sb.append(c[Math.abs(random.nextInt()) % c.length]);
        }
        return sb.toString();
    }
	
	@SuppressWarnings("deprecation")
	public static void deleteOldFile(){
		File[] files = new File(ServletActionContext.getRequest().getRealPath("/data/temp")).listFiles();
		long dateline = System.currentTimeMillis()-1*1000*60*60;
		for(File file : files){
			if(file.lastModified() < dateline)
				file.delete();
		}
	}

	public static int getUid(){
		return Integer.parseInt(ActionContext.getContext().getSession().get(User.UID).toString());
	}
	
	public static String getUserName(){
		return ActionContext.getContext().getSession().get(User.REALNAME).toString();
	}
	
	public static String getAvatarPath(int uid) {
		String avatarURLPath = "";
		DecimalFormat df=(DecimalFormat) DecimalFormat.getInstance();
		df.setGroupingSize(2);
		String[] path = df.format(uid).split(",");
		if(path[0].length() == 1)
			path[0] = "0" + path[0];
		
		int i = 0;
		
		while(i < 5-path.length)
		{
			avatarURLPath += "00" + System.getProperty("file.separator");
			i++;
		}
		
		for(i = 0; i < path.length;i++)
		{
			avatarURLPath += path[i] + System.getProperty("file.separator");
		}
		return System.getProperty("file.separator") + "avatar" + System.getProperty("file.separator") + avatarURLPath;
	}
	
	public static String getAvatarURLPath(int uid) {
		String avatarURLPath = "";
		DecimalFormat df=(DecimalFormat) DecimalFormat.getInstance();
		df.setGroupingSize(2);
		String[] path = df.format(uid).split(",");
		if(path[0].length() == 1)
			path[0] = "0" + path[0];
		
		int i = 0;
		
		while(i < 5-path.length)
		{
			avatarURLPath += "00/";
			i++;
		}
		
		for(i = 0; i < path.length;i++)
		{
			avatarURLPath += path[i] + "/";
		}
		return "data/avatar/" + avatarURLPath;
	}
	
	public static String getSiteName() {
		return "学习社区";
	}
	
	public static int getDateLine(){
		return (int)(System.currentTimeMillis()/1000);
	}
	
	public static String arrayToString(Object[] objects) {
		if(objects == null)
			return "";
		String tostring = "";
		for(Object object : objects)
			tostring += object.toString() + ",";
		return tostring.length()>0 ? tostring.substring(0, tostring.length()-1) : null;
	}
	
	public static boolean imageScale(File srcFile, File destFile, int width, int height) {
		try{
			ImageScale.resizeFix(srcFile, destFile, width, height);
			return true;
		}
		catch (Exception e) {
			e.getMessage();
			return false;
		}
	}
	
	public static boolean hasProcess(String process) {
		BufferedReader br = null;
		try {
			Process proc = Runtime.getRuntime().exec(
					"tasklist /FI \"IMAGENAME eq " + process + "\"");
			br = new BufferedReader(
					new InputStreamReader(proc.getInputStream()));
			String line = null;
			while ((line = br.readLine()) != null) 
			{
				if (line.contains(process)) {
					return true;
				}
			}

			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (Exception ex) {
				}
			}
		}
	}
	
	public static String getFileName(File file) {
		return file.getName().substring(0,file.getName().lastIndexOf("."));
	}
	
	public static String getExtension(File file) {
		return file.getName().substring(file.getName().lastIndexOf(".")).toLowerCase();
	}

	public static String getCharset(Object object) {
		CodepageDetectorProxy detector = CodepageDetectorProxy.getInstance();
		detector.add(new ParsingDetector(false));
		detector.add(JChardetFacade.getInstance());
		detector.add(ASCIIDetector.getInstance());
		detector.add(UnicodeDetector.getInstance());
		java.nio.charset.Charset charset = null;
		try {
			if(object instanceof String){
				String o = (String)object;
				charset = detector.detectCodepage(new ByteArrayInputStream(o.getBytes()), o.length());
			}
			if(object instanceof File){
				File o = (File)object;
				charset = detector.detectCodepage(o.toURI().toURL());
			}
		} catch (Exception e) {
			e.getMessage();
		}
		if (charset != null)
			return charset.name();
		else
			return "void";
	}
	
	public static boolean converFile(File source) {
		if(getExtension(source).equals(".txt")){
			try {
				String charset = getCharset(source);
				File txtFile = new File(source.getParent(),getRandomString(20)+".txt");
				Reader r = new BufferedReader(new InputStreamReader(new FileInputStream(source),charset));       
				Writer w = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(txtFile), "UTF-8")); 
				char[] buffer = new char[4096];       
				int len;       
				while ((len = r.read(buffer)) != -1)       
					w.write(buffer, 0, len);       
				r.close();       
				w.flush();       
				w.close();
				source.delete();
				txtFile.renameTo(source);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return false;
			}
		}
		
		String date = new SimpleDateFormat("yyyyMM").format(new Date());
		String fileName = com.wacke.common.tool.Tool.getFileName(source);
		File saveDir = new File(source.getParent().substring(0,source.getParent().length()-4) + "/document/" + date);
		if(!saveDir.exists())
			saveDir.mkdirs();
		
		File pdf = new File(saveDir,fileName+".pdf");
		File swf = new File(saveDir,fileName+".swf");
		
		try{
			return ConvertFiles.conver(source, pdf, swf);
		}
		catch (Exception e) {
			e.getMessage();
			return false;
		}
	}
	
	public static boolean dealAlbumPic(String pic, File srcPath, File destPath){
		BufferedImage srcBufferImage;
		File src = new File(srcPath,pic);
		File dest = new File(destPath,pic);
		File thumb = new File(destPath,"thumb_"+pic);
		try {
			srcBufferImage = javax.imageio.ImageIO.read(src);
			int width = srcBufferImage.getWidth();
			int height = srcBufferImage.getHeight();
			imageScale(src, dest, 600, (int)(600*height/width));
			imageScale(src, thumb, 130, 130);
			src.delete();
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
