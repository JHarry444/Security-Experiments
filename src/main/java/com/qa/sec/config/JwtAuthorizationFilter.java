package com.qa.sec.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

	private String jwtSecret;
	private UserDetailsService userDetailsService;

	public JwtAuthorizationFilter(AuthenticationManager authenticationManager, String jwtSecret,
			UserDetailsService userDetailsService) {
		super(authenticationManager);
		this.jwtSecret = jwtSecret;
		this.userDetailsService = userDetailsService;
	}

	private UsernamePasswordAuthenticationToken parseToken(HttpServletRequest request) {
		String token = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (token != null && token.startsWith("Bearer ")) {
			String claims = token.replace("Bearer ", "");
			try {
				Jws<Claims> claimsJws = Jwts.parser().setSigningKey(jwtSecret.getBytes()).parseClaimsJws(claims);

				String username = claimsJws.getBody().getSubject();

				if ("".equals(username) || username == null) {
					return null;
				}

				UserDetails user = this.userDetailsService.loadUserByUsername(username);

				return new UsernamePasswordAuthenticationToken(username, null, user.getAuthorities());
			} catch (JwtException exception) {
				System.out.println("Some exception : {} failed : {}");
				System.out.println(token);
				System.out.println(exception.getMessage());
			}
		}

		return null;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		UsernamePasswordAuthenticationToken authentication = parseToken(request);

		if (authentication != null) {
			SecurityContextHolder.getContext().setAuthentication(authentication);
		} else {
			SecurityContextHolder.clearContext();
		}

		filterChain.doFilter(request, response);
	}

}
