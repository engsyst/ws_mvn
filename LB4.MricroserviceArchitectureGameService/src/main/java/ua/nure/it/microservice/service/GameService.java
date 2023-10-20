package ua.nure.it.microservice.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.ServletContext;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import ua.nure.it.microservice.entity.Game;

@Path("games")
public class GameService {

	final Logger log = LoggerFactory.getLogger(GameService.class);

	@Context
	private ServletContext servletContext;

	private static final List<Game> games = List.of(
			new Game(1L, "Mario"), 
			new Game(2L, "Puzzle"),
			new Game(3L, "Sudoku"), 
			new Game(4L, "Words")
			);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Game> getAll() {
		log.debug("getAll");
		return games;
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Game getGameById(@PathParam("id") Long id) {
		log.debug("id: {}", id);
		Game game = games.stream().filter(u -> u.getId().equals(id)).findAny()
				.orElseThrow(() -> new NotFoundException("User was not found"));
		log.debug("game: {}", game);
		return game;
	}
}
