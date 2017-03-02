package org.radoslawburkacki.client;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
/** Radoslaw Burkacki B00309449
 Web services coursework
 21/11/2016
*/
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.radoslawburkacki.MusicExchange.model.Account;
import org.radoslawburkacki.MusicExchange.model.Playlist;
import org.radoslawburkacki.MusicExchange.model.Song;
import org.radoslawburkacki.MusicExchange.model.Statistic;
import org.radoslawburkacki.MusicExchange.model.Top10Song;

public class RestApiClient {// creating new class

	public static void main(String[] args) {// calling main function
		

		// this section is about creating url targets //////////////////////////////////////////
		Client client = ClientBuilder.newClient(); // creating new instance of client
		WebTarget baseTarget = client.target("http://localhost:8080/MusicExchange/webapi/"); // setting main url target
		WebTarget accountsTarget = baseTarget.path("accounts"); // setting accounts target
		WebTarget nicknameTarget = accountsTarget.path("{nickname}"); // setting nickname target (nickname is a variable)
		WebTarget accpassword = nicknameTarget.path("{password}"); // setting password target (password is a variable)
		
		WebTarget playlistsTarget = baseTarget.path("playlists"); // setting playlists target
		WebTarget playlisttop10Target = playlistsTarget.path("top10"); // setting top10 target
		WebTarget songtop10 = playlistsTarget.path("top10songs"); // setting top10songs target 
		WebTarget plnickTarget = playlistsTarget.path("{nickname}"); // setting nickname target (nickname is a variable)
		WebTarget editplaylistTarget = plnickTarget.path("edit"); // setting edit target
		WebTarget removesongTarget = editplaylistTarget.path("{songindex}"); // setting songindex target (songindex is a variable)
		
		WebTarget statisticsTarget = baseTarget.path("statistics"); // setting statistics target
		WebTarget rateTarget = statisticsTarget.path("rate"); // setting rate target
	
		// this section is about creating url targets //////////////////////////////////////////
		
		Scanner sc = new Scanner (System.in);  // creating new instance of scanner
		int menu =0; // creating new variable and setting its value to 0
		
		  try {
			  String strUrl = "http://localhost:8080/MusicExchange/webapi/accounts/";

		        URL url = new URL(strUrl);
		        HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
		        urlConn.connect();

		while(menu != 4){ // starting while loop, this loop will exit when manu will be equal to 4
			makeSpace();  // calling function (this function is simply making space in console)
			System.out.println("Menu:"); // printing message
			System.out.println("1. Register"); // printing message
			System.out.println("2. Login"); // printing message
			System.out.println("3. Contiune as guest"); // printing message
			System.out.println("4. Exit"); // printing message
			menu=sc.nextInt();  // whatever user enters is saved to menu variable
			
			switch (menu){ 	 // starting switch statement and saying at which variable it needs to look
			case 1:	 // starting case 1
				makeSpace();// calling function (this function is simply making space in console)
				String nickname,password; // creating two new variables nickname and password
				System.out.println("Register:"); //printing message
				sc.nextLine();
				System.out.println("Please pick username"); // printing message
				nickname = sc.nextLine(); 
				System.out.println("Please pick password"); // printing message
				password = sc.nextLine();
				
				Account newAccount = new Account(nickname,password); // creating new instance of Account and passing two variables into it
				Response postResponse;
				
				        postResponse = nicknameTarget // creating connection to web service and saving response to in into postResponse
						.resolveTemplate("nickname", newAccount.getNickname()) // resolving template, passing variable into it 
						.request(MediaType.APPLICATION_JSON) // requesting json response
						.post(Entity.json(newAccount)); // calling post function on web service, sending account(converting it into json).
				
				  
				       
				  
			
				if(postResponse.getStatus() == 409) // if response status code is equal to 409 then...
					System.out.println("Account already exists");
				
				if(postResponse.getStatus() == 201) // if response status code is equal to 201 then...
					System.out.println("Account created");
				
				
				  break; //breaks out of case 1
				  
				  
			case 2: // starting case 2
				
				makeSpace(); // calling function
				System.out.println("Please enter username");
				sc.nextLine();
				nickname = sc.nextLine();
				
				if(nickname.equals("")){	 // if user havent enterd anything into nickname
					do{		/** start do while loop */
						System.out.println("Please re-enter nickname");		/** Displaying message */
						nickname=sc.nextLine();	// save input again
					}while(nickname.equals("")); // end while loop when nickname is not empty
				}
				
				System.out.println("Please enter password");
				password = sc.nextLine();
	
				if(password.equals("")){	
					do{		/** start do while loop */
						System.out.println("Please re-enter password");		/** Displaying message */
						password=sc.nextLine();	
					}while(password.equals(""));
				}
				
				Account acc = accpassword // create connection with server, save esponse to acc
						.resolveTemplate("nickname", nickname) // pass nickname
						.resolveTemplate("password", password) // pass password
						.request(MediaType.APPLICATION_JSON) // request json response
						.get(Account.class); // call get function
				
				if(acc.getNickname().equals("true") & acc.getPassword().equals("true")){
					System.out.println("You have logged in successfully.");
					
					
					while(menu != 9){ // start while loop
						
						makeSpace();
						// menu
						System.out.println("1. Add playlist");
						System.out.println("2. View my playlist");
						System.out.println("3. Edit my playlist");
						System.out.println("4. Delete my playlist");
						System.out.println("5. Search playlists by creator");
						System.out.println("6. View all playlists");
						System.out.println("7. View TOP10 playlists");
						System.out.println("8. View TOP10 songs");
						System.out.println("9. Exit");
						menu = sc.nextInt();
						switch (menu){ 	
						case 1: // Create new playlist
							makeSpace();
							
							String title;
							System.out.println("Please enter title");
							sc.nextLine();
							title = sc.nextLine();
							
							if(title.equals("")){	/** if title is empty then... */
								do{		/** start do while loop */
									System.out.println("Please enter title");		/** Displaying message */
									title = sc.nextLine();	/** set title equal to input from user */
								}while(title.equals(""));	/** end while loop when title is not empty */
							}
							if(!Character.isUpperCase(title.charAt(0))){	/** if index 0 of title is lower case then...*/
								title = ToUpperCase(title);		/** set title equal to(call function ToUpperCase and pass title into it) */
							}
							
							Playlist newPlaylist = new Playlist();
							newPlaylist.setTitle(title);
					
							
							Response postResponse1 = plnickTarget// create connection with ws, save response 
									.resolveTemplate("nickname", nickname) // pass nickname 
									.request(MediaType.APPLICATION_JSON)
									.post(Entity.json(newPlaylist)); // call post function 
							
							if(postResponse1.getStatus() == 409)
								System.out.println("You already own a playlist");
							
							if(postResponse1.getStatus() == 400)
								System.out.println("Name already in use");
							
							if(postResponse1.getStatus() == 201)
								System.out.println("Playlist created");
							
							
							
							break; // break out of case 1
							
						case 2: // View My playlist
							makeSpace();
							boolean empty = true;
							Response rr = plnickTarget // start new connection, save response
									.resolveTemplate("nickname", nickname) // pass nickname
									.request(MediaType.APPLICATION_JSON) // request json response
									.get(); // call get function
							
							if(rr.getStatus() == 302){
								setPlaylistView(nickname);
								Playlist p = rr.readEntity(Playlist.class);
								System.out.println("      "+p.getTitle());
								
								for(int i = 0; i < p.getListofsongs().size(); i++) {
									empty=false;
									System.out.println("      Song "+(i+1) +"- "+ p.getListofsongs().get(i).getTitle() +"   "+ p.getListofsongs().get(i).getArtist() +"   " + p.getListofsongs().get(i).getAlbum() +"   "+ p.getListofsongs().get(i).getGenre());
								}
								
								if(empty){
									System.out.println("Empty Playlist");
								}
								
							}
							else if(rr.getStatus() == 204){
								System.out.println("Playlist not found");
							}
							break;
							
						case 3: // edit playlist
							makeSpace();
							// 1 need to check if user has a playlist if yes then display menu (add song, remove song,go back) if not then display error message
							
							Response r = plnickTarget
									.resolveTemplate("nickname", nickname)
									.request(MediaType.APPLICATION_JSON)
									.get();
							
							if(r.getStatus() == 204)
								System.out.println("Playlist not found");
							
							if(r.getStatus() == 302){
								System.out.println("1. Add new Song");
								System.out.println("2. Delete Song");
								System.out.println("3. Go back");
								
								
								menu =sc.nextInt();
								
								switch(menu){
								case 1:
									makeSpace();
									Song s = new Song();
									System.out.println("Please enter title");
									sc.nextLine();
									s.setTitle(sc.nextLine());
									System.out.println("Please enter artist");
									s.setArtist(sc.nextLine());
									System.out.println("Please enter album");
									s.setAlbum(sc.nextLine());
									System.out.println("Please enter genre");
									s.setGenre(sc.nextLine());
									
									if(s.getTitle().equals("")){	
										do{		/** start do while loop */
											System.out.println("Please re-enter title");		/** Displaying message */
											s.setTitle(sc.nextLine());	
										}while(s.getTitle().equals(""));
									}
									if(!Character.isUpperCase(s.getTitle().charAt(0))){	/** if index 0 of s.getTitle() is lower case then...*/
										s.setTitle(ToUpperCase(s.getTitle()));		
									}

									if(s.getArtist().equals("")){	
										do{		/** start do while loop */
											System.out.println("Please re-enter artist");		/** Displaying message */
											s.setArtist(sc.nextLine());	
										}while(s.getArtist().equals(""));
									}
									if(!Character.isUpperCase(s.getArtist().charAt(0))){	/** if index 0 of s.getArtist() is lower case then...*/
										s.setArtist(ToUpperCase(s.getArtist()));		
									}

									if(s.getAlbum().equals("")){	
										do{		/** start do while loop */
											System.out.println("Please re-enter album");		/** Displaying message */
											s.setAlbum(sc.nextLine());	
										}while(s.getAlbum().equals(""));
									}
									if(!Character.isUpperCase(s.getAlbum().charAt(0))){	/** if index 0 of s.getAlbum() is lower case then...*/
										s.setAlbum(ToUpperCase(s.getAlbum()));		
									}
									
									if(s.getGenre().equals("")){	
										do{		/** start do while loop */
											System.out.println("Please re-enter genre");		/** Displaying message */
											s.setGenre(sc.nextLine());	
										}while(s.getGenre().equals(""));
									}
									if(!Character.isUpperCase(s.getGenre().charAt(0))){	/** if index 0 of s.getGenre() is lower case then...*/
										s.setGenre(ToUpperCase(s.getGenre()));		
									}

								
									Response p1=editplaylistTarget
										.resolveTemplate("nickname", nickname)
										.request(MediaType.APPLICATION_JSON)
										.post(Entity.json(s));
									
									if (p1.getStatus() == 409){
										System.out.println("Song already in playlists.");
									} 
									if (p1.getStatus() == 201){
										System.out.println("Song has been added.");
									}
									
									break;
								case 2:
									makeSpace();
									Response rrr = plnickTarget
									.resolveTemplate("nickname", nickname)
									.request(MediaType.APPLICATION_JSON)
									.get();
									

									if(rrr.getStatus() == 302){  
										Playlist p = rrr.readEntity(Playlist.class);
										System.out.println(" " + p.getTitle() );
										for(int i = 0; i < p.getListofsongs().size(); i++) {
											System.out.println("      Song "+(i+1)+"- " +p.getListofsongs().get(i).getTitle() + "   " +
													p.getListofsongs().get(i).getArtist() + "   " +
													p.getListofsongs().get(i).getAlbum() + "   " +
													p.getListofsongs().get(i).getGenre());
										}
										
										if(!p.getListofsongs().isEmpty()){
										
										System.out.print("Which song would you like to delete?(Enter number)");
										int songindex;
										songindex = sc.nextInt()-1;
												
										removesongTarget.resolveTemplate("nickname", nickname)
														.resolveTemplate("songindex", songindex)
														.request(MediaType.APPLICATION_JSON)
														.delete();
										}
										else{
											System.out.println("Empty Playlist");
											
										}
									}
								
							
									break;
								}
							}
							break;
						case 4:
							makeSpace();
							
							Response resp = plnickTarget
							.resolveTemplate("nickname", nickname)
							.request(MediaType.APPLICATION_JSON)
							.get();
							
							
							plnickTarget.resolveTemplate("nickname", nickname)
							.request(MediaType.APPLICATION_JSON)
							.delete();
							
							if (resp.getStatus() ==204){
								System.out.println("No playlist found");
							}
							else{
								System.out.println("Playlist deleted");
							}
							break;
							
							
						case 5: //Search playlist by nickname
							makeSpace();
							if(searchPlaylistByNickname())
							votePlaylist(nickname);
							
							break;
						case 6: // view all playlists
							makeSpace();
							if(getAllPlaylists())
							votePlaylist(nickname);// make sure it works after function creation
							
							break;
							
						case 7:// view top10 playlists
								List <Playlist>Top10Playlists=	playlisttop10Target
												.request(MediaType.APPLICATION_JSON)
												.get(new GenericType<List<Playlist>>() {});
							

								     for(int i =0; i < Top10Playlists.size(); i++){
								    	 	
											System.out.println("-------------------------------------------------------");
											System.out.println("      "+Top10Playlists.get(i).getTitle()+ " TOP"+(i+1)+" Playlist("+Top10Playlists.get(i).getRankPoints()+"Rank Points)");
											for(int jj=0; jj < Top10Playlists.get(i).getListofsongs().size(); jj++){
												System.out.println("      Song "+(jj+1)+"- " +Top10Playlists.get(i).getListofsongs().get(jj).getTitle() + "   " +
														Top10Playlists.get(i).getListofsongs().get(jj).getArtist() + "   " +
														Top10Playlists.get(i).getListofsongs().get(jj).getAlbum() + "   " +
														Top10Playlists.get(i).getListofsongs().get(jj).getGenre());
											}
										}
								     
								     if(Top10Playlists.isEmpty()){
								    	 System.out.println("No playlists found");
								     }
							
							break;
							
						case 8:
							
							List<Top10Song> top10songs = new ArrayList<Top10Song>();
							
							top10songs = songtop10.request(MediaType.APPLICATION_JSON)
												.get(new GenericType<List<Top10Song>>() {});
							
							if(top10songs.isEmpty()){
								System.out.println("No songs found");
							}
							
							
							int i=0;
							for(Top10Song song: top10songs){
								System.out.println("-------------------------------------------------------");

								System.out.println("      Top "+(i +1)+" Song- "+song.getTitle()+"  "+song.getArtist()+"  "+song.getAlbum()+"  "+song.getGenre() +"|This song is in side of " + song.getRank() +" Playlists");
								i++;
							}
							break;
						}
					}
					
				}
					
				else{
					System.out.println("Wrong nickname/password");
				}
			
				
				
				
				  
				
				break;
				
			case 3:
				  int menu1 =0;
				while(menu1 != 3){
				makeSpace();
				System.out.println("Guest menu:");
				System.out.println("1. View all playlists");
				System.out.println("2. Search playlist by name");
				System.out.println("3. Go to main menu");
				menu1 = sc.nextInt();
		
				
					switch(menu1){
					
					case 1:
						getAllPlaylists();
						
					
						break;
						
					case 2:						
						searchPlaylistByNickname();
						break;
					}	
					
				
				}
				break;
				
			case 4:
				
				System.out.println("Closing application");
				break;
				
			case 5:
				
			
				  break;
				
				
			}
			
			
		}
		  } catch (IOException e) {
		        System.err.println("Web Service is currently off");
		    }
	
	
	}
	
	public static void votePlaylist(String nickname){
		Client client = ClientBuilder.newClient();
		WebTarget baseTarget = client.target("http://localhost:8080/MusicExchange/webapi/");
		WebTarget statisticsTarget = baseTarget.path("statistics");
		WebTarget rateTarget = statisticsTarget.path("rate");
		WebTarget titleTarget = rateTarget.path("{title}");
		WebTarget nicknameTarget1 = titleTarget.path("{nickname}");
		
		Scanner sc = new Scanner (System.in);
		System.out.println("Would you like to rate playlist?Y/N");
		String answer; 
		answer =sc.nextLine();

		if(answer.equals("")){	
			do{		/** start do while loop */
				System.out.println("Please enter title");		/** Displaying message */
				answer.equals(sc.nextLine());	
			}while(answer.equals(""));
		}
		if(!Character.isUpperCase(answer.charAt(0))){	/** if index 0 of s.getTitle() is lower case then...*/
			answer = answer.substring(0, 1).toUpperCase() + answer.substring(1);		
		}
		
		if(answer.equals("Y")){
			System.out.println("Enter title of the playlist");
			String ttitle;
			ttitle = sc.nextLine();
			if(ttitle.equals("")){	
				do{		/** start do while loop */
					System.out.println("Enter title of the playlist");		/** Displaying message */
					ttitle=sc.nextLine();	
				}while(ttitle.equals(""));
			}
			if(!Character.isUpperCase(ttitle.charAt(0))){	/** if index 0 of s.getTitle() is lower case then...*/
				ttitle = ttitle.substring(0, 1).toUpperCase() + ttitle.substring(1);		

			}
			
			
			System.out.println("Did you like this playlist? Like/Dislike");
			String rate;
			rate = sc.nextLine();
			
			if(rate.equals("")){	
				do{		/** start do while loop */
					System.out.println("Did you like this playlist? Like/Dislike");		/** Displaying message */
					rate=sc.nextLine();	
				}while(rate.equals(""));
			}
			if(!Character.isUpperCase(rate.charAt(0))){	/** if index 0 of s.getTitle() is lower case then...*/
				rate = rate.substring(0, 1).toUpperCase() + rate.substring(1);		

			}
			
			Statistic s = new Statistic();
			if(rate.equals("Like"))
				s.setLike(1);
			
			else if(rate.equals("Dislike"))
				s.setDislike(1);
			
			Response r1=nicknameTarget1
						.resolveTemplate("title", ttitle)
						.resolveTemplate("nickname", nickname)
						.request(MediaType.APPLICATION_JSON)
						.post(Entity.json(s));
			
			if (r1.getStatus() ==409){
				System.out.println("Cannot vote for own playlist");
			}
			
			else if(r1.getStatus() == 404){
				System.out.println("Not Found");
			}
			
			else if(r1.getStatus() == 201){
				System.out.println("Voted Successfuly");
			}
		
		}
	}
	
	public static boolean searchPlaylistByNickname(){
		Client client = ClientBuilder.newClient();
		WebTarget baseTarget = client.target("http://localhost:8080/MusicExchange/webapi/");
		WebTarget playlistsTarget = baseTarget.path("playlists");
		WebTarget plnickTarget = playlistsTarget.path("{nickname}");
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter nickname of creator");
		//sc.nextLine();
		String nick;
		nick = sc.nextLine();
		setPlaylistView(nick);
		boolean emptyy = true;
		Response r1 = plnickTarget
				.resolveTemplate("nickname", nick)
				.request(MediaType.APPLICATION_JSON)
				.get();
		
		if(r1.getStatus() == 302){
			Playlist p = r1.readEntity(Playlist.class);
			System.out.println("      "+p.getTitle());
			for(int i = 0; i < p.getListofsongs().size(); i++) {
				emptyy=false;
				System.out.println("      Song "+(i+1)+"- " + p.getListofsongs().get(i).getTitle() +"   "+ p.getListofsongs().get(i).getArtist() +"   " + p.getListofsongs().get(i).getAlbum() +"   "+ p.getListofsongs().get(i).getGenre());
			}
			
			if(emptyy){
				System.out.println("Empty Playlist");
				
			}
			return true;
			
		}
		else if(r1.getStatus() == 204){
			System.out.println("Playlist not found");
		}
		return false;
	}
	
	public static void setPlaylistView(String nickname){
	
		
		Client client = ClientBuilder.newClient();
		WebTarget baseTarget = client.target("http://localhost:8080/MusicExchange/webapi/");
		WebTarget statisticsTarget = baseTarget.path("statistics");
		WebTarget nicknameTarget = statisticsTarget.path(nickname);
		WebTarget viewTarget = nicknameTarget.path("view");
		
		Statistic s = new Statistic();
		
	
		viewTarget
				.resolveTemplate("nickname", nickname)
				.request(MediaType.APPLICATION_JSON)
				.put(Entity.entity(s, MediaType.APPLICATION_JSON));
	}
	
	
	public static boolean getAllPlaylists(){
		Client client = ClientBuilder.newClient();
		
		List<Playlist> pl =	client.target("http://localhost:8080/MusicExchange/webapi/")
				.path("playlists")
				.request(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<Playlist>>() {});
		
		for(int i =0; i < pl.size(); i++){
			System.out.println("-------------------------------------------------------");
			System.out.println("      "+pl.get(i).getTitle());
			for(int j=0; j < pl.get(i).getListofsongs().size(); j++){
				System.out.println("      Song "+(j+1)+"- " +pl.get(i).getListofsongs().get(j).getTitle() + "   " +
								pl.get(i).getListofsongs().get(j).getArtist() + "   " +
								pl.get(i).getListofsongs().get(j).getAlbum() + "   " +
								pl.get(i).getListofsongs().get(j).getGenre());
			}
		}
		
		if(!pl.isEmpty()){
			
			Client client1 = ClientBuilder.newClient();
			WebTarget baseTarget = client1.target("http://localhost:8080/MusicExchange/webapi/");
			WebTarget statisticsTarget = baseTarget.path("statistics");
			WebTarget viewTarget = statisticsTarget.path("view");
		
			Statistic s = new Statistic();
			viewTarget
				.request(MediaType.APPLICATION_JSON)
				.put(Entity.entity(s, MediaType.APPLICATION_JSON));
			
			
			return true;
		}
		
		if(pl.isEmpty()){
			System.out.println("-------------------------------------------------------");
			System.out.println("No playlists found");
		}
		return false;
	}
	
	public static void makeSpace(){
		for(int i=0; i < 1; i++){
			System.out.println("-------------------------------------------------------");
		}
	}
	
	public static String ToUpperCase(String s){
		/**it takes the string and sets the first letter of string to capital letter*/

		
		String ss  = s; /** Create new variable called s which is equal to title*/
		ss = ss.substring(0,1).toUpperCase() + ss.substring(1).toLowerCase();/** setting first letter of s (index 0) to upper case and rest of them to lower*/
		s = ss; /** set s equal to ss */

		return s; /** @return s*/
		
	}

}
