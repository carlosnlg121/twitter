package com.totvs.financeiro.twitter.aplication;

import com.totvs.financeiro.twitter.domain.likes.Curtidas;

public interface CurtidasService {

	Curtidas obter(Long id);

	Iterable<Curtidas> listar();

	Iterable<Curtidas> findByTwitter(Long id);

	Curtidas inserir(Curtidas comments);

	void deleteByOwner(Long id, Long user);

}
