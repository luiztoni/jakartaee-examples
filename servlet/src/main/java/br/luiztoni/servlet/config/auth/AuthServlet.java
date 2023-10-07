package br.luiztoni.servlet.config.auth;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet({"/auth/*", "/login", "/register"})
public class AuthServlet extends HttpServlet {

	private static final long serialVersionUID = -535527126349259049L;

	private static final Logger LOGGER = Logger.getLogger(AuthServlet.class.getName());

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.log(Level.INFO, "Acessando AuthServlet");
		boolean isLoginPage = request.getRequestURI().endsWith("login");
		boolean isRegisterPage = request.getRequestURI().endsWith("register");
		boolean isLogout = request.getRequestURI().endsWith("logout");
		boolean success = false;
		String errorPage = null;
		if (isLoginPage) {
			success = this.login(request, response);
			errorPage = "/views/login.jsp";
		}
		if (isRegisterPage) {
			success = this.register(request, response);
			errorPage = "/views/register.jsp";
		}
		
		if (isLogout) {
			HttpSession session = request.getSession(false);
			if (session != null) {
				session.invalidate();
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/views/login.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		if (success) {
			response.sendRedirect("/admin/product/index");
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher(errorPage);
			request.setAttribute("message", "ocorreu um erro!");
			dispatcher.include(request, response);
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean isLogin = request.getRequestURI().endsWith("login.jsp");
		boolean isRegister = request.getRequestURI().endsWith("register.jsp");
		String page = "";
		if (isLogin) {
			page = "/views/login.jsp";
		}
		if (isRegister) {
			page = "/views/register.jsp";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.include(request, response);
	}
	
	private boolean register(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.log(Level.INFO, "Acessando AuthServlet#register");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		UserRepository repository = new UserRepository();
		User otherUser = repository.findByEmail(email);
		if (Objects.nonNull(otherUser)) {
			return false;
		}
		password = Password.encode(password, "abc");
		User newUser = new User();
		newUser.setEmail(email);
		newUser.setPassword(password);
		repository.create(newUser);
		HttpSession session = request.getSession(true);
		session.setAttribute("user", newUser);
		return true;
	}
	
	private boolean login(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.log(Level.INFO, "Acessando AuthServlet#login");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		UserRepository repository = new UserRepository();
		
		User user = repository.findByEmail(email);
		if (Objects.isNull(user)) {
			return false;
		}
		if (Password.verify(password, user.getPassword(), "abc")) {
			HttpSession session = request.getSession(true);
			session.setAttribute("user", user);
			return true;
		}
		return false;
	}
}
