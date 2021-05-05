package com.rps.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rps.dto.Game;
import com.rps.dto.GameType;
import com.rps.dto.Move;
import com.rps.exception.GameDoesNotExistException;
import com.rps.pool.GamePool;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GameController.class)
public class GameControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private GamePool<String, Game> gamePool;

	private final ObjectMapper jsonMapper = new ObjectMapper();

	private final Game game = new Game(GameType.PersonVsComputer);

	@BeforeEach
	public void setup() throws GameDoesNotExistException {
		BDDMockito.given(gamePool.get(game.getId())).willReturn(game);
	}

	@Test
    public void gameTypes() throws Exception {
		final String jsonContent = jsonMapper.writeValueAsString(GameType.values());
		mockMvc.perform(get("/games/gameTypes")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().json(jsonContent));
	}

	@Test
	public void abortGame_nonExistingGame() throws Exception {
		mockMvc.perform(delete("/games/1")
				.accept(MediaType.APPLICATION_JSON))
        		.andExpect(status().isNoContent());
	}

	@Test
	public void abortGame() throws Exception {
		mockMvc.perform(delete("/games/" + game.getId())
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}

	@Test
	public void startNewGame() throws Exception {
		final String jsonContent = jsonMapper.writeValueAsString(game.getGameType().name());

		mockMvc.perform(post("/games/start")
				.content(jsonContent)
				.contentType(MediaType.APPLICATION_JSON))
    			.andExpect(status().isCreated())
				.andReturn();
	}

	@Test
	public void startNewGame_notExistingGameType() throws Exception {
		mockMvc.perform(post("/games/start")
				.content("notExistingGameType")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andReturn();
	}

	@Test
	public void getGame() throws Exception {
		final String jsonContent = jsonMapper.writeValueAsString(game);

		mockMvc.perform(get("/games/" + game.getId())
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().json(jsonContent));
	}

	@Test
	public void play() throws Exception {
		final String jsonContent = jsonMapper.writeValueAsString(Move.Paper.name());

		mockMvc.perform(put("/games/" + game.getId())
				.content(jsonContent)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON_VALUE))
	    		.andExpect(status().isOk())
	    		.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	public void play_notExistingMove() throws Exception {
		mockMvc.perform(put("/games/" + game.getId())
				.content("notExistingMove")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isBadRequest());
	}
}
