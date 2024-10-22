package com.oauth2server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oauth2server.model.Provider;
import com.oauth2server.model.User;

@Service
public class UserService {

	
	
	public void processOAuthPostLogin(String username) {
		User existUser = null;
		
		if (existUser == null) {
			User newUser = new User();
			newUser.setUsername(username);
			newUser.setProvider(Provider.GOOGLE);
			newUser.setEnabled(true);			
			
		}
		
	}
	
}
