package com.totvs.financeiro.twitter.aplication.impl;

import java.util.Optional;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.totvs.financeiro.twitter.aplication.LikesService;
import com.totvs.financeiro.twitter.domain.likes.Likes;
import com.totvs.financeiro.twitter.domain.likes.LikesRepository;
import com.totvs.financeiro.twitter.domain.user.User;
import com.totvs.financeiro.twitter.infra.exception.NotFoundException;

@Service
public class LikesServiceImpl implements LikesService {
	
	private LikesRepository repository;
	
	@Autowired
	public LikesServiceImpl(LikesRepository repository) {
		super();
		this.repository = repository;
	}
	
	@Override
	public Likes obter(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Usuario não encontrado com ID %s", id)));
	}

	@Override
	public Iterable<Likes> listar() {
        return repository.findAll();
                
	}
	
	@Override
    public Likes inserir(@NonNull Likes likes) {
       // validate(likes);
        return Optional.ofNullable(repository.save(likes)).get();
    }
	
	@Override
    public Likes alterar(@NonNull Likes likes) {
        //validate(user);
        Validate.notNull(likes.getId(), "Dados da Live incompleto");
        return repository.save(likes);
    }
    
    @Override
    public void deletar(@NonNull Long id) {
        repository.deleteById(id);
    }

}
