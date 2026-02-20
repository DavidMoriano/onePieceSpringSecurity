package com.daw.onepiece.controladores.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daw.onepiece.dtos.TripulacionDTO;
import com.daw.onepiece.servicio.interfaces.ITripulacionService;

@RestController
@RequestMapping("/v2")
public class TripulacionesRestControllerV2 {

	@Autowired
	private ITripulacionService tripulacionService;

	@GetMapping("/tripulaciones")
	public List<TripulacionDTO> listarTodasTripulaciones() {
		return tripulacionService.listarTodasTripulacionesService();
	}

	@GetMapping(value = "/tripulaciones", params = { "nombre", "barco", "activo" })
	public List<TripulacionDTO> listarTripulacionesPorFiltros(
			@RequestParam(value = "nombre", required = false) String nombre,
			@RequestParam(value = "barco", required = false) String barco,
			@RequestParam(value = "activo", required = true) Integer activo) {

		if (activo != 0 && activo != 1) {
			throw new org.springframework.web.server.ResponseStatusException(HttpStatus.BAD_REQUEST,
					"activo debe ser 0 o 1");
		}

		String nombreLimpio = (nombre != null && !nombre.trim().isEmpty()) ? nombre.trim() : null;
		String barcoLimpio = (barco != null && !barco.trim().isEmpty()) ? barco.trim() : null;

		return tripulacionService.listarTripulacionesFiltros(nombreLimpio, barcoLimpio, activo);
	}

	@GetMapping("/tripulaciones/{id}")
	public ResponseEntity<TripulacionDTO> obtenerTripulacionPorId(@PathVariable Integer id) {
		TripulacionDTO tripulacion = tripulacionService.obtenerTripulacionPorId(id);
		if (tripulacion == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(tripulacion);
	}

	@PostMapping("/tripulaciones")
	public ResponseEntity<String> insertarTripulacion(@RequestBody TripulacionDTO tripulacion) {
		if (tripulacion.getNombre() == null || tripulacion.getNombre().trim().isEmpty()) {
			return ResponseEntity.badRequest().body("El nombre de la tripulación es obligatorio");
		}
		if (tripulacion.getBarco() == null || tripulacion.getBarco().trim().isEmpty()) {
			return ResponseEntity.badRequest().body("El barco es obligatorio");
		}

		int activa = (tripulacion.getEstaActiva() == 0 || tripulacion.getEstaActiva() == 1)
				? tripulacion.getEstaActiva()
				: 1;

		try {
			Integer idGenerado = tripulacionService.insertarTripulacion(tripulacion.getNombre().trim(),
					tripulacion.getBarco().trim(), activa);
			return new ResponseEntity<>("Tripulación creada con ID: " + idGenerado, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al crear la tripulación: " + e.getMessage());
		}
	}

	@PutMapping("/tripulaciones/{id}")
	public ResponseEntity<?> actualizarTripulacion(@PathVariable Integer id, @RequestBody TripulacionDTO tripulacion) {

		if (!id.equals(tripulacion.getId())) {
			return ResponseEntity.badRequest().body("El ID de la URL no coincide con el de la tripulación");
		}

		TripulacionDTO existente = tripulacionService.obtenerTripulacionPorId(id);
		if (existente == null) {
			return ResponseEntity.notFound().build();
		}

		String nombreFinal = (tripulacion.getNombre() != null && !tripulacion.getNombre().trim().isEmpty())
				? tripulacion.getNombre().trim()
				: existente.getNombre();

		String barcoFinal = (tripulacion.getBarco() != null && !tripulacion.getBarco().trim().isEmpty())
				? tripulacion.getBarco().trim()
				: existente.getBarco();

		int activaFinal = (tripulacion.getEstaActiva() == 0 || tripulacion.getEstaActiva() == 1)
				? tripulacion.getEstaActiva()
				: existente.getEstaActiva();

		int resultado = tripulacionService.actualizarTripulacion(id, nombreFinal, barcoFinal, activaFinal);

		if (resultado <= 0) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo actualizar la tripulación");
		}

		return ResponseEntity.ok(tripulacionService.obtenerTripulacionPorId(id));
	}

	@DeleteMapping("/tripulaciones/{id}")
	public ResponseEntity<?> desactivarTripulacion(@PathVariable Integer id) {
		TripulacionDTO existente = tripulacionService.obtenerTripulacionPorId(id);
		if (existente == null) {
			return ResponseEntity.notFound().build();
		}

		int resultado = tripulacionService.desactivarTripulacion(id);

		if (resultado <= 0) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al desactivar la tripulación");
		}

		return new ResponseEntity<>("Tripulación desactivada correctamente", HttpStatus.OK);
	}
}