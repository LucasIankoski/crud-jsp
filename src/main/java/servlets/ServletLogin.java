package servlets;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;

/**
 * Servlet implementation class ServletLogin
 */
@WebServlet(urlPatterns = {"/principal/ServletLogin", "/ServletLogin"})
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletLogin() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String url = request.getParameter("url");

		if (login != null && !login.isEmpty() && senha != null && !senha.isEmpty()) {
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setLogin(login);
			modelLogin.setSenha(senha);

			if (modelLogin.getLogin().equalsIgnoreCase("admin") && modelLogin.getSenha().equalsIgnoreCase("admin")) {
				request.getSession().setAttribute("usuario", modelLogin.getLogin());
				
				if(url == null || url.equals("null")) {
					url = "principal/home.jsp";
				}
				
				RequestDispatcher redirecionamento = request.getRequestDispatcher(url);
				redirecionamento.forward(request, response);
			} else {
				RequestDispatcher redirecionamento = request.getRequestDispatcher("/index.jsp");
				request.setAttribute("mensagemErroLogin", "Login ou senha incorretos!");
				redirecionamento.forward(request, response);
			}

		} else {
			RequestDispatcher redirecionamento = request.getRequestDispatcher("index.jsp");
			request.setAttribute("mensagemErroLogin", "Login ou senha incorretos!");
			redirecionamento.forward(request, response);
		}

	}

}
