package ua.nure.itech.jaxws.service.entity;

import java.awt.Image;
import java.io.Serializable;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlMimeType;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "User", propOrder = {
    "name", "avatar"
})
public class User implements Serializable {
	
    @XmlElement(required = true)
	private String name;
    @XmlElement
    @XmlMimeType("image/*")
	private Image avatar;

	public User() {
	}

	public User(String name, Image avatar) {
		this.name = name;
		this.avatar = avatar;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Image getAvatar() {
		return avatar;
	}

	public void setAvatar(Image avatar) {
		this.avatar = avatar;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", avatar=" + (avatar) + "]";
	}
}
