package br.luiztoni.restful.product;

import br.luiztoni.restful.config.AbstractRepository;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class ProductRepository extends AbstractRepository<Product> {
    public ProductRepository() {
        super(Product.class);
    }
}
