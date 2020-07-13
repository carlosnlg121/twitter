package com.totvs.financeiro.twitter.domain.likes;

import org.springframework.data.repository.CrudRepository;

import com.totvs.financeiro.twitter.domain.comments.Comments;

public interface LikesRepository extends CrudRepository<Likes, Long>{
	Iterable<Likes> findByPostId(Long id);
}
