package org.radoslawburkacki.MusicExchange.model;

public class Song {

	String title;
	String artist;
	String album;
	String genre;
	
	public Song(){
		
	}
	
	
	public Song( String title, String artist, String album, String genre) {
	
		this.title = title;
		this.artist = artist;
		this.album = album;
		this.genre = genre;
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
	
	
	
	

	
}
