package com.totvs.financeiro.twitter.domain.likes;

import org.springframework.data.repository.CrudRepository;

import com.totvs.financeiro.twitter.domain.comments.Comments;

public interface CurtidasRepository extends CrudRepository<Curtidas, Long>{
	Iterable<Curtidas> findByPostId(Long id);
}
