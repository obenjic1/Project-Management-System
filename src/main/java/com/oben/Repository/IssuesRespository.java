package com.oben.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oben.Model.Issues;

@Repository
public interface IssuesRespository extends JpaRepository<Issues, Long> {
//	Optional<Issues> getIssueById(Long id) throws Exception;

}
