package br.luiztoni.faces.product;

import br.luiztoni.faces.config.AbstractRepository;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class ProductRepository extends AbstractRepository<Product> {

    public ProductRepository() {
        super(Product.class);
    }
}
