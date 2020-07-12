package com.totvs.financeiro.twitter.aplication;

import com.totvs.financeiro.twitter.domain.likes.Likes;

public interface LikesService {

	Likes obter(Long id);

	Iterable<Likes> listar();

	Likes inserir(Likes likes);

	Likes alterar(Likes likes);

	void deletar(Long id);

}
