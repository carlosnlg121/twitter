package com.totvs.Treinamento.twitter.aplication.impl;



import java.util.Optional;

import org.apache.commons.lang3.Validate;
import org.springframework.lang.NonNull;

import com.totvs.Treinamento.twitter.aplication.UsuarioService;
import com.totvs.Treinamento.twitter.domain.usuario.Usuario;
import com.totvs.Treinamento.twitter.domain.usuario.UsuarioRepository;
import com.totvs.Treinamento.twitter.infra.Logger;
import com.totvs.Treinamento.twitter.infra.exception.NotFoundException;

public class UsuarioServiceImpl  implements Logger, UsuarioService{
	
	
	private final UsuarioRepository repository;

	public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
		this.repository = usuarioRepository;
	}
	
	public Usuario Obter(@NonNull Long id) {
		log("Obter" + id);
		
		return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Usuário não encontrado com ID %s", id)));		
	}
	
	 @Override
	    public Usuario inserir(@NonNull Usuario usuario) {
	        log("obter: " + usuario);
	        validate(usuario);
	        Usuario user = new Usuario();
	        
	        if (Optional.ofNullable(usuario).isPresent()){
	        	user = repository.save(usuario);
	        }
	        return user;
	    }
	 
	 @Override
	    public Usuario alterar(@NonNull Usuario usuario) {
	        validate(usuario);
	        Validate.notNull(usuario.getId(), "Dados do usuario incompleto");
	        return repository.save(usuario);
	    }

	    @Override
	    public void deletar(@NonNull Long id) {
	        repository.deleteById(id);
	    }
	 
	 private static void validate(Usuario obj) {
	        Validate.notNull(obj, "Dados do usuario informado");
	        Validate.notNull(obj.getName(), "Nome do usuario não informado");
	        Validate.notNull(obj.getAvatar(), "Avatar não foi informado");
	        Validate.notNull(obj.getLogin(), "Login não foi informado");
	        Validate.notNull(obj.getLocation(), "Localização não foi informado");
	    }
	
	
	
	
	
	

}
