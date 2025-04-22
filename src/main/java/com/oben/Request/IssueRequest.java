package com.oben.Request;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class IssueRequest {
	
	private String title;
	
	private String description;
	
	private String status;
	private Long projectID; 
	private String priority;
	private LocalDate dueDate;
	private List<String> tags = new ArrayList<>();




}
