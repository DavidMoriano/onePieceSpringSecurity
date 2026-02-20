package com.adrian.onepiece.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.adrian.onepiece.entities.RoleEntity;
import com.adrian.onepiece.entities.UserEntity;
import com.adrian.onepiece.repositorios.UserRepository;

@Service
public class DetallesUsuarioService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Buscar el usuario en la base de datos
		UserEntity user = userRepository.findByUserName(username);
		if (user == null)
			throw new UsernameNotFoundException("Usuario no encontrado: " + username);

		// Convertir los roles a GrantedAuthority
		Set<GrantedAuthority> authorities = new HashSet<>();

		for (RoleEntity role : user.getRoles()) {
		    authorities.add(new SimpleGrantedAuthority(role.getUserRole()));
		}
		
		// Devolver un objeto UserDetails de Spring Security
        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getUserPasswd(),
                authorities
        );

	}

}
