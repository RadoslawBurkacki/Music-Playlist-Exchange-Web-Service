package org.radoslawburkacki.MusicExchange.model;



public class Statistic {

	int views=0;
	int like=0;
	int dislike =0;
	public Statistic(){
		
	}
	public Statistic(int views, int like, int dislike) {
		super();
		this.views = views;
		this.like = like;
		this.dislike = dislike;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	public int getDislike() {
		return dislike;
	}
	public void setDislike(int dislike) {
		this.dislike = dislike;
	}
	
	
	
	
	
	
	
	
	
}
