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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oben.Model.Issues;
import com.oben.Model.MessageResponse;
import com.oben.Model.User;
import com.oben.Request.IssueRequest;
import com.oben.Service.IssueServiceImpl;
import com.oben.Service.ProjectService;
import com.oben.Service.UserService;

@RestController
@RequestMapping("/api/issues")
public class IssuesController {
	
	@Autowired
	private IssueServiceImpl issueService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProjectService projectService;
	
	
	@GetMapping("/{issueId}")
	public ResponseEntity<Issues> getIssueById(@PathVariable Long issueId) throws Exception{
		
		return new ResponseEntity<Issues>(issueService.getIssueById(issueId).get(),HttpStatus.OK);
		
	}
	
	
	@GetMapping("/project/{projectId}")
	public ResponseEntity<List<Issues>> getIssueByProjectId(@PathVariable Long projectId) throws Exception{
		
		return new ResponseEntity<List<Issues>>(projectService.getProjectById(projectId).getIssues(),HttpStatus.OK);
		
	}
	
	@PostMapping()
	public ResponseEntity<Issues> createIssue (@RequestBody IssueRequest issueResquest,@RequestHeader("Authorization") String token) throws Exception{
		
		
		User user = userService.findUserByJwt(token);	
		Issues	issue = issueService.createIssue(issueResquest, user.getId());
		
		return new ResponseEntity<Issues>(issue,HttpStatus.OK);
	}
	
	@DeleteMapping("/{issueId}")
	public ResponseEntity<MessageResponse> deleteIssues(@PathVariable Long issueId,@RequestHeader("Authorization") String jwt) throws Exception {
		
		issueService.deleteIssue(issueId, userService.findUserByJwt(jwt).getId());
		MessageResponse res = new MessageResponse();
		res.setMessage("this issue have been deleted successfully");
		
		return new ResponseEntity<MessageResponse>(res,HttpStatus.GONE);
	}
	
	@PutMapping("/{issueId}/asign/{userId}")
	public ResponseEntity<Issues> asignUserToIssue(@PathVariable Long issueId,@PathVariable Long userId) throws Exception{
		
		Issues issue =issueService.addUserToIssue(issueId, userId);
		return  new ResponseEntity<Issues>(issue,HttpStatus.ACCEPTED);
	}
	
	
	@PutMapping("/{issueId}/status/{status}")
	public ResponseEntity<Issues> updateIssueStatus(@PathVariable Long issueId,@PathVariable String status) throws Exception{
		
		Issues issue =issueService.updateStatus(issueId, status);
		return  new ResponseEntity<Issues>(issue,HttpStatus.ACCEPTED);
	}
}
