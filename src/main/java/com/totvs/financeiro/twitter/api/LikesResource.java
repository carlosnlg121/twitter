package com.totvs.financeiro.twitter.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.totvs.financeiro.twitter.aplication.LikesService;
import com.totvs.financeiro.twitter.aplication.PostService;
import com.totvs.financeiro.twitter.domain.likes.Likes;
import com.totvs.financeiro.twitter.domain.post.Post;

@RestController
@RequestMapping("like")
public class LikesResource {
	
	@Autowired
	private LikesService service;
	
	@GetMapping
	public Iterable<Likes> listar(){
		return service.listar();
		
	}
	
	@GetMapping("/{id}")
	public Likes obter(@NonNull Long id){
		return service.obter(id);
		
	}	
	
	@PostMapping
    public Likes inserir(@RequestBody Likes param) {
        return service.inserir(param);
    }

    @PutMapping
    public Likes alterar(@RequestBody Likes param) {
        return service.alterar(param);
    }
}
