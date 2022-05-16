package Dia27.EjerciciosManana27.Ticket;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import Dia27.EjerciciosManana27.models.Client;
import Dia27.EjerciciosManana27.models.Product;
import Dia27.EjerciciosManana27.models.Ticket;
import Dia27.EjerciciosManana27.repositories.ClientRepository;
import Dia27.EjerciciosManana27.repositories.TicketRepository;
import Dia27.EjerciciosManana27.services.TicketService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TicketServicesTest {

	@InjectMocks
	private TicketService service;
	@Mock
	private TicketRepository repositoryMock;
	
	private Ticket ticket = new Ticket(1L, 12, new Product(), new Client());
	
	private Client client = ticket.getClient();
	
	@Test
	public void GET_SALES() {
		when(repositoryMock.findAll()).thenReturn(new ArrayList<Ticket>());
		List<Ticket> sales = service.getAll();
		assertNotNull(sales);
		assertEquals(sales, new ArrayList<Ticket>());
	}
	
	@Test
	public void SAVE_SALE() {
		when(repositoryMock.save(ticket)).thenReturn(ticket);
		assertNotNull(service.save(ticket));
	}
	
	@Test
	public void SAVE_SALE_FAIL() {
		when(repositoryMock.existsById(ticket.getNTicket())).thenReturn(false);
		assertNull(service.save(ticket));
	}
	
	@Test
	public void GET_BY_CLIENT() {
		when(repositoryMock.getByDni(ticket.getClient().getDni())).thenReturn(new ArrayList<>());
		assertNotNull(service.getByClient(ticket.getClient().getDni()));
	}

	@Test
	public void GET_BY_CLIENT_FAIL() {
			when(repositoryMock.getByDni(ticket.getClient().getDni())).thenReturn(null);
			assertNull(service.getByClient(ticket.getClient().getDni()));
	}
	@Test
	public void GET_BY_ID() {
		when(repositoryMock.getById(ticket.getNTicket())).thenReturn(new Ticket());
		assertNotNull(service.getById(ticket.getNTicket()));
	}

	@Test
	public void GET_BY_ID_FAIL() {
		when(repositoryMock.getById(ticket.getNTicket())).thenReturn(null);
		assertNull(service.getById(ticket.getNTicket()));
	}
}
	
