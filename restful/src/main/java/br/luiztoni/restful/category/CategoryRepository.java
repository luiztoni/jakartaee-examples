package br.luiztoni.restful.category;

import java.util.List;

import br.luiztoni.restful.config.AbstractRepository;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class CategoryRepository extends AbstractRepository<Category> {

    public CategoryRepository() {
        super(Category.class);
    }

    @Deprecated
    public List<Category> listAll() {
        List<Category> categories = manager.createNamedQuery("Category.index", Category.class).getResultList();
        return categories;
    }

	public Category findByNameLike(String name) {
		Category category;
		try {
			category = (Category) manager.createQuery("SELECT c FROM Category c WHERE c.name like :name").setParameter("name", name).getSingleResult();
		} catch (Exception exception) {
			return null;
		}
		return category;
	}
    
}

