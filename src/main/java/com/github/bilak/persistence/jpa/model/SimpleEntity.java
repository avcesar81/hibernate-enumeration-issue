package com.github.bilak.persistence.jpa.model;

import javax.persistence.*;

/**
 * Created by lvasek on 18/01/2017.
 */
@Entity
@Table(name = "simple_entity")
public class SimpleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;

	@Column
	@Enumerated(EnumType.STRING)
	private SimpleEnum simpleEnum;

	public SimpleEntity() {
	}

	public SimpleEntity(SimpleEnum simpleEnum) {
		this.simpleEnum = simpleEnum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SimpleEnum getSimpleEnum() {
		return simpleEnum;
	}

	public void setSimpleEnum(SimpleEnum simpleEnum) {
		this.simpleEnum = simpleEnum;
	}
}
