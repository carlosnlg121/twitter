package com.totvs.financeiro.twitter.domain.comments;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;

import com.totvs.financeiro.twitter.domain.user.User;

@Entity
@Table(name = "comments")
public class Comments {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, unique = true, nullable = false)
	private UUID id; 
	
	private String text;
	
	@UpdateTimestamp
	private LocalDateTime time;
	
	@OneToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
	private User user;

}
