package ua.nure.it.microservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import ua.nure.it.microservice.entity.Game;
import ua.nure.it.microservice.entity.User;

@RestController
@RequestMapping(path = "users")
public class UserService {
	final Logger log = LoggerFactory.getLogger(UserService.class);

	private static final List<User> users = List.of(
			new User(1L, "Jhon", List.of(1L, 3L)),
			new User(2L, "Alice", List.of(2L, 3L)),
			new User(3L, "Suzen", List.of(2L)),
			new User(4L, "Mike", List.of(1L, 2L))
			);
	
	@Autowired
	private RestTemplate gameService;
	
	@Value("${ua.nure.it.microservice.service.game-service-uri}")
	private String gameServiceUri;


	@GetMapping
	public List<User> getAll() {
		log.debug("getAll");
		return users;
	}

	@GetMapping(path = "{id}/favorits", 
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public List<Game> getuserFavoitGames(@PathVariable("id") Long id) {
		log.debug("id: {}", id);
		User user = gatUserById(id).orElseThrow(
				() -> new ResourceNotFoundException("User was not found"));
		List<Game> favorits = new ArrayList<>();
		for (Long fId : user.getFavoritIds()) {
			favorits.add(getGameById(fId));
		}
		return favorits;
	}

	private Game getGameById(Long id) {
		return gameService.getForEntity(gameServiceUri + "/" + id, Game.class).getBody();
	}

	private Optional<User> gatUserById(Long id) {
		return users.stream().filter(u -> u.getId().equals(id)).findAny();
	}
}
