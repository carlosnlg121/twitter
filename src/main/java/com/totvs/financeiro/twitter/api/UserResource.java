package com.totvs.financeiro.twitter.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.totvs.financeiro.twitter.aplication.UserService;
import com.totvs.financeiro.twitter.domain.user.User;

@RestController
@RequestMapping("user")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@GetMapping
	public Iterable<User> listar(){
		return service.listar();
		
	}
	
	@GetMapping("/{id}")
	public User obter(@NonNull Long id){
		return service.obter(id);
		
	}	
	
	@PostMapping
    public User inserir(@RequestBody User param) {
        return service.inserir(param);
    }

    @PutMapping
    public User alterar(@RequestBody User param) {
        return service.alterar(param);
    }

}
