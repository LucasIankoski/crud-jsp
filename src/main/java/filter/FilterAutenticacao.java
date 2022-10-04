package filter;

import java.io.IOException;

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

/**
 * Servlet Filter implementation class FilterAj
 */
@WebFilter(urlPatterns = { "/principal/*" })
public class FilterAutenticacao implements Filter {

	/**
	 * Default constructor.
	 */
	public FilterAutenticacao() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpSession httpSession = httpReq.getSession();
		String usuarioLogado = (String) httpSession.getAttribute("usuario");
		String urlParaAutenticacao = httpReq.getServletPath();
		
		System.out.println(urlParaAutenticacao);

		if (usuarioLogado == null && !urlParaAutenticacao.equalsIgnoreCase("/principal/ServletLogin")) {
			RequestDispatcher redirecionamento = request.getRequestDispatcher("/index.jsp?url=" + urlParaAutenticacao);
			request.setAttribute("mensagemErroLogin", "Por favor, efetue o seu login");
			redirecionamento.forward(request, response);
			return;
		} else {
			chain.doFilter(request, response);
		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
