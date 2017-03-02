package org.radoslawburkacki.MusicExchange.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.radoslawburkacki.MusicExchange.database.DatabaseClass;
import org.radoslawburkacki.MusicExchange.model.Account;

public class AccountService {
	
	private Map<String, Account> accounts = DatabaseClass.getAccounts(); // create new map which equals to what ever is returned by function

	public AccountService(){
		Account acc = new Account();
		
	
	}
	
	
	public List<Account> getAllAccounts(){
		return new ArrayList<Account>(accounts.values());
	}
	
	public Account addAccount(String nickname, Account acc){
		if (accounts.containsKey(nickname)){
			acc.setNickname("failed");
			acc.setPassword("failed");
		}
		else if(!accounts.containsKey(nickname))
		accounts.put(nickname, acc);
		return acc;
	}
	
	public Account validateAccount(String nickname, String password){
		Account acc = new Account(nickname,password);
		Account a= new Account();
		if (accounts.containsKey(nickname)){
			a = accounts.get(nickname);
			if(acc.getNickname().equals(a.getNickname()) && acc.getPassword().equals(a.getPassword())){
				acc.setNickname("true");
				acc.setPassword("true");
			}
		}
		return acc;
	}



}
