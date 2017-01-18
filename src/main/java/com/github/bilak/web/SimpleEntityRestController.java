package com.github.bilak.web;

import com.github.bilak.persistence.jpa.model.SimpleEntity;
import com.github.bilak.persistence.jpa.repository.SimpleEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * Created by lvasek on 18/01/2017.
 */
@RestController
public class SimpleEntityRestController implements QueryController<SimpleEntity> {

	private SimpleEntityRepository repository;

	@Autowired
	public SimpleEntityRestController(SimpleEntityRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/rest-sql")
	public ResponseEntity<List<SimpleEntity>> rsql(@RequestParam("q") String rsqlQuery) {
		return Optional.ofNullable(repository.findAll(restSqlQuerySpecifications(rsqlQuery)))
				.map(result -> result.size() > 0 ?
						new ResponseEntity<List<SimpleEntity>>(result, HttpStatus.OK) :
						new ResponseEntity<List<SimpleEntity>>(HttpStatus.NOT_FOUND))
				.orElse(new ResponseEntity<List<SimpleEntity>>(HttpStatus.NOT_FOUND));
	}
}
