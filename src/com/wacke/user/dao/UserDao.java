package com.wacke.user.dao;

import java.util.List;

import com.wacke.entity.User;

public interface UserDao {
	public String MD5(String password);
	
	public boolean checkEmail(String email);
	
	public void saveNewUser(String userEmail, String userName, String userPassword, String userSex);

	public void updateUser(User user);
	
	public User getUserByEmail(String email);
	
	public User getUserByUid(Integer uid);
	
	public List<User> getuserListByUid(Integer[] uid);
}
