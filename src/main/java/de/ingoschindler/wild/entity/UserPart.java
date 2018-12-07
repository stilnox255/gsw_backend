package de.ingoschindler.wild.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class UserPart {

	private Long id;
	private String name;
	private String weight;
	private String price;
	private String whatsapp;
	private String mail;
	private boolean isReh;
	private boolean isWs;
	private boolean isHoney;
	private LocalDate freezeDate;

	public UserPart(Part p) {

		this.id = p.getId();
		BigDecimal w;
		String u;
		Category c = p.getCategory();
		Unit pu = c.getPriceUnit();

		this.name = c.getName();

		if (p.getWeight().compareTo(pu.getConversionFactor()) >= 1) {
			w = p.getWeight().divide(pu.getConversionFactor()).setScale(2);
			u = pu.getShortUnit();
		} else {
			w = p.getWeight().setScale(0);
			u = pu.getFactorUnit();

		}
		this.weight = w.toString();
		this.weight += u;

		this.price = c.getPrice().divide(pu.getConversionFactor()).multiply(p.getWeight())
				.setScale(2, RoundingMode.HALF_EVEN).toString();
		this.price += pu.getCurrency() + " (" + c.getPrice().setScale(2).toString() + pu.getCurrency() + "/"
				+ pu.getShortUnit() + ")";

		this.freezeDate = p.getFreezeDate();

		this.isReh = false;
		this.isWs = false;
		this.isHoney = false;

		switch (c.getName()) {
		case "Reh":
			this.isReh = true;
			break;
		case "Wildschwein":
			this.isWs = true;
			break;
		case "Honig":
			this.isHoney = true;
			break;
		}

		StringBuilder sb = new StringBuilder("Hallo! ich hätte gerne ");

		sb.append(name);
		sb.append(" (");
		sb.append(this.weight);
		sb.append(") für ");
		sb.append(this.price);
		sb.append(". Lieben Dank!");

		this.whatsapp = sb.toString();

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

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getWhatsapp() {
		return whatsapp;
	}

	public void setWhatsapp(String whatsapp) {
		this.whatsapp = whatsapp;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public boolean isReh() {
		return isReh;
	}

	public void setReh(boolean isReh) {
		this.isReh = isReh;
	}

	public boolean isWs() {
		return isWs;
	}

	public void setWs(boolean isWs) {
		this.isWs = isWs;
	}

	public boolean isHoney() {
		return isHoney;
	}

	public void setHoney(boolean isHoney) {
		this.isHoney = isHoney;
	}

	public LocalDate getFreezeDate() {
		return freezeDate;
	}

	public void setFreezeDate(LocalDate freezeDate) {
		this.freezeDate = freezeDate;
	}

}
