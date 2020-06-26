package com.totvs.Treinamento.twitter.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.totvs.Treinamento.twitter.aplication.UsuarioService;
import com.totvs.Treinamento.twitter.domain.usuario.Usuario;

@RestController
@RequestMapping("usuario")
public class UsuarioResource {
	
	@Autowired
	public UsuarioService service;


}
