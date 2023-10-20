package ua.nure.it.microservice.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class User implements Serializable {

	private Long id;
	private String name;
	private List<Long> favoritIds;
	private transient List<Game> favorits;

	public User() {
		super();
	}

	public User(Long id, String name, List<Long> favoritIds) {
		super();
		this.id = id;
		this.name = name;
		this.favoritIds = favoritIds;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Long> getFavoritIds() {
		return favoritIds;
	}

	public void setFavoritIds(List<Long> favoritIds) {
		this.favoritIds = favoritIds;
	}

	public List<Game> getFavorits() {
		return favorits;
	}

	public void setFavorits(List<Game> favorits) {
		this.favorits = favorits;
	}

	@Override
	public int hashCode() {
		return Objects.hash(favoritIds, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(favoritIds, other.favoritIds) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", favoritIds=" + favoritIds + "]";
	}

}
