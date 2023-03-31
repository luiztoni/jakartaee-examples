package br.luiztoni.servlet.product;

import java.util.Map;

import br.luiztoni.servlet.config.Controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ProductController extends Controller {

	private ProductRepository repository;

	public ProductController() {
		repository = new ProductRepository();
	}

	@Override
	public String store(HttpServletRequest request, HttpServletResponse response) {
		String strings[] = request.getRequestURI().split("/");
		Map<String, String[]> map = request.getParameterMap();
		String[] name = map.get("name");
		String[] description = map.get("description");
		String[] price = map.get("price");
		
		Product product = new Product();
		product.setName(name[0]);
		product.setDescription(description[0]);
		product.setPrice(Double.parseDouble(price[0]));
		repository.create(product);
		return index(request, response);
	}

	@Override
	public String show(HttpServletRequest request, HttpServletResponse response) {
		String strings[] = request.getRequestURI().split("/");
		var product = repository.read(Integer.parseInt(strings[4]));
		request.setAttribute("product", product);
		return "/views/show.jsp";
	}

	@Override
	public String update(HttpServletRequest request, HttpServletResponse response) {
		String strings[] = request.getRequestURI().split("/");
		Map<String, String[]> map = request.getParameterMap();
		String[] name = map.get("name");
		String[] description = map.get("description");
		String[] price = map.get("price");
		Product product = new Product();
		product.setName(name[0]);
		product.setDescription(description[0]);
		product.setPrice(Double.parseDouble(price[0]));
		product.setId(Integer.parseInt(strings[4]));
		repository.update(product);
		return index(request, response);
	}

	@Override
	public String create(HttpServletRequest request, HttpServletResponse response) {
	      throw new RuntimeException("Not implemented!");
	}

	@Override
	public String delete(HttpServletRequest request, HttpServletResponse response) {
		String strings[] = request.getRequestURI().split("/");
		repository.delete(Integer.parseInt(strings[4]));
		return index(request, response);
	}

	@Override
	public String index(HttpServletRequest request, HttpServletResponse response) {
		var products = repository.index();
		request.setAttribute("products", products);
		return "/views/index.jsp";
	}
	
	@Override
	public String edit(HttpServletRequest request, HttpServletResponse response) {
		// fomrato do request.getRequestURI(): /admin/product/edit/1
		System.out.println("URI: "+request.getRequestURI());
		String strings[] = request.getRequestURI().split("/");
		var product = repository.read(Integer.parseInt(strings[4]));
		request.setAttribute("product", product);
		return "/views/edit.jsp";
	}

}