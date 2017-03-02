package org.radoslawburkacki.MusicExchange.model;

public class Top10Song{

	
	String title;
	String artist;
	String album;
	String genre;
	int rank;
	
	public Top10Song(){
		
	}
	
	public Top10Song(String title, String artist, String album, String genre, int rank) {
		super();
		this.title = title;
		this.artist = artist;
		this.album = album;
		this.genre = genre;
		this.rank = rank;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getAlbum() {
		return album;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	
	
	
}
