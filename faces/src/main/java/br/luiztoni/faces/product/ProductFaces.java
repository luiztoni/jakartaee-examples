package br.luiztoni.faces.product;

import java.io.Serializable;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIInput;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@SessionScoped
public class ProductFaces implements Serializable {

    private static final long serialVersionUID = 1L;

   
    private Product product;

    private boolean edit;

    private boolean show;

    private List<Product> products;

    @Inject
    private ProductRepository repository;

    @PostConstruct
    public void init() {
        index();
    }

    public void index() {
        products = repository.index();
        product = new Product();
        this.edit = false;
        this.show = false;
    }

    public String store() {
        repository.create(this.product);
        index();
        return "dashboard";
    }

    public String update() {
        repository.update(this.product);
        index();
        return "dashboard";
    }

    public void edit(Product product) {
        this.product = product;
        this.edit = true;
    }

    public void show(Product product) {
        this.product = product;
        this.show = true;
    }

    public String delete(Product product) {
        repository.delete(product);
        index();
        return "dashboard";
    }

    public String cancel() {
        index();
        return "dashboard";
    }

    public void validate(FacesContext context,
                         UIComponent toValidate, Object value) {
        String name = (String) value;
        Product product = new Product();
        product.setName(name);
        if (products.contains(product)) {
            ((UIInput)toValidate).setValid(false);

            FacesMessage message = new FacesMessage("This product exists in list.");
            context.addMessage(toValidate.getClientId(context), message);
        }
    }

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public boolean isEdit() {
		return edit;
	}

	public void setEdit(boolean edit) {
		this.edit = edit;
	}

	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
