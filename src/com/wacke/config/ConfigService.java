package com.wacke.config;

import com.wacke.config.dao.ConfigDao;

public class ConfigService {

	public String getConfigByName(String name){
		return getConfigDao().getConfigByName(name);
	}
	
	private ConfigDao configDao;

	public ConfigDao getConfigDao() {
		return configDao;
	}

	public void setConfigDao(ConfigDao configDao) {
		this.configDao = configDao;
	}
	
}
