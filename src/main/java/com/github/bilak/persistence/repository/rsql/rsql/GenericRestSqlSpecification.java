package com.github.bilak.persistence.repository.rsql.rsql;

import cz.jirutka.rsql.parser.ast.ComparisonOperator;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvasek on 09/11/2016.
 */
public class GenericRestSqlSpecification<T> implements Specification<T> {
	private String property;
	private ComparisonOperator operator;
	private List<String> arguments;

	public GenericRestSqlSpecification(String property, ComparisonOperator operator, List<String> arguments) {
		super();
		this.property = property;
		this.operator = operator;
		this.arguments = arguments;
	}

	@Override
	public Predicate toPredicate(
			Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

		List<Object> args = castArguments(root);
		Object argument = args.get(0);
		switch (RestSqlSearchOperation.getSimpleOperator(operator)) {

			case EQUAL: {
				if (argument instanceof String && ((String) argument).contains("*")) {
					return builder.like(root.<String>get(property), argument.toString().replace('*', '%'));
				} else if (argument == null) {
					return builder.isNull(root.get(property));
				} else {
					return builder.equal(root.get(property), argument);
				}
			}
			case NOT_EQUAL: {
				if (argument instanceof String && ((String) argument).contains("*")) {
					return builder.notLike(root.<String>get(property), argument.toString().replace('*', '%'));
				} else if (argument == null) {
					return builder.isNotNull(root.get(property));
				} else {
					return builder.notEqual(root.get(property), argument);
				}
			}
			case GREATER_THAN: {
				return builder.greaterThan(root.<String>get(property), argument.toString());
			}
			case GREATER_THAN_OR_EQUAL: {
				return builder.greaterThanOrEqualTo(root.<String>get(property), argument.toString());
			}
			case LESS_THAN: {
				return builder.lessThan(root.<String>get(property), argument.toString());
			}
			case LESS_THAN_OR_EQUAL: {
				return builder.lessThanOrEqualTo(root.<String>get(property), argument.toString());
			}
			case IN:
				return root.get(property).in(args);
			case NOT_IN:
				return builder.not(root.get(property).in(args));
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	private List<Object> castArguments(Root<T> root) {
		List<Object> args = new ArrayList<Object>();
		Class type = root.get(property).getJavaType();

		for (String argument : arguments) {
			if (type.equals(Integer.class)) {
				args.add(Integer.parseInt(argument));
			} else if (type.equals(Long.class)) {
				args.add(Long.parseLong(argument));
			} else if (type.equals(Boolean.class)) {
				switch (argument) {
					case "0":
					case "F":
					case "f":
						argument = Boolean.FALSE.toString();
						break;
					case "1":
					case "T":
					case "t":
						argument = Boolean.TRUE.toString();
						break;

				}
				args.add(Boolean.valueOf(argument));
			} else if (type.isEnum()) {
				args.add(Enum.valueOf(type, argument));
			} else {
				args.add(argument);
			}
		}

		return args;
	}
}