package com.github.bilak.persistence;

import com.github.bilak.persistence.jpa.model.SimpleEntity;
import com.github.bilak.persistence.jpa.model.SimpleEnum;
import com.github.bilak.persistence.jpa.repository.SimpleEntityRepository;

import javax.annotation.PostConstruct;

/**
 * Created by lvasek on 18/01/2017.
 */
public class Initializer {

	private SimpleEntityRepository repository;

	public Initializer(SimpleEntityRepository repository) {
		this.repository = repository;
	}

	@PostConstruct
	void init() {
		if (repository.count() < 100) {
			for (int i = 0; i < 10; i++) {
				repository.save(new SimpleEntity(i % 2 == 0 ? SimpleEnum.ENUM1 : SimpleEnum.ENUM2));
			}
		}
	}
}
