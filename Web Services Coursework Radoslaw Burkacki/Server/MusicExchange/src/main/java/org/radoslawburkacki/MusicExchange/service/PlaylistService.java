package org.radoslawburkacki.MusicExchange.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.radoslawburkacki.MusicExchange.database.DatabaseClass;
import org.radoslawburkacki.MusicExchange.model.Playlist;
import org.radoslawburkacki.MusicExchange.model.Song;
import org.radoslawburkacki.MusicExchange.model.Statistic;
import org.radoslawburkacki.MusicExchange.model.Top10Song;

public class PlaylistService {
	
	private Map<String, Playlist> playlists = DatabaseClass.getPlaylists();
	private Map<String, Statistic> playliststatistics = DatabaseClass.getPlaylistStatistics();
	private List<Song> songdb = DatabaseClass.getsongdb();
	
	

	public List<Playlist> getAllPlaylists(){
		return new ArrayList<Playlist>(playlists.values());
	}
	
	
	public Playlist getPlaylist(String nickname){
	
		return playlists.get(nickname);
	}

	
	public List<Playlist> getTop10Playlists(){
		Map<String, Integer> playlistRankPoints = new HashMap<String, Integer>();
		
		for ( String key : playliststatistics.keySet() ) {
			int like = playliststatistics.get(key).getLike();
			int dislike = playliststatistics.get(key).getDislike();
			int views = playliststatistics.get(key).getViews()/2;
			
			int points = (like - dislike) + ( views);
			playlistRankPoints.put(key, points);
		}
		
		playlistRankPoints = sortTop10(playlistRankPoints);
		
		List < Integer> list = new ArrayList<Integer>();
		
		 for(String key : playlistRankPoints.keySet()){
	        	list.add(playlistRankPoints.get(key));
	      }
	        
	     
		Map<String, Integer> Top10Playlists = new HashMap<String, Integer>();

		if(list.size() >= 11){
			int i = list.size();
			for(int j =0; j <10 ; j++){
				i--;
				for(String key : playlistRankPoints.keySet()){
					if(playlistRankPoints.get(key) == list.get(i)){
						Top10Playlists.put(key, playlistRankPoints.get(key));
					}
				
					
				}
			}
			
		}
		
		else if (list.size() < 11){
			Top10Playlists = playlistRankPoints;
		}
		
		List<Playlist> top10 = new ArrayList<Playlist>();
		
		for(String key : Top10Playlists.keySet()){
			playlists.get(key).setRankPoints(Top10Playlists.get(key));
			top10.add(playlists.get(key));
		}
		
		
		return top10;

	}
	
	public static <String, Integer extends Comparable<? super Integer>> Map<String, Integer> sortTop10( Map<String, Integer> map ){
	     List<Map.Entry<String, Integer>> list =
	         new LinkedList<Map.Entry<String, Integer>>( map.entrySet() );
	     Collections.sort( list, new Comparator<Map.Entry<String, Integer>>()
	     {
	         public int compare( Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2 )
	         {
	             return (o2.getValue()).compareTo( o1.getValue() );
	         }
	     } );

	     Map<String, Integer> result = new LinkedHashMap<String, Integer>();
	     for (Map.Entry<String, Integer> entry : list)
	     {
	         result.put( entry.getKey(), entry.getValue() );
	     }
	     return result;
	 }
	
	
	public Playlist addPlaylist(String nickname, Playlist playlist){
		
		
		if (playlists.containsKey(nickname)){ //checking if user has a playlist
			playlist.setTitle("customer already has a playlist error");
		}
		

		
		else if(!playlists.containsKey(nickname)){ // if user doesnt have a playlist then...

			for(Playlist s: playlists.values()){ // loop through playlists
				if(s.getTitle().equals(playlist.getTitle())){ // if any playlists title is equal to title of current playlist then
					playlist.setTitle("name already in use"); // set name of current playlist to "name already in use"
				}
			} 
			
			if((!playlist.getTitle().equals("custnomer already has a playlist error")) && (!playlist.getTitle().equals("name already in use"))){
				playliststatistics.put(nickname, new Statistic());
				playlists.put(nickname, playlist);
			}
	
		}
		
		return playlist;
	}
	
	
	
	public Song addSongToPlaylist(String nickname, Song s){ // receiving nickname of user and song (title, artist, album genre)
		boolean a = false;
		 for(Song song : songdb) {
	            if(song.getTitle().equals(s.getTitle()) && song.getArtist().equals(s.getArtist()) && song.getAlbum().equals(s.getAlbum()) && song.getGenre().equals(s.getGenre())){
	            	a = true;
	            }
	        }
		 
		 if (a){
			 
		 }
		 else if(!a){
			 songdb.add(s);
		 }
				
		
		
		for(int i = 0; i < playlists.get(nickname).getListofsongs().size();i++){
			if(playlists.get(nickname).getListofsongs().get(i).getTitle().equals(s.getTitle()) && playlists.get(nickname).getListofsongs().get(i).getArtist().equals(s.getArtist()) && playlists.get(nickname).getListofsongs().get(i).getAlbum().equals(s.getAlbum())&& playlists.get(nickname).getListofsongs().get(i).getGenre().equals(s.getGenre())){
				s.setTitle("song is already in playlist");
			}
		}
		
		
		if(!s.getTitle().equals("song is already in playlist")){
			List<Song> songli = new ArrayList<Song>();
			songli = playlists.get(nickname).getListofsongs();
			songli.add(s);
			playlists.get(nickname).setListofsongs(songli);
		}
		
		
		
		return s;
	}
	
	public List<Top10Song> getTop10Songs(){

		List <Top10Song> top10songs = new ArrayList<Top10Song>();
		
		for(int i =0; i < songdb.size(); i++){// loop through song database
			Top10Song s= new Top10Song();
			s.setTitle(songdb.get(i).getTitle()); s.setArtist(songdb.get(i).getArtist()); s.setAlbum(songdb.get(i).getAlbum()); s.setGenre(songdb.get(i).getGenre()); s.setRank(0);
			for(String key : playlists.keySet()){// loop through playlists
				for(int j=0; j < playlists.get(key).getListofsongs().size(); j++){// loop through songs inside of a playlist
					if(playlists.get(key).getListofsongs().get(j).getTitle().equals(s.getTitle()) && playlists.get(key).getListofsongs().get(j).getArtist().equals(s.getArtist()) && playlists.get(key).getListofsongs().get(j).getAlbum().equals(s.getAlbum()) && playlists.get(key).getListofsongs().get(j).getGenre().equals(s.getGenre())){
						s.setRank(s.getRank()+1);
					}

				}
			}
			top10songs.add(s);
		}
		
	
		  int j;
		     boolean flag = true;   // set flag to true to begin first pass
		     Top10Song temp = new Top10Song();

		     while ( flag )
		     {
		            flag= false;    //set flag to false awaiting a possible swap
		            for( j=0;  j < top10songs.size() -1;  j++ )
		            {
		                   if ( top10songs.get(j).getRank() < top10songs.get(j+1).getRank() )  
		                   {
		                           temp = top10songs.get(j);               
		                           top10songs.add(j, top10songs.get(j+1)); 
		                           top10songs.remove(j+1);
		                           top10songs.add(j+1, temp); 
		                          flag = true;               
		                  } 
		            } 
		      } 
		     
		     List <Top10Song> top10songss = new ArrayList<Top10Song>();
				
		     if(top10songs.size() > 10){
		    	 for(int i =0; i < 10; i++){
		    		 top10songss.add(top10songs.get(i));
		    	 }
		    	 return top10songss;
		     }
		     
		return top10songs;

	}
	
	
	public void deleteSong(String nickname, int songindex){
		playlists.get(nickname).getListofsongs().remove(songindex);
	}


	public void deletePlatlist(String nickname) {
		playlists.remove(nickname);
		playliststatistics.remove(nickname);
	
	}
	


}
