package com.oben.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oben.Model.Message;
import com.oben.Model.Project;
import com.oben.Request.CreateMessageRequest;
import com.oben.Service.MessageService;
import com.oben.Service.ProjectService;
import com.oben.Service.UserService;

@RestController
@RequestMapping("/api/message")
public class MessageController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private MessageService messageService;
	
	
	
	@PostMapping("/send")
	public ResponseEntity<Message> sendMessge(@RequestBody CreateMessageRequest req, @RequestHeader("Authorization") String jwt) throws Exception{
		
		Project project = projectService.getProjectById(req.getProjectId());
		
		Message msg = messageService.sendMessage(userService.findUserByJwt(jwt).getId(),project.getId() , req.getContent());
		
		return new  ResponseEntity<Message>(msg,HttpStatus.CREATED);
		
		
	}
	
	@GetMapping("/{projectId}")
	public  ResponseEntity<List<Message>> getMessagesByProjectId(@PathVariable Long projectId) throws Exception{
	//	Project project = projectService.getProjectById(projectId);
		List<Message> msg = messageService.getMessagesByProjectId(projectId);
		return new  ResponseEntity<List<Message>>(msg,HttpStatus.CREATED);
		
	}
}
