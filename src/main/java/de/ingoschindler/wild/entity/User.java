package de.ingoschindler.wild.entity;

import java.io.Serializable;
import java.util.List;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Null;
import javax.ws.rs.Path;

@Entity
@Table(name = "USERS", schema = "PUBLIC")
@NamedQuery(query = "SELECT u from User u where u.ref = :ref", name = User.USER_BY_REF)
@NamedQuery(query = "SELECT u from User u where u.username = :username", name = "User.byUsername")
public class User implements Serializable {
	private static final long serialVersionUID = -2122778146143882079L;

	public static final String USER_BY_REF = "User.byRef";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 25, unique = true)
	@NotEmpty
	private String username;

	@Email
	@NotEmpty
	@Column(unique = true)
	private String email;

	@Column(length = 25)
	private String publicName;

	@Column(unique = true, length = 15)
	private String ref;

	@Column(length = 50)
	private String realName;

	@Convert(converter = PasswordConverter.class)
	@Column(length = 255)
	@JsonbTransient
	private String password;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
	@JsonbTransient
	private List<Part> parts;

	@Embedded
	@Null
	private Location location;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "USER_GROUPS", joinColumns = { @JoinColumn(name = "GROUP_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "USERS_ID") })
	@JsonbTransient
	private List<Group> groups;

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Path("/email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPublicName() {
		return publicName;
	}

	public void setPublicName(String publicName) {
		this.publicName = publicName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Path("/parts")
	public List<Part> getParts() {
		return parts;
	}

	public void setParts(List<Part> parts) {
		this.parts = parts;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

}
