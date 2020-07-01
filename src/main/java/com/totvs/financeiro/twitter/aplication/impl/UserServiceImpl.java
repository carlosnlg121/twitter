package com.totvs.financeiro.twitter.aplication.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.totvs.financeiro.twitter.aplication.UserService;
import com.totvs.financeiro.twitter.domain.user.User;
import com.totvs.financeiro.twitter.domain.user.UserRepository;
import com.totvs.financeiro.twitter.infra.exception.NotFoundException;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

@Service
public class UserServiceImpl implements UserService {
	
	private UserRepository repository;

	@Autowired
	public UserServiceImpl(UserRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public User obter(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Usuario não encontrado com ID %s", id)));
	}

	@Override
	public Iterable<User> listar() {
        return repository.findAll();
                
	}
	
	@Override
    public User inserir(@NonNull User user) {
        validate(user);
        return Optional.ofNullable(repository.save(user)).get();
    }
	
	@Override
    public User alterar(@NonNull User user) {
        validate(user);
        Validate.notNull(user.getId(), "Dados da Live incompleto");
        return repository.save(user);
    }
    
    @Override
    public void deletar(@NonNull Long id) {
        repository.deleteById(id);
    }
	
	private static void validate(User obj) {
        Validate.notNull(obj, "Dados da usuario não informado");
        Validate.isTrue(StringUtils.isNotBlank(obj.getName()), "Nome do usuario não informado");
        Validate.notNull(obj.getLogin(), "Login do usuario não informado");
        Validate.notNull(obj.getAvatar(), "Avatar não informado");
    }
	

}
