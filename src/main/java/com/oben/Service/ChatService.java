package com.oben.Service;

import org.springframework.stereotype.Service;

import com.oben.Model.Chat;

@Service
public interface ChatService {
	
	Chat createChat (Chat chat);
	Chat findById (Long chatId);


}
