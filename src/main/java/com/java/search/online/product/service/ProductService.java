package com.java.search.online.product.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.search.online.product.dao.ProductRepository;
import com.java.search.online.product.dto.ProductDto;
import com.java.search.online.product.sql.domain.Product;
import com.java.search.online.product.sql.search.criteria.ProductSearchQueryCriteria;
import com.java.search.online.product.sql.search.criteria.SearchCriteria;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * search product
	 * @param brand : brand name
	 * @param size : product size
	 * @param category 
	 * @param colour
	 * @param priceLower
	 * @param priceUpper
	 * @return
	 */
	public List<Product> getproductBySearch(String brand, String size, String category, String colour,
			Integer priceLower, Integer priceUpper) {
		List<SearchCriteria> params = new ArrayList<SearchCriteria>();
		if (StringUtils.isNotBlank(brand))
			params.add(new SearchCriteria("brandName", "=", brand));
		if (StringUtils.isNotBlank(category))
			params.add(new SearchCriteria("productCategory", "=", category));
		if (StringUtils.isNotBlank(size))
			params.add(new SearchCriteria("size", "=", size));
		if (null != priceUpper)
			params.add(new SearchCriteria("price", ">", priceUpper));
		if (null != priceLower)
			params.add(new SearchCriteria("price", "<", priceLower));
		if (StringUtils.isNotBlank(colour))
			params.add(new SearchCriteria("colour", "=", colour));

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Product> query = builder.createQuery(Product.class);
		Root<Product> root = query.from(Product.class);
		Predicate predicate = builder.conjunction();
		ProductSearchQueryCriteria searchProduct = new ProductSearchQueryCriteria(predicate, builder, root);
		params.stream().forEach(searchProduct);
		predicate = searchProduct.getPredicate();
		query.where(predicate);
		List<Product> result = entityManager.createQuery(query).getResultList();
		return result;
	}

	/**
	 * 
	 * @return
	 */
	public List<Product> getAllProduct() {
		List<Product> products = (List<Product>) productRepository.findAll();
		return products;
	}

	/**
	 * 
	 * @param productDto
	 */
	public void upsertProduct(ProductDto productDto) {
		Product entity = mapper(productDto);
		productRepository.save(entity);
	}

	/**
	 * 
	 * @param productDto
	 * @return
	 */
	private Product mapper(ProductDto productDto) {
		Product entity = new Product();
		entity.setpId(productDto.getpId() != null ? productDto.getpId() : null);
		entity.setBrandName(productDto.getBrandName());
		entity.setColour(productDto.getColour());
		entity.setPrice(productDto.getPrice());
		entity.setProductCategory(productDto.getProductCategory());
		entity.setSize(productDto.getSize());
		entity.setStockAvl(productDto.getStockAvl());
		return entity;
	}

}
