package de.ingoschindler.wild.entity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Path;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "CATEGORIES", schema = "PUBLIC")
@NamedQueries({
        @NamedQuery(name = Category.FIND_ALL, query = "SELECT  c FROM Category  c WHERE  c.parent is null "),
        @NamedQuery(name = Category.FIND_SUBCATEGORIES, query = "SELECT  c FROM Category  c WHERE  c.parent.id = :parent "),
        @NamedQuery(name = "Category.usersCategories", query = "SELECT distinct p.category.parent FROM Part p WHERE p.owner.ref = :ref order by p.category.parent.priority asc ")
})
public class Category implements Serializable {
    private static final long serialVersionUID = -1643680519898785439L;

    public static final String FIND_ALL = "Category.FIND_ALL";
    public static final String FIND_SUBCATEGORIES = "Category.FIND_SUBCATEGORIES";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Integer priority;

    @NotNull
    private String name;

    @Column(length = 2048)
    private String note;

    @ManyToOne
    @JsonbTransient
    private Category parent;

    @OneToMany(mappedBy = "parent")
    @JsonbTransient
    private List<Category> subcategories;

    @OneToMany(mappedBy = "category")
    @JsonbTransient
    private List<Part> parts;

    @Column(scale = 4, precision = 8)
    private BigDecimal price;

    @NotNull
    @ManyToOne
    private Unit priceUnit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Path("/parts")
    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public List<Category> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<Category> subcategories) {
        this.subcategories = subcategories;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Unit getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(Unit priceUnit) {
        this.priceUnit = priceUnit;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        return id.equals(category.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
