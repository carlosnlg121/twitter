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

import com.totvs.financeiro.twitter.aplication.LikesService;
import com.totvs.financeiro.twitter.domain.likes.Likes;

@RestController
@RequestMapping("likes")
public class LikesResource {
	
	@Autowired
	private LikesService service;

    @GetMapping
    public Iterable<Likes> list() {
        return service.listar();
    }

    @GetMapping("/twitters/{id}")
    public Iterable<Likes> listByTwitter(@PathVariable Long id) {
        return service.findByTwitter(id);
    }

    @PostMapping
    public Likes save(@RequestBody Likes likes) {
        return service.inserir(likes);
    }

    @DeleteMapping("/{id}")
    public Map<String, String> deleteByOwner(@PathVariable Long id, @RequestHeader("user_id") Long userId) {
        service.deleteByOwner(id, userId);
        return Collections.singletonMap("msg", "Comentário deletado com sucesso");
    }
}
