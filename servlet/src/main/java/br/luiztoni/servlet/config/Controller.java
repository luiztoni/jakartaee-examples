package br.luiztoni.servlet.config;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public abstract class Controller {
	public String execute(HttpServletRequest request, HttpServletResponse response, String methodName) {
		try {
			Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			return (String) method.invoke(this, request, response);	
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			return null;
		}
	}
	
	public abstract String store(HttpServletRequest request, HttpServletResponse response);
	public abstract String show(HttpServletRequest request, HttpServletResponse response);
	public abstract String update(HttpServletRequest request, HttpServletResponse response);
	public abstract String create(HttpServletRequest request, HttpServletResponse response);
	public abstract String delete(HttpServletRequest request, HttpServletResponse response);
	public abstract String index(HttpServletRequest request, HttpServletResponse response);
	public abstract String edit(HttpServletRequest request, HttpServletResponse response);
	
}