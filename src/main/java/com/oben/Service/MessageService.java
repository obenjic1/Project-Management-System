package com.oben.Service;

import java.util.List;

import com.oben.Model.Message;

public interface MessageService {
	
	Message sendMessage (Long senderId, Long chatId,String content)throws Exception;
	List<Message> getMessagesByProjectId(Long projectId)throws Exception;

}
