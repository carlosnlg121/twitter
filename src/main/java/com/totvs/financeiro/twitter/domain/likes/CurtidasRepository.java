package com.totvs.financeiro.twitter.domain.likes;

import org.springframework.data.repository.CrudRepository;


public interface CurtidasRepository extends CrudRepository<Curtidas, Long>{
	Iterable<Curtidas> findByPostId(Long id);
	Curtidas findByUserIdAndPostId(Long idUsuario, Long idTwitter);
}
