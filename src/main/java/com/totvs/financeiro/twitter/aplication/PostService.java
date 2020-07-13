package com.totvs.financeiro.twitter.aplication;

import com.totvs.financeiro.twitter.domain.post.Post;
import com.totvs.financeiro.twitter.domain.user.User;

public interface PostService {

	void deleteByOwner(Long id, Long user);
	
	Iterable<Post> list();

	Post save(Post param);

	Post get(Long id);

	Iterable<Post> findByUser(Long id);

}
