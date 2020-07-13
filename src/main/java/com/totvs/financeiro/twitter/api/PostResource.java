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
	
	@Autowired
	private UserService userservice;
	
	@GetMapping
	public Iterable<Post> list(){
		return service.list();
		
	}
	
	@GetMapping("/{id}")
	public Post obter(@NonNull Long id){
		return service.get(id);
		
	}
	
	@GetMapping("/users/{id}")
    public Iterable<Post> search(@PathVariable Long id) {
        return service.findByUser(id);
    }
	
	@PostMapping
    public Post inserir(@RequestBody Post param) {
        return service.save(param);
    }

    @DeleteMapping("/{id}")
    public Map<String, String> deleteMy(@PathVariable Long id, @RequestHeader("user_id") Long userId) {
        service.deleteByOwner(id, userId);
        return Collections.singletonMap("msg", "Twitter deletado com sucesso");
    }

}
