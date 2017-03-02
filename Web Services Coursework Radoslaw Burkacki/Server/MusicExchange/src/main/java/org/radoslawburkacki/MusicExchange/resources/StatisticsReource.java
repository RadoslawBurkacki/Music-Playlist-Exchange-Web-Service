package org.radoslawburkacki.MusicExchange.resources;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.radoslawburkacki.MusicExchange.model.Statistic;
import org.radoslawburkacki.MusicExchange.service.StatisticsService;

@Path("/statistics")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StatisticsReource {

	StatisticsService statisticservice = new StatisticsService();

	
	
	@GET
	@Path("/{nickname}")
	public Statistic getPlaylistStatistics(@PathParam("nickname") String nickname){
		return statisticservice.getPlaylistStatistic(nickname);
	}
	
	@PUT
	@Path("/view")
	public void setAllPlaylistView(){
		statisticservice.increaseViewOfAllPlaylists();	
	}
	
	
	
	@PUT
	@Path("/{nickname}/view")
	public void setPlaylistView(@PathParam("nickname") String nickname, Statistic s){
		statisticservice.increaseViewOfOnePlalist(nickname);
	}
	
	@POST
	@Path("/rate/{title}/{nickname}")
	public Response ratePlaylist(@PathParam("title") String title, @PathParam("nickname") String nickname, Statistic s){
		String response = null;
		if(s.getLike()==1){
			response =statisticservice.likePlaylist(title,nickname,s);
		}
		else if(s.getDislike()==1){
			response =statisticservice.dislikePlaylist(title,nickname,s);
		}
		
		if(response.equals("cannot vote for own playlist")){
			return Response.status(Status.CONFLICT)
						.build();
		}
		else if(response.equals("error")){
			return Response.status(Status.NOT_FOUND)
					.build();
		}
		else{
			return Response.status(Status.CREATED)
					.build();
		}

	}
	
	
	
	
}
