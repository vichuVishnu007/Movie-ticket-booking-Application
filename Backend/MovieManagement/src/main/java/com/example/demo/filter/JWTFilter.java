package com.example.demo.filter;

import com.example.demo.model.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.catalina.connector.RequestFacade;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.GenericFilterBean;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
public class JWTFilter extends GenericFilterBean
{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpServletResponse httpRes = (HttpServletResponse) response;

		String authHeader = httpReq.getHeader("authorization");
		System.out.println(authHeader);

		if(authHeader ==null || !authHeader.startsWith("Bearer")) {
			throw new ServletException("Missing or invalid authentication header");
		}

		String jwtToken = authHeader.substring(7);

		System.out.println(jwtToken);

		Claims claims = Jwts.parser().setSigningKey("secret key").parseClaimsJws(jwtToken).getBody();

		httpReq.setAttribute("username", claims);

		final String requestURI = ((RequestFacade) request).getRequestURI();

		setAuthenticationContext(jwtToken, httpReq);

		chain.doFilter(request, response);

	}

	private void setAuthenticationContext(String token, HttpServletRequest request) {
		Role role = getUserDetails(token);

		UsernamePasswordAuthenticationToken
				authentication = new UsernamePasswordAuthenticationToken(role, null, role.getAuthorities());

		authentication.setDetails(
				new WebAuthenticationDetailsSource().buildDetails(request));


		SecurityContextHolder.getContext().setAuthentication(authentication);

		System.out.println("*******"+SecurityContextHolder.getContext().getAuthentication()+"******");
	}

	private Role getUserDetails(String token) {
		Role role = new Role();
		Claims claims =  Jwts.parser().setSigningKey("secret key").parseClaimsJws(token).getBody();
		String subject = (String) claims.get(Claims.SUBJECT);
		String roles = (String) claims.get("role");
		System.out.println("sub:"+subject);
		System.out.println("role:"+roles);
		role.addUsername(subject);
		role.addRole(roles);
		System.out.println("****"+role.getUsername()+role.getRole()+role.getAuthorities()+"****");
		return role;
	}





}




