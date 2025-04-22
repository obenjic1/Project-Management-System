package com.oben.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oben.Model.Comments;

@Service
public interface CommentService {
	
	Comments createComment (Long issueId,Long userId,String comment) throws Exception;
	
	void deleteComment(Long commentId, Long userId) throws Exception;
	
	List<Comments> findCommentByIssue(Long issueId) throws Exception;

}
