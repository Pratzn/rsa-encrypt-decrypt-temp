package rsa.crytography;

import java.util.Date;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.java.Log;

@Log
@Component
@SuppressWarnings("deprecation")
public class JWTService {

	public static void main(String[] args) {
		JWTService jwt = new JWTService();
		String token = jwt.generateToken("prat");
		log.info("token: "+token);
		
	}

	public String generateToken(String iUser) {
		String token = Jwts.builder()
				.setHeaderParam("typ", "JWT")
				.setSubject(iUser)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setIssuer("SSO")
				.signWith(SignatureAlgorithm.HS256, Base64.encodeBase64String(Base64.encodeBase64String("secretsecretsecretsecretsecretsecret".getBytes()).getBytes()))
				.compact();
		log.info("token: " + token);
		return token;
	}

	public String verifyToken(String token) {
		try{
			Claims claims = Jwts.parser() // (1)
					.setSigningKey(Base64.encodeBase64String(Base64.encodeBase64String("secretsecretsecretsecretsecretsecret".getBytes()).getBytes())) 
					.parseClaimsJws(token).getBody();	
			return claims.toString();
		} catch (JwtException ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
