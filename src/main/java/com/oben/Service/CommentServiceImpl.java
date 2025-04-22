 package com.oben.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oben.Model.Comments;
import com.oben.Model.Issues;
import com.oben.Model.User;
import com.oben.Repository.CommentRepository;

@Service

public class CommentServiceImpl implements CommentService {
	
	
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private IssueService issueService;

	@Override
	public Comments createComment(Long issueId, Long userId, String comment) throws Exception {
		
		Issues issue = issueService.getIssueById(issueId).get();
		User user = userService.findUserById(userId);
		
		if (issue.getProject().getTeam().contains(user)) {
			
			Comments newComment = new Comments();
			newComment.setContents(comment);
			newComment.setCreatedDate(LocalDateTime.now());
			newComment.setIssue(issue);
			newComment.setUser(user);
			commentRepository.save(newComment);
			issue.getComments().add(newComment);
			return  newComment;
			
		} else {
			throw new Exception (" user doesnt belong to this project team");
		}
		
	}

	@Override
	public void deleteComment(Long commentId, Long userId) throws Exception {
		
		User user = userService.findUserById(userId);
		Comments commnent =commentRepository.findById(commentId).get();
		if(commnent.getUser().equals(user)) {
			
			commentRepository.delete(commnent);
			
		} else {
			throw new Exception(" you are not the owner of this comment you dont have permission to delete it ");
		}
		
	}

	@Override
	public List<Comments> findCommentByIssue(Long issueId) throws Exception {
		return issueService.getIssueById(issueId).get().getComments();
	}
	

}
