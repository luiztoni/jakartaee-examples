package br.luiztoni.restful.config.auth;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.annotation.security.DenyAll;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
public class UserFilter implements ContainerRequestFilter {
	@Context
	private ResourceInfo resourceInfo;

	private static final String AUTHORIZATION_PROPERTY = "Authorization";

	@Override
	public void filter(ContainerRequestContext request) throws IOException {
		Method method = resourceInfo.getResourceMethod();

		if (!method.isAnnotationPresent(PermitAll.class)) {
			if (method.isAnnotationPresent(DenyAll.class)) {
				request.abortWith(Response.status(Response.Status.FORBIDDEN).entity("Deny all.").build());
				return;
			}

			final MultivaluedMap<String, String> headers = request.getHeaders();
			final List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);
			if (authorization == null || authorization.isEmpty()) {
				request.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Unauthorized.").build());
				return;
			}

			String[] credentials = (new String(Base64.getDecoder().decode(authorization.get(0).split(" ")[1]), "UTF-8"))
					.split(":");

			final String email = credentials[0];
			final String password = credentials[1];
			if (method.isAnnotationPresent(RolesAllowed.class)) {
				RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
				Set<String> roles = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));

				if (!isUserAllowed(email, password, roles)) {
					request.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Unauthorized.").build());
					return;
				}
			}
		}
	}

	private boolean isUserAllowed(final String email, final String password, final Set<String> roles) {
		boolean isAllowed = false;
		if (email.equals("admin@admin.com") && password.equals("password")) {
			String userRole = "admin";
			if (roles.contains(userRole)) {
				isAllowed = true;
			}
		}
		return isAllowed;
	}
}
