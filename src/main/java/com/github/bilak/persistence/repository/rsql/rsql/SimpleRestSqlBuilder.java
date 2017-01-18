package com.github.bilak.persistence.repository.rsql.rsql;

import cz.jirutka.rsql.parser.ast.LogicalOperator;
import cz.jirutka.rsql.parser.ast.RSQLOperators;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by lvasek on 06/12/2016.
 */
public class SimpleRestSqlBuilder {

	private static final Logger logger = LoggerFactory.getLogger(SimpleRestSqlBuilder.class);

	private StringBuilder sb = new StringBuilder();

	public SimpleRestSqlBuilder and() {
		this.sb.append(LogicalOperator.AND);
		return this;
	}

	public SimpleRestSqlBuilder or() {
		this.sb.append(LogicalOperator.OR);
		return this;
	}

	public SimpleRestSqlBuilder equal(String attribute, String value) {
		this.sb.append(attribute)
				.append(RSQLOperators.EQUAL.getSymbol())
				.append(value == null ? null : encodeValues(value).get(0));
		return this;
	}

	public SimpleRestSqlBuilder notEqual(String attribute, String value) {
		this.sb.append(attribute)
				.append(RSQLOperators.NOT_EQUAL.getSymbol())
				.append(value == null ? null : encodeValues(value).get(0));
		return this;
	}

	public SimpleRestSqlBuilder greaterThan(String attribute, String value) {
		this.sb.append(attribute)
				.append(RSQLOperators.GREATER_THAN.getSymbol())
				.append(value == null ? null : encodeValues(value).get(0));
		return this;
	}

	public SimpleRestSqlBuilder greaterThanOrEqual(String attribute, String value) {
		this.sb.append(attribute)
				.append(RSQLOperators.GREATER_THAN_OR_EQUAL.getSymbol())
				.append(value == null ? null : encodeValues(value).get(0));
		return this;
	}

	public SimpleRestSqlBuilder lessThan(String attribute, String value) {
		this.sb.append(attribute)
				.append(RSQLOperators.LESS_THAN.getSymbol())
				.append(value == null ? null : encodeValues(value).get(0));
		return this;
	}

	public SimpleRestSqlBuilder lessThanOrEqual(String attribute, String value) {
		this.sb.append(attribute)
				.append(RSQLOperators.LESS_THAN_OR_EQUAL.getSymbol())
				.append(value == null ? null : encodeValues(value).get(0));
		return this;
	}

	public SimpleRestSqlBuilder in(String attribute, String... args) {
		this.sb.append(attribute)
				.append(RSQLOperators.IN.getSymbol())
				.append("(")
				.append(StringUtils.join(encodeValues(args), ","))
				.append(")");
		return this;
	}

	public SimpleRestSqlBuilder notIn(String attribute, String... args) {
		this.sb.append(attribute)
				.append(RSQLOperators.NOT_IN.getSymbol())
				.append("(")
				.append(StringUtils.join(encodeValues(args), ","))
				.append(")");
		return this;
	}

	public String buildQuery() {
		return this.sb.toString();
	}

	private List<String> encodeValues(String... values) {
		return Arrays.asList(values)
				.stream()
				.map(s -> s.contains("'") ? s.replace("'", "\\'") : s)
				.map(s -> s.contains("\"") ? s.replace("\"", "\\\"") : s)
				.map(s -> s.contains("\\") ? s.replace("\\", "\\\\") : s)
				.map(s -> {
					try {
						return URLEncoder.encode(s, "UTF-8");
					} catch (UnsupportedEncodingException e) {
						logger.error("Unable to encode {}", s);
					}
					return s;
				})
				.map(s -> "'".concat(s).concat("'"))
				.collect(Collectors.toList());
	}
}
