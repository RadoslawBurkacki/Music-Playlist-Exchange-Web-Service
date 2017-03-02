package org.radoslawburkacki.MusicExchange.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.radoslawburkacki.MusicExchange.database.DatabaseClass;
import org.radoslawburkacki.MusicExchange.model.Playlist;
import org.radoslawburkacki.MusicExchange.model.Statistic;

public class StatisticsService {
	
	
	private Map<String, Statistic> playliststatistics = DatabaseClass.getPlaylistStatistics();
	private Map<String, Playlist> playlists = DatabaseClass.getPlaylists();

	
	public Statistic getPlaylistStatistic(String nickname){
		return playliststatistics.get(nickname);
	}
	
	public void increaseViewOfOnePlalist(String nickname) {
		playliststatistics.get(nickname).setViews(playliststatistics.get(nickname).getViews()+1);
		
	}


	public void increaseViewOfAllPlaylists(){
	
		for ( String key : playliststatistics.keySet() ) {
			playliststatistics.get(key).setViews(playliststatistics.get(key).getViews()+1);
		}
	}
	
	public String likePlaylist(String title, String nickname, Statistic s){
		String playlistOwner="";
		for ( String key : playlists.keySet() ) {// loop through playlist
			if(playlists.get(key).getTitle().equals(title)){// if playlist title is equal to title
				playlistOwner = key; // set playlistOwner equal to key (key is nickname of playlist owner)
			}
		}
		
		for ( String key : playliststatistics.keySet() ) {
		
			if(key.equals(playlistOwner)){
				if(playlistOwner.equals(nickname)){// if creator of playlist likes own playlist then do nothing
					return "cannot vote for own playlist";
				}
				else {// if anyone else except creator of playlist is liking then...
				playliststatistics.get(key).setLike(playliststatistics.get(key).getLike()+1); // add like
				return "success";
				}
			}
		}
			
		return "error";
			
	
	}
	
	public String dislikePlaylist(String title, String nickname, Statistic s){
		
		String playlistOwner="";
		for ( String key : playlists.keySet() ) {// loop through playlist
			if(playlists.get(key).getTitle().equals(title)){// if playlist title is equal to title
				playlistOwner = key; // set playlistOwner equal to key (key is nickname of playlist owner)
			}
		}
		
		for ( String key : playliststatistics.keySet() ) {
		
			if(key.equals(playlistOwner)){
				if(playlistOwner.equals(nickname)){// if creator of playlist likes own playlist then do nothing
					return "cannot vote for own playlist";
				}
				else {// if anyone else except creator of playlist is liking then...
				playliststatistics.get(key).setDislike(playliststatistics.get(key).getDislike()+1); // add like
					return "success";
				}
			}
		}
		
		return "error";
	}
	
	
}
