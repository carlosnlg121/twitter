package com.totvs.financeiro.twitter.aplication;

import com.totvs.financeiro.twitter.domain.post.Post;
import com.totvs.financeiro.twitter.domain.user.User;

public interface PostService {

	Post obter(Long id);

	Iterable<Post> listar();

	Post alterar(Post post);

	Post inserir(Post post);

	void deleteByOwner(Long id, Long user);

	Iterable<Post> findByUser(Long id);

}
