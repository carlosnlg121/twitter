package com.totvs.financeiro.twitter.aplication.impl;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.totvs.financeiro.twitter.aplication.PostService;
import com.totvs.financeiro.twitter.aplication.UserService;
import com.totvs.financeiro.twitter.domain.post.Post;
import com.totvs.financeiro.twitter.domain.post.PostRepository;
import com.totvs.financeiro.twitter.domain.user.User;
import com.totvs.financeiro.twitter.infra.exception.BusinessException;
import com.totvs.financeiro.twitter.infra.exception.NotFoundException;

@Service
public class PostServiceImpl implements PostService {
	
	private final PostRepository repository;
	private final UserService userService;

	@Autowired
	public PostServiceImpl(PostRepository repository,UserService userService) {
		this.repository = repository;
		this.userService = userService;
	}
	
    @Override
    public Post get(@NonNull Long id) {
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public Post save(@NonNull Post param) {
        return repository.save(param);
    }

    @Override
    public Iterable<Post> list() {
        return repository.findAll();
    }

    @Override
    public Iterable<Post> findByUser(@NonNull Long id) {
        return repository.findByUserId(id);
    }

    @Override
    public void deleteByOwner(@NonNull Long id, @NonNull Long user) {
        final Post twitter = repository.findById(id)
                .orElseThrow(() -> new BusinessException("Twitter não encontrado"));
        final User owner = userService.find(user);

        if (owner.getId().equals(twitter.getUser().getId())) {
            repository.delete(twitter);
        } else {
            throw new BusinessException("Somente o proprietario pode deletar a postagem");
        }
    }
	
	

}
