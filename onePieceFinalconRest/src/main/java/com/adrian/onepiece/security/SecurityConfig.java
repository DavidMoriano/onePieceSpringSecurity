package com.adrian.onepiece.security;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.adrian.onepiece.security.jwt.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private DetallesUsuarioService userDetailsService;

	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	/**
	 * Define el encoder de contraseñas. BCrypt es el recomendado por Spring
	 * Security.
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Configura cómo se autentican los usuarios.
	 */
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	/**
	 * Expone el AuthenticationManager como bean.
	 */
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}


	/**
		 * Configuración principal de seguridad. Aquí definimos qué URLs están
		 * protegidas y qué roles pueden acceder. VISTAS THYMELEAF
		 */
	@Bean
	@Order(1) // Se evalúa primero
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http.securityMatcher("/**") // Aplica a todas las rutas que no sean APIs
	        .securityMatcher(request -> {
	            String uri = request.getRequestURI();
	            return !uri.startsWith("/v1") && !uri.startsWith("/v2"); // Excluye /v1/* y /v2/*
	        })// Excluye /v1/* y /v2*
	        .csrf(csrf -> csrf.disable()) // Deshabilitamos CSRF para APIs REST
	        .authorizeHttpRequests(auth -> auth
	                               // URLs públicas (sin autenticación) y recursos estáticos
	                               .requestMatchers("/", "/login", "/accesoDenegado", "/css/**").permitAll()

	                               // Home todos tienen acceso si estan logueados
	                               .requestMatchers("/home/**").authenticated()

	                               // CRUD de Notas - solo DIRECTOR y PROFESOR
	                               .requestMatchers("/piratas/**").hasAnyAuthority("almirante", "capitan")

	                               // CRUD de Faltas - solo DIRECTOR y PROFESOR
	                               .requestMatchers("/tripulaciones/**").hasAnyAuthority("almirante", "vicealmirante")

	                               // CRUD de Matriculaciones - solo DIRECTOR y SECRETARIO
	                               .requestMatchers("/recompensas/**").hasAnyAuthority("almirante", "capitan")

	                               // Resto de recursos - solo DIRECTOR
	                               .anyRequest().hasAuthority("director"))
	        .formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/home", true).permitAll())
	        .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/login?logout").permitAll())
	        .exceptionHandling(exception -> exception.accessDeniedPage("/accesoDenegado"));

	    return http.build();
	}

	@Bean
	@Order(2)
	public SecurityFilterChain apiSecurityFilterChain(HttpSecurity http) throws Exception {
	    http.securityMatcher("/v1/**", "/v2/**") // Aplica a ambas versiones
	        .csrf(csrf -> csrf.disable())
	        //SESIONES: Política STATELESS (sin sesiones en servidor)
	        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	        .authorizeHttpRequests(auth -> auth
	                               // Endpoints públicos
	                               .requestMatchers("/v1/login/**", "/v2/login/**").permitAll().requestMatchers("/error").permitAll()

	                               // Endpoints protegidos por roles
	                               .requestMatchers("/v1/piratas/**", "/v2/piratas/**").hasAnyAuthority("almirante", "capitan")
	                               .requestMatchers("/v1/tripulaciones/**", "/v2/tripulaciones/**").hasAnyAuthority("almirante", "vicealmirante")
	                               .requestMatchers("/v1/recompensas/**", "/v2/recompensas/**").hasAnyAuthority("almirante", "capitan")

	                               // Cualquier otra petición API requiere autenticación
	                               .anyRequest().authenticated())
	        //MANEJO DE ERRORES
	        .exceptionHandling(exceptions -> exceptions
	                .authenticationEntryPoint((request, response, authException) -> {
	                    response.setContentType("application/json;charset=UTF-8");
	                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
	                    response.getWriter().write(
	                        "{\"timestamp\":\"" + LocalDateTime.now() + "\"," +
	                        "\"status\":401," +
	                        "\"error\":\"No autorizado\"," +
	                        "\"message\":\"Token no válido o no proporcionado\"}"
	                    );
	                })
	                .accessDeniedHandler((request, response, accessDeniedException) -> {
	                    response.setContentType("application/json;charset=UTF-8");
	                    response.setStatus(HttpStatus.FORBIDDEN.value());
	                    response.getWriter().write(
	                        "{\"timestamp\":\"" + LocalDateTime.now() + "\"," +
	                        "\"status\":403," +
	                        "\"error\":\"Acceso denegado\"," +
	                        "\"message\":\"No tienes permisos para acceder a este recurso\"}"
	                    );
	                })
	            )
	        //Añadimos nuestro filtro ANTES del filtro de autenticación estándar
	        // Esto significa que nuestro filtro se ejecutará primero
	        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

	    return http.build();
	}
}
