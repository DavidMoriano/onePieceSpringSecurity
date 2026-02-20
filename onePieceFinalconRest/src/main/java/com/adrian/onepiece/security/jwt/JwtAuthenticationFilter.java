package com.adrian.onepiece.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.adrian.onepiece.security.DetallesUsuarioService;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private DetallesUsuarioService customUserDetailsService;
    
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        // No aplicar el filtro JWT en endpoints de autenticación públicos
        return path.startsWith("/v1/auth/") || 
               path.startsWith("/v2/auth/") ||
               path.equals("/error");
    }

    /**
     * Este método se ejecuta UNA VEZ por cada petición HTTP
     * 
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                    HttpServletResponse response, 
                                    FilterChain filterChain) throws ServletException, IOException {
    	System.out.println("🔍 JWT FILTER - URI: " + request.getRequestURI());
        System.out.println("🔍 JWT FILTER - Method: " + request.getMethod());
        
    	try {
            // PASO 1: Intentar obtener el JWT del header de la petición
            String jwt = getJwtFromRequest(request);

            // PASO 2: Si hay token Y es válido, procesarlo
            if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
                
                // PASO 3: Extraer el username del token
                String username = tokenProvider.getUsernameFromToken(jwt);

                // PASO 4: Cargar los detalles completos del usuario desde la BD
                // (necesitamos los roles para saber qué puede hacer)
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

                // PASO 5: Crear el objeto de autenticación de Spring Security
                UsernamePasswordAuthenticationToken authentication = 
                    new UsernamePasswordAuthenticationToken(
                        userDetails,           // El usuario
                        null,                  // Las credenciales (no las necesitamos aquí)
                        userDetails.getAuthorities()  // Los roles/permisos
                    );
                
                // Añadimos detalles extra de la petición (IP, sesión, etc.)
                authentication.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
                );

                // PASO 6: Establecer la autenticación en el contexto de Spring Security
                // Esto le dice a Spring "este usuario está autenticado"
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception ex) {
            logger.error("No se pudo establecer la autenticación del usuario", ex);
        }

        // PASO 7: Continuar con la cadena de filtros (siguiente filtro o el controller)
        filterChain.doFilter(request, response);
    }

    /**
     * Método auxiliar para extraer el JWT del header Authorization
     * 
     * ¿Qué busca? Un header así: "Authorization: Bearer eyJhbGc..."
     * ¿Qué devuelve? Solo el token: "eyJhbGc..."
     */
    private String getJwtFromRequest(HttpServletRequest request) {
        // Obtenemos el header "Authorization"
        String bearerToken = request.getHeader("Authorization");
        
        // Verificamos que existe y empieza con "Bearer "
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            // Quitamos "Bearer " del inicio (los primeros 7 caracteres)
            return bearerToken.substring(7);
        }
        
        // No hay token
        return null;
    }
}