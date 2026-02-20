package com.adrian.onepiece.controladores.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adrian.onepiece.dtos.JwtDTO;
import com.adrian.onepiece.dtos.LoginDTO;
import com.adrian.onepiece.security.jwt.JwtTokenProvider;

@RestController
@RequestMapping("/v1")
public class AuthController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider tokenProvider;

	/*
	 * POST /api/auth/login con body: { "username": "akainu", "password": "1234" }
	 */
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginDTO loginRequest) {
		  System.out.println("✅ Llegó al controlador de login");

		// PASO 1: Autenticar al usuario con username y password
		// Si las credenciales son incorrectas, esto lanza una BadCredentialsException
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsuario(), loginRequest.getPasswd()));

		// PASO 2: Establecer la autenticación en el contexto de seguridad
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// PASO 3: Generar el token JWT
		String jwt = tokenProvider.generateToken(authentication);

		// PASO 4: Obtener los roles del usuario autenticado
		List<String> roles = new ArrayList<>();
		for (GrantedAuthority authority : authentication.getAuthorities()) {
		    roles.add(authority.getAuthority());
		}

		// PASO 5: Devolver la respuesta con el token y datos del usuario
		return ResponseEntity.ok(new JwtDTO(jwt, // El token JWT
				loginRequest.getUsuario(), // El username
				roles // Los roles
		));
	}

}
