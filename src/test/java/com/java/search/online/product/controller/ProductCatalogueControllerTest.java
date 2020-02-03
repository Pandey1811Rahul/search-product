package com.java.search.online.product.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.search.online.product.service.ProductService;
import com.java.search.online.product.sql.domain.Product;

public class ProductCatalogueControllerTest {

	MockMvc mockMvc;

	@Mock
	ProductService service;
	@InjectMocks
	private ProductCatalogueController controller;
	Product products;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		products = new Product();
		products.setpId(1);
		products.setBrandName("mufti");
		products.setColour("blue");
		products.setPrice("899");
		products.setSize("M");
		products.setStockAvl(9);
		products.setProductCategory("shirt");
	}

	@Test
	public void getSearchProduct_shouldReturnSuccessufully_onGivenInputs() throws Exception {
		mockMvc.perform(get("/search-product").param("brand", "mufti").param("size", "M")).andExpect(status().isOk());
	}

	@Test
	public void getSearchProduct_shouldReturnSuccessufully_() throws Exception {
		List<Product> list = new ArrayList<Product>();
		list.add(products);
		when(service.getproductBySearch(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(),
				Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt()))
		.thenReturn(list);
		MvcResult mvcResult = mockMvc.perform(get("/search-product")
				.param("brand", "mufti")
				.param("size", "M")
				.param("category", "shirt")
				.param("colour", "blue")
				.param("priceLower", "100")
				.param("priceUpper", "100"))
				.andExpect(status().isOk()).andReturn();
		ObjectMapper mapper = new ObjectMapper();
		List<Product> acutal = mapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<Product>>() {});
		assertEquals(list.get(0).getpId(), acutal.get(0).getpId());
	}
}
