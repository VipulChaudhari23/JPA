package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String description;

    @ManyToOne
    @JoinColumn(name = "created_by_user_id", nullable = false)
    private User createdBy;

    @Enumerated(EnumType.STRING)
    private IssueType issue;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public IssueType getIssue() {
		return issue;
	}

	public void setIssue(IssueType issue) {
		this.issue = issue;
	}

	public Feedback(Long id, String description, User createdBy, IssueType issue) {
		this.id = id;
		this.description = description;
		this.createdBy = createdBy;
		this.issue = issue;
	}
	

	public Feedback() {
	}

	@Override
	public String toString() {
		return "Feedback [id=" + id + ", description=" + description + ", createdBy=" + createdBy + ", issue=" + issue
				+ "]";
	}

	
}
