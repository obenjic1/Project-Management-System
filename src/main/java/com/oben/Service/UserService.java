package com.oben.Service;

import org.springframework.stereotype.Service;

import com.oben.Model.User;

@Service
public interface UserService {
	
	
	User findUserByJwt(String jwt)throws Exception;
	User findUserByFullName(String fullName)throws Exception;

	User findUserByEmail (String email)throws Exception;
	User findUserById(Long id)throws Exception;
	User updateUserProjectSixe(User user,int number)throws Exception;
	

	

	

}
