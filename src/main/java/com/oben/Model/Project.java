package com.oben.Model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Project {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;

	private String name ;
	private String description ;
	private String category;
	
	private List<String> tags =new ArrayList<String>();
	
	@JsonIgnore
	@OneToOne(mappedBy = "project",cascade = CascadeType.ALL,orphanRemoval = true)
	private Chat chat ;
	
	
	@ManyToOne
	private User user;
	
	@OneToMany(mappedBy = "project",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Issues> issues = new ArrayList<Issues>();
	
	
	@ManyToMany
	private List<User> team = new ArrayList<User>();
	
}
