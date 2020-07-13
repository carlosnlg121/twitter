package com.totvs.financeiro.twitter.aplication.impl;

import java.util.Optional;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.totvs.financeiro.twitter.aplication.LikesService;
import com.totvs.financeiro.twitter.aplication.UserService;
import com.totvs.financeiro.twitter.domain.comments.Comments;
import com.totvs.financeiro.twitter.domain.comments.CommentsRepository;
import com.totvs.financeiro.twitter.domain.likes.Likes;
import com.totvs.financeiro.twitter.domain.likes.LikesRepository;
import com.totvs.financeiro.twitter.domain.user.User;
import com.totvs.financeiro.twitter.infra.exception.BusinessException;
import com.totvs.financeiro.twitter.infra.exception.NotFoundException;

@Service
public class LikesServiceImpl implements LikesService {
	
	private LikesRepository repository;
	private UserService userService;
	
	@Override
	public Likes obter(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Usuario não encontrado com ID %s", id)));
	}
	
	@Override
	public Iterable<Likes> listar() {
        return repository.findAll();
                
	}
	
	@Override
    public Iterable<Likes> findByTwitter(@NonNull Long id) {
        return repository.findByPostId(id);
    }
	
	@Override
    public Likes inserir(@NonNull Likes likes) {
       // validate(comments);
        return Optional.ofNullable(repository.save(likes)).get();
    }
	
    @Override
    public void deleteByOwner(@NonNull Long id, @NonNull Long user) {
        final Likes comment = repository.findById(id)
                .orElseThrow(() -> new BusinessException("Comentário não encontrado"));
        final User owner = userService.obter(user);

        if (owner.getId().equals(comment.getUser().getId())) {
            repository.delete(comment);
        } else {
            throw new BusinessException("Somente o proprietário pode deletar o comentário");
        }
    }

}
