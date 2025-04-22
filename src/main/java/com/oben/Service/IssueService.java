package com.oben.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.oben.Model.Issues;
import com.oben.Request.IssueRequest;

@Service
public interface IssueService {
	
	Optional<Issues> getIssueById(Long id)throws Exception;
	List<Issues> getIssuesByProjectId(Long projectId) throws Exception;
	Issues createIssue(IssueRequest issue,Long userId) throws Exception;
	Optional<Issues> updateIssues(IssueRequest issue,Long userId,Long issueId) throws Exception;
	void deleteIssue(Long issueId,Long userId) throws  Exception;
	List<Issues> searchIssues (String title,String status,String priority, Long assigneeId) throws Exception;
	Issues addUserToIssue(Long ussureId,Long userId) throws Exception;
	Issues updateStatus (Long issueId,String status)throws Exception;
	
	

}
