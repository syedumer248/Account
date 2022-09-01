package com.nagarro.account;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
class AccountApplicationTests {
	

	@Autowired
	private MockMvc mvc;

	@Test
	public void testRedirectLoginPage() {
		try {
			mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.TEXT_HTML)).andExpect(status().is(302));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testLoginPage() {
		try {
			mvc.perform(MockMvcRequestBuilders.get("/login").accept(MediaType.TEXT_HTML)).andExpect(status().isOk())
					.andExpect(view().name("login"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
