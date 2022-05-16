package Dia27.EjerciciosManana27.Product;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import Dia27.EjerciciosManana27.models.Product;
import Dia27.EjerciciosManana27.repositories.ProductRepository;
import Dia27.EjerciciosManana27.services.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {
 
	@InjectMocks
	private ProductService service;
	
	@Mock
	private ProductRepository mockRepository;
	
	private Product product = new Product(34, "product1", 200);
	
	@Test
	public void SELECT_CLIENTS() {
		when(mockRepository.findAll()).thenReturn(new ArrayList<Product>());
		assertNotNull(service.getAll());
	}
	
	@Test
	public void SAVE_PRODUCT() {
		when(mockRepository.save(product)).thenReturn(product);
		assertNotNull(service.save(product));
	}
	
	@Test
	public void SAVE_PRODUCT_FAIL() {
		when(mockRepository.save(product)).thenReturn(null);
		assertNull(service.save(product));
	}
	
	@Test
	public void UPDATE_PRODUCT() {
		when(mockRepository.getById(product.getCode())).thenReturn(product);
		when(mockRepository.save(product)).thenReturn(product);
		assertNotNull(service.update(product, product.getCode()));
	}
	
	@Test
	public void UPDATE_PRODUCT_FAIL() {
		when(mockRepository.getById(product.getCode())).thenReturn(null);
		assertNull(service.update(product, product.getCode()));
	}
	
	@Test
	public void DELETE_PRODUCT() {
		when(mockRepository.getById(product.getCode())).thenReturn(product);
		assertTrue(service.delete(product.getCode()));
	}
	
	@Test
	public void DELETE_PRODUCT_FAIL() {
		when(mockRepository.getById(product.getCode())).thenReturn(null);
		assertFalse(service.delete(product.getCode()));
	}
}