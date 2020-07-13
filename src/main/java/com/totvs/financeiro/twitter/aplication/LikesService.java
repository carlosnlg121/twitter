package com.totvs.financeiro.twitter.aplication;

import com.totvs.financeiro.twitter.domain.likes.Likes;

public interface LikesService {

	Likes obter(Long id);

	Iterable<Likes> listar();

	Iterable<Likes> findByTwitter(Long id);

	Likes inserir(Likes comments);

	void deleteByOwner(Long id, Long user);

}
