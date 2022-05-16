package Dia27.EjerciciosManana27.Client;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import Dia27.EjerciciosManana27.models.Client;
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



@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientTest {
	
	@Autowired 
	private WebApplicationContext applicationContext;
	private MockMvc mock;
	
	@Test
	public void DELETE_CLIENT() throws Exception {
		mock = MockMvcBuilders.webAppContextSetup(applicationContext).build();
		String JsonResponse = mock.perform(
					delete("/clients/delete/123")
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.TEXT_PLAIN))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();
		assertTrue(JsonResponse.equals("Success."));
	}
	
	@Test
	public void SELECT_CLIENTS() throws Exception {
		mock = MockMvcBuilders.webAppContextSetup(applicationContext).build();
		String JsonResponse = mock.perform(
					get("/clients")
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andReturn()
					.getResponse()
					.getContentAsString();
		Client[] clients = new ObjectMapper().readValue(JsonResponse, Client[].class);
		assertNotNull(clients);
	}
}