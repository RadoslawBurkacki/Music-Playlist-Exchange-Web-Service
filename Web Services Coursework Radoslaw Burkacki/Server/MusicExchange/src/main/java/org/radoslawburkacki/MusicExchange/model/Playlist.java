package org.radoslawburkacki.MusicExchange.model;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
	
	String title;
	List<Song> listofsongs = new ArrayList<Song>();
	int rankPoints;
	public Playlist(){
		
	}
	
	
	public Playlist(String title, List<Song> listofsongs , int rankPoints) {
		this.title = title;
		this.listofsongs = listofsongs;
		this.rankPoints = rankPoints;
	}
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<Song> getListofsongs() {
		return listofsongs;
	}
	public void setListofsongs(List<Song> listofsongs) {
		this.listofsongs = listofsongs;
	}


	public int getRankPoints() {
		return rankPoints;
	}


	public void setRankPoints(int rankPoints) {
		this.rankPoints = rankPoints;
	}
	
	
	
}
