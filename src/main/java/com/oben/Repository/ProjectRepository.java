package com.oben.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.oben.Model.Project;
import com.oben.Model.User;

@Repository
public interface ProjectRepository  extends JpaRepository<Project, Long>{
	
	List<Project> findByUser(User user); ;
	List<Project> findByNameIsContainingAndTeamContains(String name,User user);
	List<Project> findByTeam(User user);
	List<Project> findByNameContainingAndTeamContaining(String keyWord, User user);
//	List<Project> findByTagsContainingOrNameContainingAndDescriptionContains(String keyWord, User user);
	
	
	
	

	
	
	
	

}
