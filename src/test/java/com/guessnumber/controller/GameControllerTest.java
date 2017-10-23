package com.guessnumber.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.guessnumber.model.Game;
import com.guessnumber.model.User;
import com.guessnumber.service.GameService;
import com.guessnumber.service.UserService;


@RunWith(MockitoJUnitRunner.class)
public class GameControllerTest {

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Mock
	public GameService gameService;

	@Mock
	public UserService userService;
	
	@InjectMocks
	public GameController gameController;
	
    private MockMvc mockMvc;


    @Before
    public void setup() {
    	MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(gameController).build();
    }


    @Test
	public void registerUser() throws Exception {

		User newUser = new User();
		newUser.setUserName("TESTUSER");
		
		Game game = Game.getInstance();
		
		when(gameService.getGame()).thenReturn(game);
		when(userService.registerUser(newUser)).thenReturn(true);

		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson=ow.writeValueAsString(newUser);

	    mockMvc.perform(post("/api/register").contentType(APPLICATION_JSON_UTF8)
	        .content(requestJson))
	        .andExpect(status().isOk());
	}
}
