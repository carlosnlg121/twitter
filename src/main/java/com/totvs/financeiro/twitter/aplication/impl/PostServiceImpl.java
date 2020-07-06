package com.totvs.financeiro.twitter.aplication.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.totvs.financeiro.twitter.aplication.PostService;
import com.totvs.financeiro.twitter.domain.post.PostRepository;

@Service
public class PostServiceImpl implements PostService {
	
	private PostRepository repository;

	@Autowired
	public PostServiceImpl(PostRepository repository) {
		this.repository = repository;
	}
	
	

}
