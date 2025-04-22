package com.oben.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oben.Model.Message;

public interface MessageRepository  extends JpaRepository<Message, Long>{
	
	Message save (Message msg);
	

}
