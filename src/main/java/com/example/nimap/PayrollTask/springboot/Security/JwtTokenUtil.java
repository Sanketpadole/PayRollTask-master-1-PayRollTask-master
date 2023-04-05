package com.example.nimap.PayrollTask.springboot.Security;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
@PropertySource(value = { "classpath:application.properties" })
public class JwtTokenUtil implements Serializable {
	private static final long serialVersionUID = -2550185165626007488L;

	public static final long JWT_TOKEN_VALIDITY_FOR_ACCESS_TOKEN = 900;// 5*60

	public static final long JWT_TOKEN_VALIDITY_FOR_REFRESH_TOKEN = 604800;// for one day = 24 * 60 * 60; for
																			// seven=7*24*60*60;

	@Value("${jwt.secret}")
	private String secret;

	public String getEmailFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	// retrieve username from jwt token

	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	// retrieve expiration date from jwt token

	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	// for retrieveing any information from token we will need the secret key

	public Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

//function use for get token type e.g. refresh,access

	public String getTokenType(String token) throws JwtException {
		Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		return (String) claims.get("type");

	}

	// check if the token has expired

	public Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	// generate token for user

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, userDetails.getUsername());
	}

	// while creating the token -
	// 1. Define claims of the token, like Issuer, Expiration, Subject, and the ID
	// 2. Sign the JWT using the HS512 algorithm and secret key.
	// 3. According to JWS Compact
	// Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
	// compaction of the JWT to a URL-safe string

	public String doGenerateToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).claim("type", "access").setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY_FOR_ACCESS_TOKEN * 1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	public String refreshToken(String accessToken, UserDetails userDetails) {
		final Date createdDate = new Date();
		final Date expirationDate = calculateExpirationDate(createdDate);

		final Claims claims = getAllClaimsFromToken(accessToken);
		claims.setIssuedAt(createdDate);
		claims.setExpiration(expirationDate);
		return Jwts.builder().setClaims(claims).claim("type", "refresh").setSubject(userDetails.getUsername())
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	// validate token

	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
	}

	public Date calculateExpirationDate(Date createdDate) {
		return new Date(createdDate.getTime() + JWT_TOKEN_VALIDITY_FOR_REFRESH_TOKEN * 1000);
	}

	public boolean canTokenBeRefreshed(String token) {
		return (!isTokenExpired(token) || ignoreTokenExpiration(token));
	}

	private boolean ignoreTokenExpiration(String token) {
		return false;
	}

}
