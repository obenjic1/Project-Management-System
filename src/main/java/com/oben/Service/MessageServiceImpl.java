package com.oben.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oben.Model.Chat;
import com.oben.Model.Message;
import com.oben.Model.Project;
import com.oben.Model.User;
import com.oben.Repository.MessageRepository;

@Service
public class MessageServiceImpl implements MessageService {
	
	@Autowired
	private ChatService chatService;
	@Autowired
	private UserService userService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private MessageRepository messageRepository;

	@Override
	public Message sendMessage(Long senderId, Long projectId, String content) throws Exception {
		
		User user = userService.findUserById(senderId);
		Chat chat = projectService.getProjectById(projectId).getChat();
		Message msg = new Message ();
		msg.setContent(content);
		msg.setSender(user);
		msg.setCreatedAt(LocalDateTime.now());
		msg.setChat(chat);
		messageRepository.save(msg);
		chat.getMessages().add(msg);
		return msg;
	}

	@Override
	public List<Message> getMessagesByProjectId(Long projectId) throws Exception {
		Project project = projectService.getProjectById(projectId);
		List<Message> messages = project.getChat().getMessages();
		Collections.reverse(messages);
		return messages;
	}

}
