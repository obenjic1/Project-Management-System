package com.oben.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.stereotype.Service;

import com.oben.Model.User;
import com.oben.Repository.UserRepository;
import com.oben.config.JwtProvider;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;

@Service
public class UserServiceImpl implements UserService {

		@Autowired
		private UserRepository userRepository;
		
	@Override
	public User findUserByJwt(String jwt) throws Exception {
		String email = JwtProvider.getEmailFromToken(jwt);
		
		return findUserByEmail(email);
	}
		
		

	@Override
	public User findUserByEmail(String email) throws Exception {
		User user = userRepository.findByEmail(email);
		if(user ==null) {
			throw new Exception("user not found");
		}
		return user ;
	}

	@Override
	public User findUserById(Long id) throws Exception {
		
		Optional<User> user = userRepository.findById(id);
		if(user.isEmpty()) {
			throw new Exception("user not found");
		}
		return user.get() ;}

	@Override
	public User updateUserProjectSixe(User user, int number) throws Exception {
		user.setProjectSize(user.getProjectSize() + number);
		
		return userRepository.save(user);
	}



	@Override
	public User findUserByFullName(String fullName) throws Exception {
		User user = userRepository.findByFullName(fullName);
		if(user!=null) {
			return user;

		} else {
			throw new Exception ("user with this id not found");
		}
		
	}

}
