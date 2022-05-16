package Dia27.EjerciciosManana27.Client;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Dia27.EjerciciosManana27.models.Client;
import Dia27.EjerciciosManana27.repositories.ClientRepository;
import Dia27.EjerciciosManana27.services.ClientService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientServiceTest {
	
	@InjectMocks
	private ClientService service;
	
	@Mock
	private ClientRepository repository;
	
	private Client client1 = new Client("1234","client1", "lastiname1");
	
	private Client client2 = new Client("1235", "client2", "lastiname2");
		
	@Test
	public void SELECT_CLIENTS() {
		when(repository.findAll()).thenReturn(new ArrayList<Client>());
		List<Client> clients = service.getAll();
		assertNotNull(clients);
	}
	
	@Test
	public void SAVE_CLIENT() {
		when(repository.save(client1)).thenReturn(client1);
		assertNotNull(service.save(client1));
	}
	
	@Test
	public void SAVE_CLIENT_FAIL() {
		when(repository.save(client1)).thenReturn(null);
		assertNull(null);
	}
	
	@Test
	public void UPDATE_CLIENT() {
		when(repository.getById(client2.getDni())).thenReturn(client1);
		when(repository.save(client2)).thenReturn(client2);
		assertNotNull(service.update(client2, client2.getDni()));
	}
	
	@Test
	public void UPDATE_CLIENT_FAIL() {
		when(repository.getById(client2.getDni())).thenReturn(null);
		assertNull(service.update(client2, client2.getDni()));
	}
	
	@Test
	public void DELETE_CLIENT() {
		when(repository.getById(client2.getDni())).thenReturn(client2);
		assertTrue(service.delete(client2.getDni()));
	}
	
	@Test
	public void DELETE_CLIENT_FAIL() {
		when(repository.getById(client2.getDni())).thenReturn(null);
		assertFalse(service.delete(client2.getDni()));
	}
}
