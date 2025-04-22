package com.oben.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oben.Model.Comments;

@Repository
public interface CommentRepository extends JpaRepository<Comments, Long> {

}
