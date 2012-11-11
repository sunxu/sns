package com.wacke.common.entity;

public class Page {
	private int tatolCount;
	private int tatolPage;
	private boolean hasNext;
	private boolean hasPre;
	private int now;
	private int perPage;

	public int getTatolCount() {
		return tatolCount;
	}

	public void setTatolCount(int tatolCount) {
		this.tatolCount = tatolCount;
	}

	public int getTatolPage() {
		return tatolPage;
	}

	public void setTatolPage(int tatolPage) {
		this.tatolPage = tatolPage;
	}

	public boolean isHasNext() {
		return hasNext;
	}

	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}

	public boolean isHasPre() {
		return hasPre;
	}

	public void setHasPre(boolean hasPre) {
		this.hasPre = hasPre;
	}

	public int getNow() {
		return now;
	}

	public void setNow(int now) {
		this.now = now;
	}

	public int getPerPage() {
		return perPage;
	}

	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}
	
	public Page(int tatolCount, int now)
	{
		this.tatolCount = tatolCount;
		this.now = now;
		this.perPage = 10;
		init();
	}
	
	public Page(int tatolCount, int now, int perPage)
	{
		this.tatolCount = tatolCount;
		this.now = now;
		this.perPage = perPage;
		init();
	}
	
	private void init(){
		int tatolPage;
		tatolPage = tatolCount/perPage;
		if(tatolCount%perPage > 0)
		{
			tatolPage++;
		}
		if(now <= 1)
		{
			now = 1;
		}
		if(now >= tatolPage && tatolPage > 0)
		{
			now = tatolPage;
		}
		
		setNow(now);
		setTatolCount(tatolCount);
		setTatolPage(tatolPage);
		setHasNext(false);
		setHasPre(false);
		if(now > 1)
			setHasPre(true);
		if(now < tatolPage)
			setHasNext(true);
	}
	
}