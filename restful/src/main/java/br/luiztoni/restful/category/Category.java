package br.luiztoni.restful.category;

import br.luiztoni.restful.config.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@NamedQueries({
        @NamedQuery(name = "Category.index", query = "SELECT c FROM Category c")
})
@Table(name = "categories")
public class Category extends AbstractEntity<Long> {

    @NotNull
    private String name;

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

	public Category(@NotNull String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
    
	public Category() {
	}

	public Category(Long id) {
		this.setId(id);
	}
}
