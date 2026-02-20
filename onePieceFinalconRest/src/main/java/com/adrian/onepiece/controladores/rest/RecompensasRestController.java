package com.adrian.onepiece.controladores.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adrian.onepiece.dtos.RecompensaDTO;
import com.adrian.onepiece.servicio.interfaces.IRecompensasService;

@RestController
@RequestMapping("/v1")
public class RecompensasRestController {
	
	@Autowired
	private IRecompensasService recompensasService;
	
	// Obtener todos las recompensas
		@GetMapping("/recompensas")
		public Iterable<RecompensaDTO> listarTodasRecompensas() {
			return recompensasService.obtenerTodasRecompensas();
		}
		

}
