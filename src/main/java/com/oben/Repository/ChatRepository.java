package com.oben.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oben.Model.Chat;
import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
	
	Chat  findById(long id);
	
	

}
