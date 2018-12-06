package de.ingoschindler.wild;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.ingoschindler.wild.entity.Category;
import de.ingoschindler.wild.entity.Part;
import de.ingoschindler.wild.entity.Unit;
import de.ingoschindler.wild.entity.User;

@ApplicationScoped
@Singleton
@Startup
public class Initializer {

	@PersistenceContext
	private EntityManager em;

	private User u;

	private Unit kgProEur;

	@PostConstruct
	private void init() {

		u = this.createUser();

		kgProEur = new Unit();
		kgProEur.setConversionFactor(new BigDecimal("1000"));
		kgProEur.setFactorUnit("g");
		kgProEur.setShortUnit("kg");
		kgProEur.setUnit("Kilogramm");
		kgProEur.setCurrency("€");
		em.persist(kgProEur);

		Category h = newCategory(null, "Honig", 0.0);
		Category h1 = newCategory(h, "Tannen-Honig", 3.5);
		Category h2 = newCategory(h, "Frühlingstraum", 4.5);

		Category reh = newCategory(null, "Reh", 4.5);
		Category r1 = newCategory(reh, "Rücken", 30.0);
		Category r2 = newCategory(reh, "Schulter (ausgelößt)", 14.0);
		Category r3 = newCategory(reh, "Keule (ausgelößt)", 24.0);
		Category r4 = newCategory(reh, "Filet", 35.0);

		Category ws = newCategory(null, "Wildschwein", 3.5);
		Category ws1 = newCategory(ws, "Rücken", 26.5);
		Category ws2 = newCategory(ws, "Schulter (ausgelößt)", 12.0);
		Category ws3 = newCategory(ws, "Keule (ausgelößt)", 21.0);
		Category ws4 = newCategory(ws, "Filet", 30.0);

		newPart(h1);
		newPart(r1);
		newPart(ws4);
		newPart(r2);
		newPart(ws3);
		newPart(h2);
		newPart(r4);
		newPart(ws);
		newPart(ws1);
		newPart(h1);
		newPart(r3);
		newPart(r2);
		newPart(h2);
		newPart(ws2);

	}

	private Category newCategory(Category parent, String name, Double price) {
		Category category = new Category();
		category.setName(name);
		category.setPrice(new BigDecimal(price));
		category.setParent(parent);
		category.setPriceUnit(kgProEur);
		em.persist(category);

		return category;
	}

	private void newPart(Category c) {
		Part keule = new Part();
		keule.setCategory(c);
		keule.setFreezeDate(LocalDate.now());
		keule.setOwner(u);
		keule.setWeight(new BigDecimal((int) (Math.random() * 1000)));
		em.persist(keule);
	}

	private User createUser() {
		User u = new User();

		u.setPassword("test");

		u.setEmail("test@ingoschindler.de");

		u.setUsername("test");

		u.setRef("3sna4svg5dwtzhf");

		u.setPublicName("Ingo Schindler");
		em.persist(u);
		return u;
	}

	private String generateRefLink() {
		byte[] array = new byte[15]; // length is bounded by 7
		new Random().nextBytes(array);
		String generatedString = new String(array, Charset.forName("UTF-8"));

		System.out.println(generatedString);

		return generatedString;
	}
}
