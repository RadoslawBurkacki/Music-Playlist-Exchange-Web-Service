package org.radoslawburkacki.MusicExchange.resources;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.radoslawburkacki.MusicExchange.model.Account;
import org.radoslawburkacki.MusicExchange.service.AccountService;

@Path("/accounts")// set main path
@Consumes(MediaType.APPLICATION_JSON)  // consume json 
@Produces(MediaType.APPLICATION_JSON) // produce json
public class AccountResource {
	
	AccountService accountservice = new AccountService(); // creating new instance 

	
	@GET // get function
	public List<Account> getAllAccounts(){ // this function will return a list of account objects
		return accountservice.getAllAccounts(); // return what ever was returned by function .getallaccounts
		
	}
	
	@POST// post function
	@Path("/{nickname}") // path for this function is accounts/{nickname} | nickname is a variable!
	public Response addAccount(@PathParam("nickname") String nickname,Account acc){ // this function will return a response/ it also consumes param and saves it to nickname and it consumes an object of account
		Account newAccount = accountservice.addAccount(nickname, acc);
		
		
		if(newAccount.getNickname().equals("failed") & newAccount.getPassword().equals("failed")){
			return Response.status(Status.CONFLICT)
					.entity(newAccount)
					.build();

		}
		
		else {	return Response.status(Status.CREATED)
					.entity(newAccount)
					.build();
		}
		
		//return accountservice.addAccount(nickname,acc);
		
	}

	
	@GET // get function
	@Path("/{nickname}/{password}") // path / accounts/{nickname}/{password} | nickname and password is a variable
	public Account validateAccount(@PathParam("nickname")String nickname, @PathParam("password") String password){ // save path params
	return accountservice.validateAccount(nickname , password);
	}
}
