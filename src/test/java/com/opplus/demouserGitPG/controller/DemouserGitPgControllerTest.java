package com.opplus.demouserGitPG.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opplus.demouserGitPG.dto.UserDto;
import com.opplus.demouserGitPG.service.UserService;

@WebMvcTest(DemouserGitPgControllerTest.class)
public class DemouserGitPgControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@Autowired
	private ObjectMapper objectMapper;

	private final String URL = "/User/";

	@Test
	public void findAll_test() throws Exception {
		List<UserDto> list_usuarios = getUsuarios();
		List<UserDto> list_usuarios_find = userService.findAll();
		when(list_usuarios_find).thenReturn(list_usuarios);

		mockMvc.perform(get("/User").header("Access-Control-Request-Method", "GET")).andExpect(status().isOk())
				.andExpect(jsonPath("$.size()").value(list_usuarios.size())).andDo(print());

	}

	@Test
	public void createUser_test() throws Exception {
		UserDto user = new UserDto("Usuario", "Test", "Test");

		mockMvc.perform(
				post("/User/").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(user)))
				.andExpect(status().isCreated()).andDo(print());
	}

	private List<UserDto> getUsuarios() {
		List<UserDto> usuarios = new ArrayList<>();
		usuarios.add(new UserDto("Manolo", "Lopez", "Sanchez"));
		usuarios.add(new UserDto("Aitana", "Sanchez", "Gijon"));
		usuarios.add(new UserDto("Clotilda", "Fernandez", "Sandia"));
		return usuarios;

	}
}
