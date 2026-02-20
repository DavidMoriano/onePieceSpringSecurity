package com.adrian.onepiece.controladores.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adrian.onepiece.dtos.TripulacionDTO;
import com.adrian.onepiece.servicio.interfaces.ITripulacionesService;

@RestController
@RequestMapping("/v1")
public class TripulacionesRestController {
	@Autowired
	private ITripulacionesService tripulacionesService;

	// Obtener todas las triopulaciones
	@GetMapping("/tripulaciones")
	public Iterable<TripulacionDTO> listarTodasTripulaciones() {
		return tripulacionesService.obtenerTodasTripulaciones();
	}
}
