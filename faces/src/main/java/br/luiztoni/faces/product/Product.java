package br.luiztoni.faces.product;

import br.luiztoni.faces.config.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product extends AbstractEntity<Long> {
    
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	@Column(nullable = false, unique = true)
    private String name;

    @Column(length = 255)
    private String description;

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
