package com.daw.onepiece.repositorios;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.daw.onepiece.dtos.IslaDTO;
import com.daw.onepiece.entities.IslaEntity;

public interface IslaRepository extends CrudRepository<IslaEntity, Integer> {
	
	@Query("""
			SELECT new com.daw.onepiece.dtos.IslaDTO(
				i.id,
				i.nombre
			)
			FROM IslaEntity i			
			""")
	ArrayList<IslaDTO> desplegableIslas();
}
