package com.oben.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oben.Model.Chat;
import com.oben.Model.Project;
import com.oben.Model.User;
import com.oben.Repository.ProjectRepository;

@Service
public class ProjectServiceImpl  implements ProjectService{


	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private ChatServiceImpl chatServiceImpl;

    
	@Override
	public Project createProject(Project project, User user) throws Exception {
		
		Project createdProject = new Project();
		createdProject.setCategory(project.getCategory());
		createdProject.setDescription(project.getDescription());
		createdProject.setName(project.getName());
		createdProject.setUser(user);
		createdProject.setTags(project.getTags());
		createdProject.getTeam().add(user);
		projectRepository.save(createdProject);

		Chat chat = new Chat ();
		chat.setProject(createdProject);
		Chat projectChat = chatServiceImpl.createChat(chat);
		createdProject.setChat(projectChat);
		
		
		return createdProject;
	}

	@Override
	public Project getProjectById(Long projectId) throws Exception {
		// TODO Auto-generated method stub
		return projectRepository.findById(projectId).get();
	}

	@Override
	public List<Project> getProjectByTeam(User user, String category, String tag) throws Exception {

		List<Project> projects = projectRepository.findByTeam(user);
		if(category!=null) {
			projects = projects.stream().filter(project ->project.getCategory().equals(category)).collect(Collectors.toList());
		}
		if(tag!=null) {
			projects = projects.stream().filter(project ->project.getTags().contains(tag)).collect(Collectors.toList());
		}
		return projects;
	}

	@Override
	public Project updateProject(Project project, Long projectId) throws Exception {
		Project Updatedproject = getProjectById(projectId);
		Updatedproject.setName(project.getName());
		Updatedproject.setDescription(project.getDescription());
		Updatedproject.setTags(project.getTags());
		
		return projectRepository.save(Updatedproject);
	}

	@Override
	public void addUserToProject(Long ProjectId, Long userId) throws Exception {
		 Project project = projectRepository.findById(ProjectId).get();
		 List<User> users = project.getTeam();

		if (!project.getTeam().contains(userService.findUserById(userId))){
			 users.add( userService.findUserById(userId));
			 project.getChat().getUser().add(userService.findUserById(userId));

		 }
		 projectRepository.save(project);

	}

	@Override
	public void removeUserFromProject(Long ProjectId, Long userId) throws Exception {
	 Project project = projectRepository.findById(ProjectId).get();
	 List<User> users = project.getTeam();
	 List<User> chatUsers = project.getChat().getUser();

		if (project.getTeam().contains(userService.findUserById(userId))){

	 users.remove( users.indexOf(userService.findUserById(userId)));
	 chatUsers.remove( chatUsers.indexOf(userService.findUserById(userId)));
		}
	 projectRepository.save(project);
		
	}

	@Override
	public Chat getChatByProjectId(Long projectId) throws Exception {
		 Project project = projectRepository.findById(projectId).get();
		 Chat chat = project.getChat();
		 if(chat!=null) {
			 return chat;
		 }
		 else {
			 throw new Exception ("No chat cof this Project");
		 }
	}

	@Override
	public void deleteProject(Long projectId, Long userId) throws Exception {
		 Project project = projectRepository.findById(projectId).get();
		 
		 if(project.getUser() ==userService.findUserById(userId) ) {
			 
			 projectRepository.delete(project);
		 } else {
			 throw new Exception (" this project can only be deleted by the owner");
			 
		 }
		
	}

	@Override
	public List<Project> searchProjects(String keyWord, User user) throws Exception {
		// TODO Auto-generated method stub
		return projectRepository.findByNameContainingAndTeamContaining(keyWord, user);
	}

}
