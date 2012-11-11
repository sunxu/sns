package com.wacke.doing.dao;

import com.wacke.entity.Doing;

public interface DoingDao {

	public Doing getDoingById(int id);
	
	public Doing getLatestDoingByUid(int uid);
	
	public Integer saveDoing(Doing doing);
	
}
