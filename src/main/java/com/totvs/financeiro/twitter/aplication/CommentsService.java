package com.totvs.financeiro.twitter.aplication;

import com.totvs.financeiro.twitter.domain.comments.Comments;

public interface CommentsService {

	Comments obter(Long id);

	Iterable<Comments> listar();

	Comments inserir(Comments comments);

	void deleteByOwner(Long id, Long user);

	Iterable<Comments> findByTwitter(Long id);

}
