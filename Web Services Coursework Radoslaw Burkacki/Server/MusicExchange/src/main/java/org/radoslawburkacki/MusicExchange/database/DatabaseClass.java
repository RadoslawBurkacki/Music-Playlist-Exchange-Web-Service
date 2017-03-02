package org.radoslawburkacki.MusicExchange.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.radoslawburkacki.MusicExchange.model.Account;
import org.radoslawburkacki.MusicExchange.model.Playlist;
import org.radoslawburkacki.MusicExchange.model.Song;
import org.radoslawburkacki.MusicExchange.model.Statistic;

public class DatabaseClass {
	
	
	
	private static Map<String, Playlist> playlists = new HashMap<String, Playlist>();
	private static Map<String, Account> accounts = new HashMap<String, Account>();
	private static Map<String, Statistic> playliststatistics = new HashMap<String, Statistic>();
	private static List<Song> songdb = new ArrayList<Song>();
	
	public static Map<String,Account> getAccounts (){
		return accounts;	
	}
	
	public static Map<String,Playlist> getPlaylists (){
		return playlists;	
	}
	
	public static Map<String, Statistic> getPlaylistStatistics (){
		return playliststatistics;	
	}
	
 
	public static List<Song> getsongdb(){
		return songdb;
	}
}
