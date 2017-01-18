package com.github.bilak.persistence.repository.rsql.rsql;

import cz.jirutka.rsql.parser.ast.AndNode;
import cz.jirutka.rsql.parser.ast.ComparisonNode;
import cz.jirutka.rsql.parser.ast.OrNode;
import cz.jirutka.rsql.parser.ast.RSQLVisitor;
import org.springframework.data.jpa.domain.Specification;

/**
 * Created by lvasek on 09/11/2016.
 */
public class CustomRestSqlVisitor<T> implements RSQLVisitor<Specification<T>, Void> {

	private GenericRestSqlSpecificationBuilder<T> builder;

	public CustomRestSqlVisitor() {
		builder = new GenericRestSqlSpecificationBuilder<T>();
	}

	@Override
	public Specification<T> visit(AndNode node, Void param) {
		return builder.createSpecification(node);
	}

	@Override
	public Specification<T> visit(OrNode node, Void param) {
		return builder.createSpecification(node);
	}

	@Override
	public Specification<T> visit(ComparisonNode node, Void params) {
		return builder.createSpecification(node);
	}
}