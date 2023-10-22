package br.luiztoni.restful.product;

import br.luiztoni.restful.category.Category;
import br.luiztoni.restful.config.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product extends AbstractEntity<Long> {
    @Column(unique = true, nullable = false)
    private String name;

    @Column(length = 40)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

	public Product(String name, String description, Category category) {
		this.name = name;
		this.description = description;
		this.category = category;
	}

	public Product() {}
	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
