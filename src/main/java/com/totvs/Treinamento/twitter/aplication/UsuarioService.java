package com.totvs.Treinamento.twitter.aplication;


import com.totvs.Treinamento.twitter.domain.usuario.Usuario;

public interface UsuarioService {

	Usuario inserir(Usuario usuario);
	Usuario Obter(Long id);
	Usuario alterar(Usuario usuario);
	void deletar(Long id); 

}
