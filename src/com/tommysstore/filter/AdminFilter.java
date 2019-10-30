package com.tommysstore.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.tommysstore.constant.UserRole;
import com.tommysstore.domain.User;

public class AdminFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		User loginUser = (User) session.getAttribute("loginUser");
		UserRole role = loginUser.getRole();
		
		if(!role.equals(UserRole.ADMIN)) {
			
			request.setAttribute("message", "Page cannot be accessed");
			request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
			
			return;
		}
		
		chain.doFilter(request, response);
	}
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

}