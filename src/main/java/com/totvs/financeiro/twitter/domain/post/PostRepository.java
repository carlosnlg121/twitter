package com.totvs.financeiro.twitter.domain.post;

import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long>{
	Iterable<Post> findByUserId(Long id);

}
