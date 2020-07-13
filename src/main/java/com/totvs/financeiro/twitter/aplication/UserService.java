package com.totvs.financeiro.twitter.aplication;

import com.totvs.financeiro.twitter.domain.user.User;

public interface UserService {	
	User obter(Long id);
	Iterable<User> listar();
	User inserir(User user);
	void deletar(Long id);
	User alterar(User user);
	User find(Long id);

}
