package Dia27.EjerciciosManana27;

import Dia27.EjerciciosManana27.models.MyUser;
import Dia27.EjerciciosManana27.models.Product;
import Dia27.EjerciciosManana27.models.Role;
import Dia27.EjerciciosManana27.services.UserService;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
class EjerciciosManana27ApplicationTests {
/*
	@Autowired
	private UserService uA;
	@Autowired
	PasswordEncoder pE;

	@Test
	void contextLoads() {
		MyUser user = new MyUser();

		user.setUsername("admin");
		user.setPassword(pE.encode("123"));
		Role role = new Role(1,"ADMIN");
		user.setRole(role);

		uA.save(user);
		MyUser r = uA.findByUsername(user.getUsername());
		Assert.assertTrue(r.getPassword().equalsIgnoreCase(user.getPassword()));
*/
	@Test
	public void setup() {

	}
}