package Dia27.EjerciciosManana27.Product;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import Dia27.EjerciciosManana27.models.Product;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductTest {
	
	
	@Autowired
	private WebApplicationContext applicationContext;
	
	private MockMvc mock;

	@Test
	void SAVE_PRODUCT() throws Exception {
		mock = MockMvcBuilders.webAppContextSetup(applicationContext).build();
		JSONObject rawProduct = new JSONObject();
		rawProduct.put("code", 13L);
		rawProduct.put("name", "product3");
		rawProduct.put("stock", 25);
		String JsonResponse = mock.perform(post("/products/save")
				.content(rawProduct.toString())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(
					status()
					.isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();
		Product product=new ObjectMapper().readValue(JsonResponse, Product.class);
		assertNotNull(product);
	}
	
	@Test
	public void SELECT_PRODUCTS() throws Exception {
		mock = MockMvcBuilders.webAppContextSetup(applicationContext).build();
		String JsonResponse = mock.perform(get("/products")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(
					status()
					.isOk())
			.andReturn()
			.getResponse()
			.getContentAsString();
		Product[] products = new ObjectMapper().readValue(JsonResponse, Product[].class);
		assertNotNull(products);
		System.out.println(JsonPath.parse(JsonResponse));
	}
}
