package com.oben.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oben.Model.Issues;
import com.oben.Model.Project;
import com.oben.Model.User;
import com.oben.Repository.IssuesRespository;
import com.oben.Request.IssueRequest;

@Service
public class IssueServiceImpl implements IssueService {
	
	@Autowired
	private IssuesRespository  issueRepository;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private UserService userService;

	@Override
	public Optional<Issues> getIssueById(Long id) throws Exception {
		Optional<Issues> issue = issueRepository.findById(id);
		if(issue.isEmpty()) {
			throw new Exception("no issues found with this Id");
		}
		return issue;
	}

	@Override
	public List<Issues> getIssuesByProjectId(Long projectId) throws Exception {
		Project project = projectService.getProjectById(projectId);
		// if this doesnt work create this function in the repository
		return project.getIssues();
	}

	@Override
	public Issues createIssue(IssueRequest newIssue, Long userId) throws Exception {
		User user = userService.findUserById(userId);
		Issues issue = new Issues();
		issue.setAssignee(user);
		issue.setTitle(newIssue.getTitle());
		issue.setStatus(newIssue.getStatus());
		issue.setDescription(newIssue.getDescription());
		issue.setPriority(newIssue.getPriority());
		issue.setDueDate(newIssue.getDueDate());
		issue.setProjectID(newIssue.getProjectID());
		issue.setTags(newIssue.getTags());
		issue.setProject(projectService.getProjectById(newIssue.getProjectID()));
	
		return issueRepository.save(issue);
	}

	@Override
	public Optional<Issues> updateIssues(IssueRequest issue, Long userId, Long issueId) throws Exception {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public void deleteIssue(Long issueId, Long userId) throws Exception {
		
		if(issueRepository.findById(issueId).get().getAssignee().equals(userService.findUserById(userId))) {
			issueRepository.deleteById(issueId);

		} else throw new Exception(" only the asignee user can delete this issue");
	}

	@Override
	public List<Issues> searchIssues(String title, String status, String priority, Long assigneeId) throws Exception {
		List<Issues> issues = new ArrayList<>();
		
	//	if(title)
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Issues addUserToIssue(Long issureId, Long userId) throws Exception {
		User user = userService.findUserById(userId);
		Issues issue = issueRepository.findById(issureId).get();
		
		if(issue.getAssignee().equals(user)) {
			throw new Exception ("user already assign to this issue");
		}
		issue.setAssignee(user);
		
		return  issueRepository.save(issue);
	}

	@Override
	public Issues updateStatus(Long issueId, String status) throws Exception {
		Issues issue = issueRepository.findById(issueId).get();	
		issue.setStatus(status);
		return  issueRepository.save(issue);
	}

}
