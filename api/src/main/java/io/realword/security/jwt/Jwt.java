package io.realword.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.realword.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Jwt {

  private final Key key; // ?
  private final SecretKey secretKey;
  private final long expirationTime;

  public Jwt(@Value("${jwt.secret}") String secretKey, @Value("${jwt.expiration}") long expirationTime) {
    this.key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes());
    this.expirationTime = expirationTime;
  }

  public String generateToken(User user) {
    Date now = new Date();
    Date expiryDate = new Date(now.getTime() + expirationTime);

    return Jwts.builder()
      .setSubject(user.getEmail())
      .setIssuedAt(now)
      .setExpiration(expiryDate)
      .signWith(secretKey, SignatureAlgorithm.HS512)
      .compact();
  }

  public Claims parseToken(String token) {
    return Jwts.parserBuilder()
      .setSigningKey(secretKey)
      .build()
      .parseClaimsJws(token)
      .getBody();
  }

  public boolean validateToken(String token) {
    System.out.println("ete");
    try {
      Jws<Claims> claims = Jwts.parserBuilder()
        .setSigningKey(secretKey)
        .build()
        .parseClaimsJws(token);
      System.out.println("claims.getBody() = " + claims.getBody());
      return !claims.getBody().getExpiration().before(new Date());
    } catch (Exception e) {
      System.out.println(e);
      return false;
    }
  }

  public Authentication getAuthentication(String token) {
    Claims claims = parseToken(token);

    String username = claims.getSubject();
    // roles를 가져오되, null이거나 비어있으면 빈 리스트를 사용
    List<String> roles = new ArrayList<>();

    List<SimpleGrantedAuthority> authorities = roles.stream()
      .map(SimpleGrantedAuthority::new)
      .collect(Collectors.toList());

    UserDetails userDetails = new org.springframework.security.core.userdetails.User(username, "", authorities);
    System.out.println("username = " + username);

    return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
  }
}
