package com.somebunnycodes.projectmanager.models;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "projects")
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "Project title is required!")
	@Size(min = 3, max = 300, message = "Project title must be at least 3 characters")
	private String title;

	@NotEmpty(message = "Project description is required!")
	@Size(min = 3, max = 3000, message = "Write a project description, or enter N/A")
	private String description;

	@NotNull(message = "Project must have a future due date selected")
	@Future
	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
	private LocalDate dueDate;

	@ManyToOne(fetch = FetchType.LAZY)
	private User teamLeader;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "team_members", joinColumns = { @JoinColumn(name = "project_id") }, inverseJoinColumns = { @JoinColumn(name = "user_id") })
	private Set<User> teamMembers;

	@Column(updatable = false)
	@CreationTimestamp
	private Date createdAt;
	@UpdateTimestamp
	private Date updatedAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public User getTeamLeader() {
		return teamLeader;
	}

	public void setTeamLeader(User teamLeader) {
		this.teamLeader = teamLeader;
	}

	public Set<User> getTeamMembers() {
		return teamMembers;
	}

	public void addTeamMember(User u) {
		teamMembers.add(u);
	}
	
	public void removeTeamMember(User u) {
		teamMembers.remove(u);
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@PreUpdate
	@PrePersist
	public void updateTimeStamps() {
		updatedAt = new Date();
		if (createdAt == null) {
			createdAt = new Date();
		}
	}

}
