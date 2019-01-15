package de.ingoschindler.wild;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.ingoschindler.wild.entity.Category;
import de.ingoschindler.wild.entity.Group;
import de.ingoschindler.wild.entity.Part;
import de.ingoschindler.wild.entity.Unit;
import de.ingoschindler.wild.entity.User;
import de.ingoschindler.wild.members.CustomerManager;

@ApplicationScoped
@Singleton
@Startup
public class Initializer {

	@PersistenceContext(unitName = "wild")
	private EntityManager em;

	private User u;

	private Unit kgProEur;

	@Inject
	private CustomerManager cm;

	@PostConstruct
	private void init() {

		Group g = new Group();
		g.setGroupName("USER");
		em.persist(g);

		u = this.createUser(g);

		kgProEur = new Unit();
		kgProEur.setConversionFactor(new BigDecimal("1000"));
		kgProEur.setFactorUnit("g");
		kgProEur.setShortUnit("kg");
		kgProEur.setUnit("Kilogramm");
		kgProEur.setCurrency("€");
		em.persist(kgProEur);

		Category h = newCategory(null, "Honig", 0.0, 3);
		Category h1 = newCategory(h, "Tannen-Honig", 3.5, 2);
		Category h2 = newCategory(h, "Frühlingstraum", 4.5, 1);

		Category reh = newCategory(null, "Reh", 4.5, 2);
		Category r1 = newCategory(reh, "Keule (am Knochen)", 20.0, 3);
		Category r2 = newCategory(reh, "Keule (ausgelößt)", 24.0, 3);
		Category r3 = newCategory(reh, "Rücken (am Knochen)", 25.0, 2);
		Category r4 = newCategory(reh, "Rücken (ausgelöse)", 30.0, 2);
		Category r5 = newCategory(reh, "Blatt/Schulter (ausgelößt)", 14.0, 4);
		Category r6 = newCategory(reh, "Rippen/Bauch", 7.0, 1);
		Category r7 = newCategory(reh, "Träger/Kamm", 10.0, 1);
		Category r8 = newCategory(reh, "Gulasch", 15.0, 1);
		Category r9 = newCategory(reh, "Filet", 35.0, 1);

		Category ws = newCategory(null, "Wildschwein", 3.5, 1);
		Category ws1 = newCategory(ws, "Keule (am Knochen)", 20.0, 3);
		Category ws2 = newCategory(ws, "Keule (ausgelößt)", 24.0, 3);
		Category ws3 = newCategory(ws, "Rücken (am Knochen)", 25.0, 2);
		Category ws4 = newCategory(ws, "Rücken (ausgelöse)", 30.0, 2);
		Category ws5 = newCategory(ws, "Blatt/Schulter (ausgelößt)", 14.0, 4);
		Category ws6 = newCategory(ws, "Rippen/Bauch", 7.0, 1);
		Category ws7 = newCategory(ws, "Träger/Kamm", 10.0, 1);
		Category ws8 = newCategory(ws, "Gulasch", 15.0, 1);
		Category ws9 = newCategory(ws, "Filet", 35.0, 1);

		newPart(h1, 100);
		newPart(h2, 100);
		newPart(h1, 100);
		newPart(h2, 100);

		newPart(r1, 2100);
		newPart(r2, 900);
		newPart(r4, 600);
		newPart(r3, 1200);
		newPart(r2, 1600);
		newPart(r2, 1000);

		newPart(ws8, 1800);
		newPart(ws1, 2600);
		newPart(ws2, 2600);
		newPart(ws2, 600);
		newPart(ws4, 760);
		newPart(ws3, 680);
		newPart(ws1, 540);
		newPart(ws2, 750);

	}

	private Category newCategory(Category parent, String name, Double price, Integer prio) {
		Category category = new Category();
		category.setName(name);
		category.setPrice(new BigDecimal(price));
		category.setParent(parent);
		category.setPriceUnit(kgProEur);
		category.setPriority(prio);
		em.persist(category);

		return category;
	}

	private void newPart(Category c, int weight) {
		Part keule = new Part();
		keule.setCategory(c);
		keule.setFreezeDate(LocalDate.now());
		keule.setOwner(u);
		keule.setWeight(new BigDecimal(weight));
		em.persist(keule);
	}

	private User createUser(Group g) {
		User u = new User();

		u.setPassword("test");

		u.setEmail("test@ingoschindler.de");

		u.setUsername("test");

		u.setRef(cm.generateRefLink());

		u.setPublicName("Ingo Schindler");
		u.setGroups(Collections.singletonList(g));
		em.persist(u);
		return u;
	}

}
