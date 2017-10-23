package com.guessnumber.repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import com.guessnumber.model.User;

import static com.guessnumber.constants.GameConstants.*;

/**
* Creates UserRepository for User
* Map act as datastore 
* Computer user auto add to the repository   
* @author nikhil
*/
@Repository
public class UsersRepository {
	
	public static Map<String,User> registeredUsers = new ConcurrentHashMap<>();
	
	/**
	 * Register user , return false if alredy registered
	 * @param newUser
	 * @return
	 */
	public boolean registerUser(User newUser){
		
		if(registeredUsers.containsKey(newUser.getUserName())) 
			return false;
		else
		registeredUsers.put(newUser.getUserName(),newUser);	
		
		return true ;
	}
	
	
	/**
	 * 
	 * @param userName
	 * @return
	 */
	public User find(String userName){
		return registeredUsers.get(userName);
	}
	

	/**
	 * Update user record
	 * @param user
	 */
	public void update(User user){
		registeredUsers.put(user.getUserName(),user);
	}
	
	/**
	 * Auto registration of computer user
	 */
	@PostConstruct
	public void registerComputerUser(){
		User computer = new User();
		computer.setUserName(COMPUTER);
		computer.addPoints(free_Points);
		registerUser(computer);
	}

}
