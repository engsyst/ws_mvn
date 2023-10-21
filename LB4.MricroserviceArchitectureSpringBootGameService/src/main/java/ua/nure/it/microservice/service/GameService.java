package ua.nure.it.microservice.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.nure.it.microservice.entity.Game;

@RestController
@RequestMapping(path = "games")
public class GameService {
	final Logger log = LoggerFactory.getLogger(GameService.class);

	private static final List<Game> games = List.of(
			new Game(1L, "Mario"), 
			new Game(2L, "Puzzle"),
			new Game(3L, "Sudoku"), 
			new Game(4L, "Words")
			);

	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public List<Game> getAll() {
		log.debug("getAll");
		return new ArrayList<>(games);
	}

	@GetMapping(path = "{id}")
	public Game getGameById(@PathVariable("id") Long id) {
		log.debug("id: {}", id);
		Game game = games.stream().filter(u -> u.getId().equals(id)).findAny()
				.orElseThrow(() -> new ResourceNotFoundException("Game was not found"));
		log.debug("game: {}", game);
		return game;
	}
}
