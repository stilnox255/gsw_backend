package de.ingoschindler.wild.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "UNITS", schema = "PUBLIC")
public class Unit implements Serializable {
	private static final long serialVersionUID = -6990660619368064268L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 1024)
	private String note;

	@Column(length = 10)
	private String unit;

	@Column(length = 3)
	private String currency;

	@Column(length = 3)
	private String shortUnit;

	@NotNull
	private String factorUnit;

	@NotNull
	private BigDecimal conversionFactor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getShortUnit() {
		return shortUnit;
	}

	public void setShortUnit(String shortUnit) {
		this.shortUnit = shortUnit;
	}

	public BigDecimal getConversionFactor() {
		return conversionFactor;
	}

	public void setConversionFactor(BigDecimal conversionFactor) {
		this.conversionFactor = conversionFactor;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getFactorUnit() {
		return factorUnit;
	}

	public void setFactorUnit(String factorUnit) {
		this.factorUnit = factorUnit;
	}

}
