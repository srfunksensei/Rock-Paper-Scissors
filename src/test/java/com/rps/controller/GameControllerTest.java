package com.rps.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rps.dto.Game;
import com.rps.exception.GameDoesNotExistException;
import com.rps.pool.GamePool;

@RunWith(SpringRunner.class)
@WebMvcTest(GameController.class)
public class GameControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private GamePool gamePool;
	
	private final Game game = new Game();
	
	@Before
	public void setup() throws GameDoesNotExistException {
		BDDMockito.given(this.gamePool.get(game.getId())).willReturn(game);
	}
	
	@Test
    public void testGameTypes() throws Exception {
        this.mockMvc.perform(get("/games").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));

    }
	
	@Test
	public void testAbortGame() throws Exception {
		this.mockMvc.perform(delete("/games/1").accept(MediaType.APPLICATION_JSON))
        	.andExpect(status().isOk());
	}
	
	@Test
	public void testStartNewGame() throws Exception {
		this.mockMvc.perform(post("/games")
			.content("0").contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON_VALUE))
    		.andExpect(status().isCreated());
	}
	
	@Test
	public void testGetGame() throws Exception {
		String jsonContent = new ObjectMapper().writeValueAsString(game);
		
		this.mockMvc.perform(get("/games/" + game.getId()).accept(MediaType.APPLICATION_JSON))
	        .andExpect(status().isOk())
	        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
	        .andExpect(content().json(jsonContent));
	}
	
	@Test
	public void testPlay() throws Exception {
		this.mockMvc.perform(put("/games/" + game.getId())
				.content("0").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON_VALUE))
	    		.andExpect(status().isOk())
	    		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
	}
	
}
