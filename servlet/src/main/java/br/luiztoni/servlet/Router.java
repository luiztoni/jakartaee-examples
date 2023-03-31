package br.luiztoni.servlet;

import br.luiztoni.servlet.config.Controller;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/*")
public class Router extends HttpServlet {
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) {
	
		String[] split = request.getRequestURI().split("/");
		String path = split[2];
		String action = split[3].split("\\.")[0];
		try {
			Class<?> type = Class.forName(getFullControllerName(path));
			Controller controller = Controller.class.cast(type.getDeclaredConstructor().newInstance());
			String page = controller.execute(request, response, action);
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Ocorreu uma exceção - controller : "+path);
			System.out.println("Ocorreu uma exceção - action : "+action);
		}
	}
	
	private String getFullControllerName(String path) {
		String basePackage = "br.luiztoni.servlet.";
		String controllerPackage = basePackage.concat(path).concat(".");
		String controllerName = path.substring(0, 1).toUpperCase().concat(path.substring(1)).concat("Controller");
		String fullControllerName = controllerPackage.concat(controllerName);
		return fullControllerName;
	}
}