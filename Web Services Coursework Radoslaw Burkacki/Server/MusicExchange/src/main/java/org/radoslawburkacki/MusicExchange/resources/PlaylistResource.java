package org.radoslawburkacki.MusicExchange.resources;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.radoslawburkacki.MusicExchange.model.Account;
import org.radoslawburkacki.MusicExchange.model.Playlist;
import org.radoslawburkacki.MusicExchange.model.Song;
import org.radoslawburkacki.MusicExchange.model.Statistic;
import org.radoslawburkacki.MusicExchange.model.Top10Song;
import org.radoslawburkacki.MusicExchange.service.PlaylistService;

@Path("/playlists") // main path 
@Consumes(MediaType.APPLICATION_JSON) // consumes json
@Produces(MediaType.APPLICATION_JSON) // produes json
public class PlaylistResource {

	PlaylistService playlistservice = new PlaylistService();
	
	@GET
	public List<Playlist> getAllPlaylists(){
		return playlistservice.getAllPlaylists();
	}
	
	@GET
	@Path("/top10")
	public List<Playlist> getTop10Playlists(){
	 return playlistservice.getTop10Playlists();
		
		
	}
	
	@GET
	@Path("/top10songs")
	public List<Top10Song> getTop10Songs(){
		return playlistservice.getTop10Songs();
	}
	
	
	@GET// get one playlist by user nickname
	@Path("/{nickname}")
	public Response getPlaylist(@PathParam("nickname") String nickname){
		Playlist p = playlistservice.getPlaylist(nickname);
		if(playlistservice.getPlaylist(nickname)==null){
			return Response.status(Status.NO_CONTENT)
					.build();
		}
		else {return Response.status(Status.FOUND)
				.entity(p)
				.build();
	}
	}
	
	
	@POST
	@Path("/{nickname}")
	public Response addPlaylist(@PathParam("nickname") String nickname,Playlist playlist){
		Playlist newPlaylist = playlistservice.addPlaylist(nickname, playlist);
		
		if(newPlaylist.getTitle().equals("customer already has a playlist error")){
			return Response.status(Status.CONFLICT)
					.entity(newPlaylist)
					.build();

		}	
		else if(newPlaylist.getTitle().equals("name already in use")){
			return Response.status(Status.BAD_REQUEST)
					.entity(newPlaylist)
					.build();

		}	
		else {	return Response.status(Status.CREATED)
					.entity(newPlaylist)
					.build();
		}

	}
	
	@DELETE // delete function
	@Path("/{nickname}")
	public void deletePlaylist(@PathParam("nickname")String nickname){
		playlistservice.deletePlatlist(nickname);
	}

	
	
	@POST// post function
	@Path("/{nickname}/edit")
	public Response addSongToPlaylist(@PathParam("nickname") String nickname,Song s){
		
		Song newSong = playlistservice.addSongToPlaylist(nickname,s);

		if(newSong.getTitle().equals("song is already in playlist")){
			return Response.status(Status.CONFLICT)
					.entity(newSong)
					.build();

		}
		
		else {	return Response.status(Status.CREATED)
					.entity(newSong)
					.build();
		}
	}
	
	
	@DELETE // delete function
	@Path("/{nickname}/edit/{songindex}")
	public void removeSongFromPlaylist(@PathParam("nickname")String nickname,@PathParam("songindex")int songindex){
		playlistservice.deleteSong(nickname, songindex);
	}
	
}
