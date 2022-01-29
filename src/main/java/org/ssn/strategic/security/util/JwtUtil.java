package org.ssn.strategic.security.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil  {

	private final String SECRET_KEY = "secret";
	
	public String generateToken(UserDetails userDetail){
		Map<String,Object> lClaims = new HashMap<String, Object>();
		return createToken(lClaims, userDetail.getUsername());
	}
	
	public String extractUserName(String pToken){
		return extractClaim(pToken, Claims::getSubject);
	}
	
	public Date getExpiration(String pToken){
		Date t = extractClaim(pToken, Claims::getExpiration);
		return t;
	}
	
	public boolean isTokenExpired(String pToken){
		return getExpiration(pToken).before(new Date());
	}
	
	public <T> T extractClaim(String pToken, Function<Claims, T> pClaimsResolver){
		final Claims lClaims = extractAllClaims(pToken);
		return pClaimsResolver.apply(lClaims);
	}
	
	public Boolean validateUsername(String pToken, UserDetails pUserDetails){
		final String userName = extractUserName(pToken);
		boolean isValid = userName.equals(pUserDetails.getUsername());
		//boolean isTokenValid = isTokenExpired(pToken);
		return isValid;
	}
	
	private String createToken(Map<String, Object> pClaims , String subject){
		return Jwts.builder().setClaims(pClaims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}
	
	private Claims extractAllClaims(String pToken){
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(pToken).getBody();
	}
	
}
