package br.luiztoni.restful.config;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

public final class ApiClient {

	private String authorization;

	public void setAuthorization(String username, String password) {
		authorization = null;
		if (username != null && password != null) {
			authorization = getBasicAuthenticationHeader(username, password);
		}
	}

	private static final String getBasicAuthenticationHeader(String username, String password) {
		String valueToEncode = username + ":" + password;
		return "Basic " + Base64.getEncoder().encodeToString(valueToEncode.getBytes());
	}

	public HttpResponse<String> postRequest(String uri, Object payload) throws IOException, InterruptedException {
		Jsonb jsonb = JsonbBuilder.create();
		String json = jsonb.toJson(payload);
		HttpRequest.Builder builder = HttpRequest.newBuilder()
			.POST(HttpRequest.BodyPublishers.ofString(json))
			.version(HttpClient.Version.HTTP_1_1)
			.uri(URI.create(uri))
			.timeout(Duration.of(3, ChronoUnit.SECONDS))
			.header("Content-Type","application/json");
		if (authorization != null) {
			builder.header("Authorization", authorization);
		}
		HttpRequest request = builder.build();

		HttpResponse response = HttpClient.newBuilder()
			.build()
			.send(request, HttpResponse.BodyHandlers.discarding());
		return response;
	}

	public HttpResponse<String> putRequest(String uri, Object payload) throws IOException, InterruptedException {
		Jsonb jsonb = JsonbBuilder.create();
		String json = jsonb.toJson(payload);
		HttpRequest.Builder builder = HttpRequest.newBuilder()
			.PUT(HttpRequest.BodyPublishers.ofString(json))
			.version(HttpClient.Version.HTTP_1_1)
			.uri(URI.create(uri))
			.timeout(Duration.of(3, ChronoUnit.SECONDS))
			.header("Content-Type","application/json");
		if (authorization != null) {
			builder.header("Authorization", authorization);
		}
		HttpRequest request = builder.build();

		HttpResponse response = HttpClient.newBuilder()
			.build()
			.send(request, HttpResponse.BodyHandlers.discarding());
		return response;
	}
	public HttpResponse<String> getRequest(String uri) throws IOException, InterruptedException {
		HttpRequest.Builder builder = HttpRequest.newBuilder()
			.GET()
			.uri(URI.create(uri))
			.version(HttpClient.Version.HTTP_1_1)
			.timeout(Duration.of(3, ChronoUnit.SECONDS))
			.header("Content-Type","application/json");
		if (authorization != null) {
			builder.header("Authorization", authorization);
		}
		HttpRequest request = builder.build();
		HttpResponse<String> response = HttpClient.newBuilder()
			.build()
			.send(request, HttpResponse.BodyHandlers.ofString());
		return response;
	}

	public HttpResponse<String> deleteRequest(String uri) throws IOException, InterruptedException {
		HttpRequest.Builder builder = HttpRequest.newBuilder()
			.DELETE()
			.uri(URI.create(uri))
			.version(HttpClient.Version.HTTP_1_1)
			.timeout(Duration.of(3, ChronoUnit.SECONDS))
			.header("Content-Type","application/json");
		if (authorization != null) {
			builder.header("Authorization", authorization);
		}
		HttpRequest request = builder.build();
		HttpResponse response = HttpClient.newBuilder()
			.build()
			.send(request, HttpResponse.BodyHandlers.discarding());
		return response;
	}
}

