package com.oben.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oben.Model.Invitation;

@Repository
public interface InvitationRepositoy extends JpaRepository<Invitation, Long> {
	
	Invitation findByEmail(String email);
	Invitation findByToken(String token);


}
