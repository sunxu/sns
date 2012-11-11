package com.wacke.user;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.wacke.common.entity.PageInfo;
import com.wacke.entity.User;
import com.wacke.user.service.UserService;

public class UserSetAction extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8622398469691030441L;

	@Override
	public String execute() throws Exception {
		getPageInfo().title = "用户资料修改";
		getPageInfo().type = PageInfo.HOME;
		if(getType() == null)
			setType(UserSetAction.INFO);
		if(getType().equals(UserSetAction.INFO))
		{
			setUser(
					getUserService().getUserDao().getUserByUid(
							com.wacke.common.tool.Tool.getUid()
						)
					);
		}
		return super.execute();
	}

	public String saveInfo() throws Exception {
		setUser(
				getUserService().getUserDao().getUserByUid(
						com.wacke.common.tool.Tool.getUid()
					)
				);
		
		int birthday = 0;
		try{
			birthday =  Integer.parseInt(birth_year)*10000+
						Integer.parseInt(birth_month)*100+
						Integer.parseInt(birth_day);
			}
		catch(Exception e)
		{
			e.getMessage();
		}
			
		if(getUser().getSex() != (byte)Integer.parseInt(getSex())){
			getUser().setSex((byte)Integer.parseInt(sex));
			if(!getUser().getHasavatar())
				ActionContext.getContext().getSession().put(User.AVATAR, user.getAvatarURLPath());
		}
		
		if(birthday != 0)
		{
			getUser().setBirthday(birthday);
		}
		getUser().setBirthProvince(birth_province);
		getUser().setBirthCity(birth_city);
		getUser().setResideProvince(reside_province);
		getUser().setResideCity(reside_city);
		getUserService().getUserDao().updateUser(getUser());
		return "info";
	}
	
	public String savePassword() throws Exception {
		getPageInfo().title = "用户资料修改";
		getPageInfo().type = PageInfo.HOME;
		setType(UserSetAction.PASSWORD);
		if(!getNew_pw().equals(getNew_pw_repeat()))
		{
			addFieldError("NewPswError", "true");
			return "password";
		}
		setUser(
				getUserService().getUserDao().getUserByUid(
						com.wacke.common.tool.Tool.getUid()
					)
				);
		String md5psw = getUserService().getUserDao().MD5(getOld_pw());
		if(!getUser().getPassword().equals(md5psw))
		{
			addFieldError("OldPswError", "true");
			return "password";
		}
		getUserService().getUserDao().updateUser(getUser());
		addFieldError("SavePswSuccess", "true");
		return "password";
	}
	
	@SuppressWarnings("deprecation")
	public String avatarUpload() throws Exception{
		getPageInfo().title = "用户资料修改";
		getPageInfo().type = PageInfo.HOME;
		setType(UserSetAction.MAKEAVATAR);	
		
		String targetDirectory = ServletActionContext.getRequest().getRealPath("/data/temp");
		// 生成上传的File对象
		String targetName = com.wacke.common.tool.Tool.getRandomString(20)
						+getAvatarFileFileName().substring(getAvatarFileFileName().lastIndexOf(".")).toLowerCase();
		ActionContext.getContext().getSession().put("tempAvatar", targetName);
		File target = new File(targetDirectory, targetName);
		// 复制File对象，从而实现上传文件
		FileUtils.copyFile(avatarFile, target);
		
		return "makeavatar";
	}
	
	@SuppressWarnings("deprecation")
	public String makeAvatar() throws Exception{
		String directory = ServletActionContext.getRequest().getRealPath("/data/temp");
		String sourceName = ActionContext.getContext().getSession().get("tempAvatar").toString();
		File srcFile = new File(directory,sourceName);
		String fileSufix = com.wacke.common.tool.Tool.getExtension(srcFile);
		String avatarName = com.wacke.common.tool.Tool.getRandomString(20) + fileSufix;

		if(!srcFile.exists()){
			setType(UserSetAction.AVATARERROR);
			return "showavatar";
		}
		BufferedImage image = ImageIO.read(srcFile); 
		BufferedImage tempAvatar = new BufferedImage(getW(),getH(),BufferedImage.TYPE_INT_RGB);  
		tempAvatar.getGraphics().drawImage(       
				image.getScaledInstance(getW(),getH(),Image.SCALE_SMOOTH), 0, 0, null); 
		tempAvatar = tempAvatar.getSubimage(getX(),getY(),200,200);
		File avatarFile = new File(directory, avatarName);  
		BufferedImage avatar = new BufferedImage(200,200,BufferedImage.TYPE_INT_RGB);  
		avatar.getGraphics().drawImage(       
				tempAvatar.getScaledInstance(200,200,Image.SCALE_SMOOTH), 0, 0, null); 
		ImageIO.write(avatar, fileSufix.substring(1), avatarFile);
		
		try{
			com.wacke.common.tool.Tool.deleteOldFile();
			File oldFile = new File(directory,ActionContext.getContext().getSession().get("avatarName").toString());
			if(oldFile.exists())
				oldFile.delete();
		}
		catch (Exception e) {
			e.getMessage();
		}
		
		ActionContext.getContext().getSession().put("avatarName", avatarName);
		setType(UserSetAction.MAKEAVATAR);
		
		return "showavatar";
	}
	
	@SuppressWarnings("deprecation")
	public String saveAvatar() throws Exception{
		int uid = com.wacke.common.tool.Tool.getUid();
		String directory = ServletActionContext.getRequest().getRealPath("/data");
		String avatarName = ActionContext.getContext().getSession().get("avatarName").toString();
		String fileSufix = ".jpg";
		File srcFile = new File(directory+"/temp",avatarName);
		File path = new File(directory+com.wacke.common.tool.Tool.getAvatarPath(uid));
		if(!path.exists())
			path.mkdirs();
		File big = new File(path, "big"+fileSufix);  
		File middle = new File(path, "middle"+fileSufix);  
		File small = new File(path, "small"+fileSufix); 
		
		if(com.wacke.common.tool.Tool.imageScale(srcFile, big, 200, 200)
			& com.wacke.common.tool.Tool.imageScale(srcFile, middle, 120, 120)
			& com.wacke.common.tool.Tool.imageScale(srcFile, small, 48, 48))
		{
			setUser(getUserService().getUserDao().getUserByUid(uid));
			getUser().setHasavatar(true);
			getUserService().getUserDao().updateUser(getUser());
			
			ActionContext.getContext().getSession().put(User.AVATAR, com.wacke.common.tool.Tool.getAvatarURLPath(uid));
			ActionContext.getContext().getSession().remove("avatarName");
			ActionContext.getContext().getSession().remove("tempAvatar");
			
			result.put("saveavatar", true);
		}else{
			result.put("saveavatar", false);
		}
		
		return SUCCESS;
	}
	
	public static final String INFO = "info";
	public static final String AVATAR = "avatar";
	public static final String MAKEAVATAR = "makeavatar";
	public static final String PASSWORD = "password";
	public static final String AVATARERROR = "avatarerror";
	
	private PageInfo pageInfo = new PageInfo();
	private String type;
	private User user; 
	private UserService userService;
	
	private String sex;
	private String birth_year;
	private String birth_month;
	private String birth_day;
	private String birth_province;
	private String birth_city;
	private String reside_province;
	private String reside_city;
	
	private String old_pw;
	private String new_pw;
	private String new_pw_repeat;
	
	private File avatarFile;
	private String avatarFileFileName;
	
	private int x;
	private int y;
	private int w;
	private int h;
	
	private Map<String,Object> result = new HashMap<String,Object>();
	
	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirth_year() {
		return birth_year;
	}

	public void setBirth_year(String birthYear) {
		birth_year = birthYear;
	}

	public String getBirth_month() {
		return birth_month;
	}

	public void setBirth_month(String birthMonth) {
		birth_month = birthMonth;
	}

	public String getBirth_day() {
		return birth_day;
	}

	public void setBirth_day(String birthDay) {
		birth_day = birthDay;
	}

	public String getBirth_province() {
		return birth_province;
	}

	public void setBirth_province(String birthProvince) {
		birth_province = birthProvince;
	}

	public String getBirth_city() {
		return birth_city;
	}

	public void setBirth_city(String birthCity) {
		birth_city = birthCity;
	}

	public String getReside_province() {
		return reside_province;
	}

	public void setReside_province(String resideProvince) {
		reside_province = resideProvince;
	}

	public String getReside_city() {
		return reside_city;
	}

	public void setReside_city(String resideCity) {
		reside_city = resideCity;
	}

	public String getOld_pw() {
		return old_pw;
	}

	public void setOld_pw(String oldPw) {
		old_pw = oldPw;
	}

	public String getNew_pw() {
		return new_pw;
	}

	public void setNew_pw(String newPw) {
		new_pw = newPw;
	}

	public String getNew_pw_repeat() {
		return new_pw_repeat;
	}

	public void setNew_pw_repeat(String newPwRepeat) {
		new_pw_repeat = newPwRepeat;
	}

	public File getAvatarFile() {
		return avatarFile;
	}

	public void setAvatarFile(File avatarFile) {
		this.avatarFile = avatarFile;
	}

	public String getAvatarFileFileName() {
		return avatarFileFileName;
	}

	public void setAvatarFileFileName(String avatarFileFileName) {
		this.avatarFileFileName = avatarFileFileName;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}

	
}
