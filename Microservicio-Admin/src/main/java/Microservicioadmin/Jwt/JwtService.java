package Microservicioadmin.Jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	private static final String SECRETKEY= "0249";
	
	public String  getToken (UserDetails user) {
		return getToken(new HashMap<>(),user);
	}
	
	private String getToken(Map<String,Object> extraClaims, UserDetails user) {
		return Jwts
				.builder()
				.setClaims(extraClaims)
				.setSubject(user.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*15))
				.signWith(getKey(), SignatureAlgorithm.HS256)
				.compact();
	}
	
	private Key getKey() {
		byte[] keyBytes= Decoders.BASE64.decode(SECRETKEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}