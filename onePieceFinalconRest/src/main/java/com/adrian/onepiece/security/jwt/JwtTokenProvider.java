package com.adrian.onepiece.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenProvider {
	// Inyectamos la clave secreta desde application.properties
    @Value("${jwt.secret}")
    private String jwtSecret;

    // Inyectamos el tiempo de expiración desde application.properties
    @Value("${jwt.expiration}")
    private long jwtExpirationMs;
    
    /**
     * MÉTODO 1: Genera un token JWT con el username y fecha de expiración 
     * Cuando el usuario hace login correctamente
     */
    public String generateToken(Authentication authentication) {
        // Obtenemos el usuario autenticado
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        
        // Fecha actual
        Date now = new Date();
        // Fecha de expiración (ahora + 24 horas)
        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);
        
        // Creamos la clave para firmar (a partir de nuestro secret)
        SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
        
        // Construimos el JWT
        return Jwts.builder()
                .subject(userPrincipal.getUsername())  // Guardamos el usuario en el "subject"
                .issuedAt(now)                         // Cuándo se creó
                .expiration(expiryDate)                // Cuándo expira
                .signWith(key)                         // Lo firmamos con nuestra clave
                .compact();                            // Lo convertimos a String
    }

    /**
     * MÉTODO 2: Obtiene el username del token JWT
     *  Se usa cuando llega una petición con JWT 
     *  y queremos saber quién es el usuario
     */
    public String getUsernameFromToken(String token) {
        // Creamos la clave para verificar
        SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
        
        // Parseamos el token y extraemos los claims (datos)
        Claims claims = Jwts.parser()
                .verifyWith(key)                    // Verificamos con nuestra clave
                .build()
                .parseSignedClaims(token)           // Parseamos el token
                .getPayload();                      // Obtenemos el payload
        
        // Devolvemos el "subject" que es donde guardamos el username
        return claims.getSubject();
    }

    /**
     * MÉTODO 3: Valida el token JWT
     * 
     * Se usa En CADA petición que recibimos para verificar que el token es válido
     * ¿Qué hace? Comprueba:
     *   - Que la firma sea correcta
     *   - Que no esté expirado
     *   - Que no esté malformado
     */
    public boolean validateToken(String token) {
        try {
            // Creamos la clave para verificar
            SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
            
            // Intentamos parsear el token
            Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token);
            
            // Si no lanza excepción, el token es válido
            return true;
            
        } catch (SecurityException ex) {
            System.err.println("Firma JWT inválida");
        } catch (MalformedJwtException ex) {
            System.err.println("Token JWT inválido");
        } catch (ExpiredJwtException ex) {
            System.err.println("Token JWT expirado");
        } catch (UnsupportedJwtException ex) {
            System.err.println("Token JWT no soportado");
        } catch (IllegalArgumentException ex) {
            System.err.println("JWT claims string está vacío");
        }
        
        // Si llegamos aquí, el token NO es válido
        return false;
    }
}
