package com.oben.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Issues {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String title;
	
	private String description;
	
	private String status;
	private long projectID; 
	private String priority;
	private LocalDate dueDate;
	
	private List<String> tags = new ArrayList<String>();
	
	//@JsonIgnore
	@ManyToOne
	private User assignee;
	
	@JsonIgnore
	@ManyToOne
	private Project project;
	
	@JsonIgnore
	@OneToMany(mappedBy = "issue",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Comments> comments = new ArrayList<Comments>();

}
