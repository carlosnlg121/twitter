package com.totvs.financeiro.twitter.aplication.impl;

import java.util.Optional;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.totvs.financeiro.twitter.aplication.CommentsService;
import com.totvs.financeiro.twitter.aplication.UserService;
import com.totvs.financeiro.twitter.domain.comments.Comments;
import com.totvs.financeiro.twitter.domain.comments.CommentsRepository;
import com.totvs.financeiro.twitter.domain.user.User;
import com.totvs.financeiro.twitter.infra.exception.BusinessException;
import com.totvs.financeiro.twitter.infra.exception.NotFoundException;

@Service
public class CommentsServiceImpl implements CommentsService{
	
	private CommentsRepository repository;
	private final UserService userService;

	@Autowired
	public CommentsServiceImpl(CommentsRepository repository, UserService userService) {
		this.repository = repository;
		this.userService = userService;
	}
	
	@Override
	public Comments obter(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Usuario não encontrado com ID %s", id)));
	}
	
	@Override
	public Iterable<Comments> listar() {
        return repository.findAll();
                
	}
	
	@Override
    public Iterable<Comments> findByTwitter(@NonNull Long id) {
        return repository.findByPostId(id);
    }
	
	@Override
    public Comments inserir(@NonNull Comments comments) {
       // validate(comments);
        return Optional.ofNullable(repository.save(comments)).get();
    }
	
    @Override
    public void deleteByOwner(@NonNull Long id, @NonNull Long user) {
        final Comments comment = repository.findById(id)
                .orElseThrow(() -> new BusinessException("Comentário não encontrado"));
        final User owner = userService.obter(user);

        if (owner.getId().equals(comment.getUser().getId())) {
            repository.delete(comment);
        } else {
            throw new BusinessException("Somente o proprietário pode deletar o comentário");
        }
    }

}
