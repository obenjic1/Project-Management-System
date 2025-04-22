package com.oben.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oben.Model.Chat;
import com.oben.Model.Project;
import com.oben.Model.User;

@Service
public interface ProjectService {

	
	Project createProject (Project project,User user)throws Exception;
	
	Project getProjectById(Long projectId)throws Exception;
	
	List<Project> getProjectByTeam(User user,String category,String tag) throws Exception;
	
	Project updateProject (Project project,Long projectId) throws Exception;
	
	void addUserToProject (Long ProjectId, Long userId) throws Exception;
	
	void removeUserFromProject (Long ProjectId, Long userId) throws Exception;
	
	Chat getChatByProjectId(Long projectID)throws Exception;
	void deleteProject (Long ProjectId, Long userId) throws Exception;
	
	List<Project> searchProjects(String keyWord,User user) throws Exception;


	
	
	
	
	

}
