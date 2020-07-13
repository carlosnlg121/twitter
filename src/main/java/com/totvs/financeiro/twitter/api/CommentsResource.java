package com.totvs.financeiro.twitter.api;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.totvs.financeiro.twitter.aplication.CommentsService;
import com.totvs.financeiro.twitter.domain.comments.Comments;

@RestController
@RequestMapping("comments")
public class CommentsResource {
	
	 @Autowired
	    private CommentsService service;

	    @GetMapping
	    public Iterable<Comments> list() {
	        return service.listar();
	    }

	    @GetMapping("/twitters/{id}")
	    public Iterable<Comments> listByTwitter(@PathVariable Long id) {
	        return service.findByTwitter(id);
	    }

	    @PostMapping
	    public Comments save(@RequestBody Comments comment) {
	        return service.inserir(comment);
	    }

	    @DeleteMapping("/{id}")
	    public Map<String, String> deleteByOwner(@PathVariable Long id, @RequestHeader("user_id") Long userId) {
	        service.deleteByOwner(id, userId);
	        return Collections.singletonMap("msg", "Comentário deletado com sucesso");
	    }
}
