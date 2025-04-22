package com.oben.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;
import org.springframework.stereotype.Repository;

import com.oben.Model.User;
import java.util.List;


@Repository

public interface UserRepository extends JpaRepository<User,Long>{
	
	User findByEmail(String email);
	User findByFullName(String fullName);

}
