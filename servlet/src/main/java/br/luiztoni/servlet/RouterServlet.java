package br.luiztoni.servlet;

import br.luiztoni.servlet.config.Controller;
import br.luiztoni.servlet.config.auth.AuthServlet;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet({"/admin/*", "/"})
public class RouterServlet extends HttpServlet {

	private static final Logger LOGGER = Logger.getLogger(RouterServlet.class.getName());

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) {
		String[] split = {"/","admin","product", "index"};
		if (!request.getRequestURI().equals("/")) {
			split = request.getRequestURI().split("/");
		}
		String path = split[2];
		String action = split[3].split("\\.")[0];
		try {
			Class<?> type = Class.forName(getFullControllerName(path));
			Controller controller = Controller.class.cast(type.getDeclaredConstructor().newInstance());
			String page = controller.execute(request, response, action);
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Ocorreu uma exceção - controller : {0} ", path);
			LOGGER.log(Level.SEVERE, "Ocorreu uma exceção - action : {0} ", action);
			LOGGER.log(Level.SEVERE, e.getMessage());
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
