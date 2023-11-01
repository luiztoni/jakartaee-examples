package br.luiztoni.restful.category;

import static org.junit.jupiter.api.Assertions.assertEquals;

import br.luiztoni.restful.config.ApiClient;
import br.luiztoni.restful.config.ResponseRecord;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.io.IOException;

public class CategoryServiceTest {
	@Mock
	private CategoryRepository repository;

	@Mock
	private ApiClient apiClient;

	@InjectMocks
	private CategoryService service;

	private ArgumentCaptor<String> queryCaptor = ArgumentCaptor.forClass(String.class);

    @BeforeEach
	public void setup() {
		service = new CategoryService();
		repository = new CategoryRepository();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testFind() {
		Category category = new Category("Drinks", "Drinks");
		Mockito.when(repository.findByNameLike(Mockito.any(String.class))).thenReturn(category);
		Category result = service.find("Tubaina");
		Mockito.verify(repository, Mockito.times(1)).findByNameLike(queryCaptor.capture());
		assertEquals(category.getName(), result.getName());
		assertEquals("Tubaina", queryCaptor.getValue());
	}

	@Test
	public void testGetResource() throws IOException, InterruptedException {
		ResponseRecord responseRecord = new ResponseRecord("Any response payload",200);
		Mockito.when(apiClient.getRequest(Mockito.any(String.class))).thenReturn(responseRecord);
		String answer = service.getResource("java");
		Mockito.verify(apiClient, Mockito.times(1)).getRequest(queryCaptor.capture());
		assertEquals(responseRecord.body(), answer);
		assertEquals("java", queryCaptor.getValue());
	}
}
