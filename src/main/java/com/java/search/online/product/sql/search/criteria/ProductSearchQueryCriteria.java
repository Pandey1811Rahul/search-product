package com.java.search.online.product.sql.search.criteria;

import java.util.function.Consumer;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ProductSearchQueryCriteria implements Consumer<SearchCriteria> {

	 private Predicate predicate;
	    private CriteriaBuilder builder;
		private Root<?> r;
	    
	    
	public ProductSearchQueryCriteria(Predicate predicate, CriteriaBuilder builder, Root<?> r) {
			super();
			this.predicate = predicate;
			this.builder = builder;
			this.r = r;
		}


	public Predicate getPredicate() {
			return predicate;
		}


		public void setPredicate(Predicate predicate) {
			this.predicate = predicate;
		}


		public CriteriaBuilder getBuilder() {
			return builder;
		}


		public void setBuilder(CriteriaBuilder builder) {
			this.builder = builder;
		}


		public Root<?> getR() {
			return r;
		}


		public void setR(Root<?> r) {
			this.r = r;
		}


	@Override
	public void accept(SearchCriteria serCriteria) {
		// TODO Auto-generated method stub
		if(serCriteria.getOperation().equalsIgnoreCase(">")) {
			predicate = builder.and(predicate, builder.greaterThanOrEqualTo(r.get(serCriteria.getKey()), serCriteria.getValue().toString()));
		} else if(serCriteria.getOperation().equalsIgnoreCase("<")) {
			predicate = builder.and(predicate, builder.lessThanOrEqualTo(r.get(serCriteria.getKey()), serCriteria.getValue().toString()));
		} else if(serCriteria.getOperation().equalsIgnoreCase("=")) {
			predicate = builder.and(predicate, builder.equal(r.get(serCriteria.getKey()), serCriteria.getValue().toString()));
		}
	}

}
