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
import org.springframework.web.bind.annotation.RestController;

import com.oben.Model.Comments;
import com.oben.Model.MessageResponse;
import com.oben.Model.User;
import com.oben.Request.CommentRequest;
import com.oben.Service.CommentService;
import com.oben.Service.IssueService;
import com.oben.Service.UserService;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	@Autowired
	private UserService userService;
	@Autowired
	private IssueService issueService;
	
	@PostMapping()
	public ResponseEntity<Comments> createComment(@RequestBody CommentRequest comments, @RequestHeader("Authorization") String jwt ) throws Exception{
		
		User user = userService.findUserByJwt(jwt);
	
		Comments comment = commentService.createComment(comments.getIssueId(), user.getId(), comments.getContent());
		return new ResponseEntity<Comments>(comment, HttpStatus.CREATED);

	}
	
	
	@DeleteMapping("/{commentId}")
	public ResponseEntity<MessageResponse> deleteComment(@PathVariable Long commentId,@RequestHeader("Authorization") String jwt) throws Exception{
	
		User user = userService.findUserByJwt(jwt);
		commentService.deleteComment(commentId, user.getId());
		
		MessageResponse msg = new MessageResponse ();
		msg.setMessage(" comment deleted successfully");
		return new ResponseEntity<MessageResponse> (msg,HttpStatus.OK);
		
	}
	
	@GetMapping("/list/{issueId}")
	public ResponseEntity<List<Comments>> listComments(@PathVariable  Long issueId) throws Exception{
		
		List<Comments> comments = commentService.findCommentByIssue(issueId);
		return new  ResponseEntity<List<Comments>> (comments,HttpStatus.OK);
	}
	
}
