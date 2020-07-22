package com.totvs.financeiro.twitter.aplication.impl;

import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.totvs.financeiro.twitter.aplication.CurtidasService;
import com.totvs.financeiro.twitter.aplication.UserService;
import com.totvs.financeiro.twitter.domain.comments.Comments;
import com.totvs.financeiro.twitter.domain.comments.CommentsRepository;
import com.totvs.financeiro.twitter.domain.likes.Curtidas;
import com.totvs.financeiro.twitter.domain.likes.CurtidasRepository;
import com.totvs.financeiro.twitter.domain.user.User;
import com.totvs.financeiro.twitter.infra.Logger;
import com.totvs.financeiro.twitter.infra.exception.BusinessException;
import com.totvs.financeiro.twitter.infra.exception.NotFoundException;

@Service
public class CurtidasServiceImpl implements Logger, CurtidasService {

	private CurtidasRepository repository;
	private UserService userService;

	@Autowired
	public CurtidasServiceImpl(CurtidasRepository repository, UserService userService) {
		super();
		this.repository = repository;
		this.userService = userService;
	}

	@Override
	public Curtidas obter(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Usuario não encontrado com ID %s", id)));
	}

	@Override
	public Iterable<Curtidas> listar() {
		log("buscando reg");
		return repository.findAll();

	}

	@Override
	public Iterable<Curtidas> findByTwitter(@NonNull Long id) {
		return repository.findByPostId(id);
	}

	@Override
	public String inserir(@NonNull Curtidas likes) {
		String like = "Like";
		Curtidas likeCadastrado = new  Curtidas(); 
	    likeCadastrado = repository.findByUserIdAndPostId(likes.getUser().getId(),
				likes.getPost().getId());

		if (Objects.isNull(likeCadastrado)) {
			log("Salvar Likes");
			repository.save(likes);
		} else {
			log("Remolver Likes");
			repository.deleteById(likeCadastrado.getId());
			like = "Dislike";

		}
		return like;
	}

	@Override
	public void deleteByOwner(@NonNull Long id, @NonNull Long user) {
		final Curtidas comment = repository.findById(id)
				.orElseThrow(() -> new BusinessException("Comentário não encontrado"));
		final User owner = userService.obter(user);

		if (owner.getId().equals(comment.getUser().getId())) {
			repository.delete(comment);
		} else {
			throw new BusinessException("Somente o proprietário pode deletar o comentário");
		}
	}

}
