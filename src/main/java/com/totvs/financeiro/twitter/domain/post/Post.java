package com.totvs.financeiro.twitter.domain.post;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.totvs.financeiro.twitter.domain.comments.Comments;
import com.totvs.financeiro.twitter.domain.likes.Likes;
import com.totvs.financeiro.twitter.domain.user.User;

@Entity
@Table(name = "post")
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, unique = true, nullable = false)
	private UUID id;
	
	private String message;
	
	@ManyToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
	private User user;
	
	
	@Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime date;
	
	private String country;
	
	private Long comments;
	private Long likes;
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Long getComments() {
		return comments;
	}
	public void setComments(Long comments) {
		this.comments = comments;
	}
	public Long getLikes() {
		return likes;
	}
	public void setLikes(Long likes) {
		this.likes = likes;
	}
	
	

}
