package com.tommysstore.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tommysstore.domain.User;

public class SessionFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession();
		
		User loginUser = (User) session.getAttribute("loginUser");
		String path = httpRequest.getServletPath();
		
		if(path.equals("/login") && loginUser != null) {
			
			request.setAttribute("message", "You are already logged in");
			request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
		}
		
		else if((path.startsWith("/admin") || path.startsWith("/logout")) && loginUser == null) {
			
			request.setAttribute("executeNextFilter", false);
			httpResponse.sendRedirect("/TommysStore/login");
			
			return;
		}
		
		else if(path.startsWith("/customer/cart/checkout") && loginUser == null) {
			
			request.setAttribute("executeNextFilter", false);
			session.setAttribute("path", path);
			httpResponse.sendRedirect("/TommysStore/login");
			
			return;
		}
		
		request.setAttribute("executeNextFilter", true);
		chain.doFilter(request, response);
	}
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

}