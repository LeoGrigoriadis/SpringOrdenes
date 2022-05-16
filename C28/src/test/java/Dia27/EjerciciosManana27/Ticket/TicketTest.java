package Dia27.EjerciciosManana27.Ticket;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import Dia27.EjerciciosManana27.models.Product;
import Dia27.EjerciciosManana27.models.Ticket;
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

@RunWith(SpringRunner.class)
@SpringBootTest
public class TicketTest {
	
	@Autowired
	private WebApplicationContext applicationContext;
	
	private MockMvc mock;
	
	@Test
	public void SELECT_TICKETS() throws Exception {
		mock = MockMvcBuilders.webAppContextSetup(applicationContext).build();
		String JsonResponse = mock.perform(
								get("/tickets")
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON))
							.andExpect(
									status()
									  .isOk())
							.andReturn()
							.getResponse()
							.getContentAsString();
		Ticket[] tickets = new ObjectMapper().readValue(JsonResponse, Ticket[].class);
		assertNotNull(tickets);
	}
	
	@Test
	public void INSERT_TICKET() throws Exception{
		mock = MockMvcBuilders.webAppContextSetup(applicationContext).build();
		JSONObject rawTicket = new JSONObject();
		rawTicket.put("n_ticket", 13L);
		rawTicket.put("ammount_products", 12);
		rawTicket.put("prod", 1);
		rawTicket.put("client", "123");
		String JsonResponse = mock.perform(
							post("/tickets/save")
							 .content(rawTicket.toString())
							 .contentType(MediaType.APPLICATION_JSON)
							 .accept(MediaType.APPLICATION_JSON))
						.andExpect(
								status()
								  .isOk())
						.andReturn()
						.getResponse()
						.getContentAsString();
		Ticket ticket=new ObjectMapper().readValue(JsonResponse, Ticket.class);
		assertNotNull(ticket);
	}
}