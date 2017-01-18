package com.github.bilak.web;

import com.github.bilak.persistence.repository.rsql.rsql.CustomRestSqlVisitor;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import org.springframework.data.jpa.domain.Specification;

/**
 * Created by lvasek on 18/01/2017.
 */
public interface QueryController<DbEntity> {

	default Specification<DbEntity> restSqlQuerySpecifications(String query) {
		if (query == null)
			return null;
		Node rootNode = new RSQLParser().parse(query);
		Specification<DbEntity> spec = rootNode.accept(new CustomRestSqlVisitor<DbEntity>());
		return spec;
	}
}
