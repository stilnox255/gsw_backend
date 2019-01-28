package de.ingoschindler.wild.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "PARTS", schema = "PUBLIC")
@NamedQueries({ @NamedQuery(name = Part.BY_CATEGORY, //
		query = "SELECT p FROM Part p WHERE P.owner.ref = :ref and p.category.id = :cid or p.category.parent.id = :cid order by P.freezeDate asc"),
		@NamedQuery(name = Part.BY_USERNAME, //
				query = "SELECT p FROM Part p WHERE P.owner.username = :username order by P.freezeDate asc"), })
public class Part implements Serializable {
	private static final long serialVersionUID = 1773339296815097191L;

	public static final String BY_CATEGORY = "Part.usersPartsByCategory";

	public static final String BY_USERNAME = "Part.usersPartsByUsername";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 256)
	private String note;

	@NotNull
	private LocalDate freezeDate;

	@NotNull
	private BigDecimal weight;

	@NotNull
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonbTransient
	private User owner;

	@NotNull
	@ManyToOne
	private Category category;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getPrice() {
		return category.getPrice().divide(category.getPriceUnit().getConversionFactor()).multiply(this.weight)
				.setScale(2, RoundingMode.HALF_EVEN);
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public LocalDate getFreezeDate() {
		return freezeDate;
	}

	public void setFreezeDate(LocalDate freezeDate) {
		this.freezeDate = freezeDate;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
