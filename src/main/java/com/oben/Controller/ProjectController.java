package com.oben.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oben.Model.Chat;
import com.oben.Model.Invitation;
import com.oben.Model.InviteRequest;
import com.oben.Model.MessageResponse;
import com.oben.Model.Project;
import com.oben.Model.User;
import com.oben.Service.InvitationService;
import com.oben.Service.ProjectService;
import com.oben.Service.ProjectServiceImpl;
import com.oben.Service.UserService;

@RestController
@RequestMapping("/api/projects")

public class ProjectController {
	
	
	@Autowired
	private ProjectServiceImpl projectServiceImpl;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private InvitationService invitationService; 
	
	
	@GetMapping()
	public ResponseEntity<List<Project>> getProjects(
			@RequestParam(required = false) String category,
			@RequestParam(required = false) String tag,
		

			@RequestHeader("Authorization") String jwt) throws Exception{
		
		
		//String fullName = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userService.findUserByJwt(jwt);
		List<Project> projects = projectServiceImpl.getProjectByTeam(user, category, tag);
		return new ResponseEntity<>(projects, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Project> getProject(@PathVariable Long id,@RequestHeader("Authorization") String jwt	) throws Exception{
			Project project = projectService.getProjectById(id);
			return new ResponseEntity<Project>(project, HttpStatus.OK);
		}
	
	@PostMapping()
	public ResponseEntity<Project> createProject(@RequestBody Project projectNew, 
			
			@RequestHeader("Authorization") String jwt	) throws Exception{
			User user = userService.findUserByJwt(jwt);
			Project project  = projectService.createProject(projectNew, user);
	
		return new ResponseEntity<Project>(project, HttpStatus.CREATED);
	}
	
	@PostMapping("/{projectId}")
	public ResponseEntity<Project> updateProject(@RequestBody Project projectNew, @PathVariable Long ProjectId,	@RequestHeader("Authorization") String jwt	) throws Exception{
	//	String fullName = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userService.findUserByJwt(jwt);
			
			Project project  = projectServiceImpl.updateProject(projectNew, ProjectId);
		return new ResponseEntity<Project>(project, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<MessageResponse> deleteProject(@PathVariable Long id,	@RequestHeader("Authorization") String jwt	) throws Exception{
		//	User user = userService.findUserById(UserId);
		System.out.println("deleting projects");
			User user = userService.findUserByJwt(jwt);
			projectServiceImpl.deleteProject(id, user.getId());
			MessageResponse res = new MessageResponse();
			res.setMessage("Project  has been deleted successfully");
		return new ResponseEntity<MessageResponse>(res,HttpStatus.GONE);
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<Project>> searchProjects(
			@RequestParam(required = false) String keyword,
			@RequestHeader("Authorization") String jwt) throws Exception{
		
		
		User user = userService.findUserByJwt(jwt);
		List<Project> projects = projectServiceImpl.searchProjects(keyword, user);
		return new ResponseEntity<>(projects, HttpStatus.OK);
	}
	
	@GetMapping("chat/{id}")
	public ResponseEntity<Chat> getChatByProjectId(@PathVariable Long id,	@RequestHeader("Authorization") String jwt	) throws Exception{
	//		projectServiceImpl.deleteProject(id, userService.findUserByJwt(jwt).getId());
		System.out.println("--------------------------------------------");
	//	System.out.println(projectService.getProjectById(id));
			Chat chats = projectServiceImpl.getChatByProjectId(id);
			
				return new ResponseEntity<Chat>(chats,HttpStatus.GONE);
	
		
	}
	
	@PostMapping("/invite")
	public ResponseEntity<MessageResponse> inviteProject(@RequestBody Project projectNew,@RequestBody InviteRequest invite, @PathVariable Long ProjectId,	@RequestHeader("Authorization") String jwt	) throws Exception{
			User user = userService.findUserByJwt(jwt);
			
			invitationService.sendInvitation(invite.getEmail(), invite.getProjectId());
			
			MessageResponse messageResponse = new MessageResponse();
			messageResponse.setMessage("invitation email has be send to his mail");
			
		return new ResponseEntity<MessageResponse>(messageResponse, HttpStatus.OK);
	}
	
	@GetMapping("/accept_Invitation")
	public ResponseEntity<Invitation> acceptInvitation(@RequestParam String token,@RequestHeader("Authorization") String jwt	) throws Exception{
		
			Invitation invitation = 	invitationService.acceptInvitation(token);
			projectServiceImpl.addUserToProject(invitation.getProjectId(), userService.findUserByEmail(invitation.getEmail()).getId());
			
		return new ResponseEntity<Invitation>(invitation, HttpStatus.ACCEPTED);
	}
}
