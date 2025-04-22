package com.oben.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oben.Model.Chat;
import com.oben.Repository.ChatRepository;

@Service
public class ChatServiceImpl implements ChatService{
	
	@Autowired
	private ChatRepository chatRepository;

	@Override
	public Chat createChat(Chat chat) {
		Chat newChat = new Chat();
		return chatRepository.save(chat);
	}

	@Override
	public Chat findById(Long chatId) {
		return chatRepository.findById(chatId).get();
	}

}
