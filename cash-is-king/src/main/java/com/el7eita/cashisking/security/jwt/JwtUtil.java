package com.el7eita.cashisking.security.jwt;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@SuppressWarnings("deprecation")
@Component
public class JwtUtil {
	private String SECRET_KEY = "123qw123ASppL822seazuiaisu10283budiasbd9812q";
	 public String extractUsername(String token) {
	        return extractClaim(token, Claims::getSubject);
	    }

	    public Date extractExpiration(String token) {
	        return extractClaim(token, Claims::getExpiration);
	    }

	    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
	        final Claims claims = extractAllClaims(token);
	        return claimsResolver.apply(claims);
	    }
	    private Claims extractAllClaims(String token) {
	        return Jwts.parser().build().parseSignedClaims(token).getPayload();
	    }

	    private Boolean isTokenExpired(String token) {
	        return extractExpiration(token).before(new Date());
	    }

	    public String generateToken(UserDetails userDetails) {
	        Map<String, Object> claims = new HashMap<>();
	        return createToken(claims, userDetails.getUsername());
	    }
	    
	    private Key getSignKey() { 
	        byte[] keyBytes= Decoders.BASE64.decode(SECRET_KEY); 
	        return Keys.hmacShaKeyFor(keyBytes); 
	    } 

		private String createToken(Map<String, Object> claims, String subject) {

	        return Jwts.builder().claims(claims).subject(subject).issuedAt(new Date(System.currentTimeMillis()))
	                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
	                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
	    }

	    public Boolean validateToken(String token, UserDetails userDetails) {
	        final String username = extractUsername(token);
	        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	    }

}
