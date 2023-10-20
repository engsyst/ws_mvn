package ua.nure.it.microservice.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.http.HttpResponse.BodySubscriber;
import java.net.http.HttpResponse.ResponseInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.ServletContext;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Invocation.Builder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ua.nure.it.microservice.entity.Game;
import ua.nure.it.microservice.entity.User;

@Path("users")
public class UserService {
	
	final Logger log = LoggerFactory.getLogger(UserService.class);

    @Context
    private ServletContext servletContext;
    private String gamesServiceUri;
	
	private static final List<User> users = List.of(
			new User(1L, "Jhon", List.of(1L, 3L)),
			new User(2L, "Alice", List.of(2L, 3L)),
			new User(3L, "Suzen", List.of(2L)),
			new User(4L, "Mike", List.of(1L, 2L))
			);

	@PostConstruct
	void init() {
		gamesServiceUri = servletContext.getInitParameter("gamesServiceUri");
		log.debug("gamesServiceUri: {}", gamesServiceUri);
		if (gamesServiceUri == null) {
			log.error("'gamesServiceUri' property was not configured");
			throw new NullPointerException("'gamesServiceUri' property was not configured");
		}
	}
	 
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getAll() {
		log.debug("getAll");
		return users;
	}

	@GET
	@Path("{id}/favorits")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Game> getuserFavoitGames(@PathParam("id") Long id) {
		log.debug("id: {}", id);
		User user = users.stream()
				.filter(u -> u.getId().equals(id))
				.findAny()
				.orElseThrow(() -> new NotFoundException("User was not found"));
		log.debug("user: {}", user);
		
		List<Game> favorits = getFavoritsHttpClient(user.getFavoritIds());
		
		// An Unknown error occurs in jackson
//		List<Game> favorits = getFavoritsRestClient(user.getFavoritIds());
		log.debug("games: {}", favorits);
		return favorits;

	}

	private List<Game> getFavoritsHttpClient(List<Long> favoritIds) {
		HttpClient httpClient = HttpClient.newHttpClient();
		java.net.http.HttpRequest.Builder requestBuilder = HttpRequest.newBuilder();

		List<Game> favorits = new ArrayList<>();
		for (Long gameId : favoritIds) {
			try {
				HttpRequest request = requestBuilder.copy()
						.uri(new URI(gamesServiceUri + "/" + gameId))
						.GET()
						.build();
				HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
				String body = response.body();
				ObjectMapper mapper = new ObjectMapper();
				Game readValue = mapper.readerFor(Game.class).readValue(body);
				log.debug("{}", Objects.toString(readValue));
				favorits.add(readValue);
			} catch (URISyntaxException | IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		return favorits;
	}
	
	private List<Game> getFavoritsRestClient(List<Long> favoritIds) {
		try (Client client = ClientBuilder.newClient();) {
			WebTarget target = client.target(gamesServiceUri);
			
			List<Game> favorits = new ArrayList<>();
			for (Long gameId : favoritIds) {
				WebTarget path = target.path(gameId.toString());
				log.debug("web target: {}", path);
				Builder request = path.request();
				request = request.accept(MediaType.APPLICATION_JSON);
				Response response = request.get();
				Game game = response.readEntity(Game.class);
				favorits.add(game);
			}
			return favorits;
		}
	}
}
