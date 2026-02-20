package com.adrian.onepiece.controladores.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adrian.onepiece.dtos.PirataDTO;
import com.adrian.onepiece.servicio.interfaces.IPiratasService;

@RestController
@RequestMapping("/v1")
public class PiratasRestController {
	
	@Autowired
	private IPiratasService piratasService;
	
	// Obtener todos los piratas
	@GetMapping("/piratas")
	public Iterable<PirataDTO> listarTodosPiratas() {
		return piratasService.obtenerTodosPiratas();
	}
	
	

}
