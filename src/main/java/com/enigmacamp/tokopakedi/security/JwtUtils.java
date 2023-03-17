package com.enigmacamp.tokopakedi.security;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtUtils {

    @Value("${enigma.jwt-secret}")
    private String jwtSecret;

    @Value("${enigma.jwt-expiration}")
    private Integer jwtExpiration;

    public  String generateTokenFromUsername(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUsernameByToken(String token){
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String token){
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e){
            log.error("Invalid JWT Token: {}", e.getMessage());
        } catch (ExpiredJwtException e){
            log.error("JWT Token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e){
            log.error("JWT Token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e){
            log.error("JWT Token string is empty: {}", e.getMessage());
        }
        return false;
    }

}
