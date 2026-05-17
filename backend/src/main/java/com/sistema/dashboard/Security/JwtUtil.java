package com.sistema.dashboard.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component 
public class JwtUtil {

    private static final javax.crypto.SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();

    private static final long JWT_TOKEN_VALIDITY = 8 * 60 * 60 * 1000;

    public String generarToken(String username, String rol) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("rol", rol);
    
    return Jwts.builder()
            .claims(claims)                                  
            .subject(username)                                   
            .issuedAt(new Date(System.currentTimeMillis()))      
            .expiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY)) 
            .signWith(SECRET_KEY)                               
            .compact();
}


    public String extraerUsername(String token) {
        return extraerClaim(token, Claims::getSubject);
    }

    public Date extraerExpiracion(String token) {
        return extraerClaim(token, Claims::getExpiration);
    }


    public <T> T extraerClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extraerTodosLosClaims(token);
        return claimsResolver.apply(claims);
    }


    private Claims extraerTodosLosClaims(String token) {
    return Jwts.parser()           
            .verifyWith((javax.crypto.SecretKey) SECRET_KEY) 
            .build()
            .parseSignedClaims(token)    
            .getPayload();               
}

    private Boolean tokenExpirado(String token) {
        return extraerExpiracion(token).before(new Date());
    }

    public Boolean validarToken(String token, String username) {
        final String tokenUsername = extraerUsername(token);
        return (tokenUsername.equals(username) && !tokenExpirado(token));
    }
}