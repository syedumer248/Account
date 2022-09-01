package com.nagarro.account;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;;

@SpringBootTest
public class AuthenticationTest {

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private FilterChainProxy springSecurityFilterChain;

	private MockMvc mockMvc;

	@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).addFilter(springSecurityFilterChain).build();
	}

	@Test
	public void testLoginWithAdminCredentials() {
		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/search").with(user("admin").password("admin")))
					.andExpect(status().isOk()).andExpect(view().name("search"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testLoginWithUserCredentials() {
		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/search").with(user("user").password("user")))
					.andExpect(status().isOk()).andExpect(view().name("search"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testLoginWithDifferentUser() {
		try {
			mockMvc.perform(MockMvcRequestBuilders.post("/accountStatement").with(user("user1").password("user")))
					.andExpect(status().isForbidden());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
