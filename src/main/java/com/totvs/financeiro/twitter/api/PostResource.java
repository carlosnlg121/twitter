package com.totvs.financeiro.twitter.api;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.totvs.financeiro.twitter.aplication.PostService;
import com.totvs.financeiro.twitter.aplication.UserService;
import com.totvs.financeiro.twitter.domain.post.Post;
import com.totvs.financeiro.twitter.domain.user.User;

@RestController
@RequestMapping(value={"tweets", "twitters", "posts"})
public class PostResource {
	
	@Autowired
	private PostService service;
	
	@GetMapping
	public Iterable<Post> listar(){
		return service.listar();
		
	}
	
	@GetMapping("/{id}")
	public Post obter(@NonNull Long id){
		return service.obter(id);
		
	}
	
	@GetMapping("/users/{id}")
    public Iterable<Post> search(@PathVariable Long id) {
        return service.findByUser(id);
    }
	
	@PostMapping
    public Post inserir(@RequestBody Post param) {
        return service.inserir(param);
    }

    @PutMapping
    public Post alterar(@RequestBody Post param) {
        return service.alterar(param);
    }
    
    @DeleteMapping("/{id}")
    public Map<String, String> deleteMy(@PathVariable Long id, @RequestHeader("user_id") Long userId) {
        service.deleteByOwner(id, userId);
        return Collections.singletonMap("msg", "Twitter deletado com sucesso");
    }

}
