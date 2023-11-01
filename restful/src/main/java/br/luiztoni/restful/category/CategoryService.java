package br.luiztoni.restful.category;

import br.luiztoni.restful.config.ApiClient;
import br.luiztoni.restful.config.ResponseRecord;
import jakarta.inject.Inject;

import java.io.IOException;

public class CategoryService {
	@Inject
	private CategoryRepository repository;

	@Inject
	private ApiClient apiClient;

	public Category find(String name) {
		return repository.findByNameLike(name);
	}

	public String getResource(String uri) {
		try {
			ResponseRecord response = apiClient.getRequest(uri);
			return response.body().toString();
		} catch (IOException | InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
