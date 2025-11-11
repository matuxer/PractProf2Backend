package com.example.ferreteria.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    // Clave secreta para firmar el token (cambiar en producción y mover a variables de entorno)
    private static final String SECRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";
    
    // Tiempo de expiración del token: 24 horas
    private static final long JWT_EXPIRATION = 86400000; // 24 horas en milisegundos

    /**
     * Genera un token JWT para un cliente
     */
    public String generateToken(Long clienteId, String correo) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("clienteId", clienteId);
        claims.put("correo", correo);
        
        return createToken(claims, correo);
    }

    /**
     * Crea el token con los claims y el subject
     */
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION))
                .signWith(getSignKey())
                .compact();
    }

    /**
     * Obtiene la clave de firma
     */
    private SecretKey getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Extrae el correo del token
     */
    public String extractCorreo(String token) {
        return extractClaims(token).getSubject();
    }

    /**
     * Extrae el ID del cliente del token
     */
    public Long extractClienteId(String token) {
        return extractClaims(token).get("clienteId", Long.class);
    }

    /**
     * Extrae todos los claims del token
     */
    private Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * Valida si el token es válido
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Verifica si el token ha expirado
     */
    public boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }
}
