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
        List<Category> studios = manager.createNamedQuery("Category.index", Category.class).getResultList();
        return studios;
    }
    
}

