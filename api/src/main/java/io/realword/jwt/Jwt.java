package io.realword.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.realword.model.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class Jwt {

  private final Key key;
  private final long expirationTime;

  public Jwt(@Value("${jwt.secret}") String secretKey, @Value("${jwt.expiration}") long expirationTime) {
    this.key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    this.expirationTime = expirationTime;
  }

  public String generateToken(User user) {
    Date now = new Date();
    Date expiryDate = new Date(now.getTime() + expirationTime);

    return Jwts.builder()
      .setSubject(user.getEmail())
      .setIssuedAt(now)
      .setExpiration(expiryDate)
      .signWith(key, SignatureAlgorithm.HS512)
      .compact();
  }

  public Claims parseToken(String token) {
    return Jwts.parserBuilder()
      .setSigningKey(key)
      .build()
      .parseClaimsJws(token)
      .getBody();
  }
}
