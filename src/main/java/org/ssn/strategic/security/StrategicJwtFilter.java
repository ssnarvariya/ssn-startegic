package org.ssn.strategic.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.ssn.strategic.security.service.StrategicUserDetailService;
import org.ssn.strategic.security.util.JwtUtil;


@Component
public class StrategicJwtFilter extends OncePerRequestFilter{
	
	@Autowired
	StrategicUserDetailService userDetailService;
	
	@Autowired
	JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String authHeader = request.getHeader("Authorization");
		
		String username = null;
		String jwtToken = null;
		
		if(authHeader != null && authHeader.startsWith("Bearer ")){
			jwtToken =  authHeader.substring(7);
			username = jwtUtil.extractUserName(jwtToken);
		}
		
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
			UserDetails userDetails = userDetailService.loadUserByUsername(username);
			if(jwtUtil.validateUsername(jwtToken,userDetails)){
				UsernamePasswordAuthenticationToken upaToken = new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
				upaToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(upaToken);
			}
		}
		filterChain.doFilter(request,response);
	}

}
