package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import connection.DB;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = { "/principal/*" })
public class FilterAutenticacao implements Filter {

	private static Connection connection;

	public FilterAutenticacao() {

	}

	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {

			HttpServletRequest httpReq = (HttpServletRequest) request;
			HttpSession httpSession = httpReq.getSession();
			String usuarioLogado = (String) httpSession.getAttribute("usuario");
			String urlParaAutenticacao = httpReq.getServletPath();

			System.out.println(urlParaAutenticacao);

			if (usuarioLogado == null && !urlParaAutenticacao.equalsIgnoreCase("/principal/ServletLogin")) {
				RequestDispatcher redirecionamento = request
						.getRequestDispatcher("/index.jsp?url=" + urlParaAutenticacao);
				request.setAttribute("mensagemErroLogin", "Por favor, efetue o seu login");
				redirecionamento.forward(request, response);
				return;
			} else {
				chain.doFilter(request, response);
			}

			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		connection = DB.getConnection();
	}

}
