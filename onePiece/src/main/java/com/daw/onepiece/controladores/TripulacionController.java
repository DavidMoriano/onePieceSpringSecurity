package com.daw.onepiece.controladores;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.daw.onepiece.dtos.MiembroTripulacionDTO;
import com.daw.onepiece.dtos.PirataDTO;
import com.daw.onepiece.dtos.TripulacionDTO;
import com.daw.onepiece.servicio.interfaces.IPirataService;
import com.daw.onepiece.servicio.interfaces.ITripulacionService;

@Controller
@RequestMapping("/tripulaciones")
public class TripulacionController {

	@Autowired
	private ITripulacionService tripulacionService;

	@Autowired
	private IPirataService pirataService;

	@GetMapping("/listadoTripulaciones")
	public String listadoTripulaciones(ModelMap model) {
		ArrayList<TripulacionDTO> lista = tripulacionService.listarTodasTripulacionesService();
		model.addAttribute("lista", lista);
		return "tripulaciones/listadoTripulaciones";
	}

	@PostMapping("/listadoTripulaciones")
	public String listadoTripulacionesFiltros(@RequestParam(name = "nombre", required = false) String nombre,
			@RequestParam(name = "barco", required = false) String barco,
			@RequestParam(name = "estaActiva", required = false) String activo, ModelMap model) {

		String nombreFiltro = (nombre == null || nombre.trim().isEmpty()) ? null : nombre.trim();
		String barcoFiltro = (barco == null || barco.trim().isEmpty()) ? null : barco.trim();

		int activoInt = "on".equals(activo) || "true".equalsIgnoreCase(activo) || "1".equals(activo) ? 1 : 0;

		ArrayList<TripulacionDTO> listaFiltrada = tripulacionService.listarTripulacionesFiltros(nombreFiltro, barcoFiltro, activoInt);
		model.addAttribute("lista", listaFiltrada);
		return "tripulaciones/listadoTripulaciones";
	}

	@GetMapping("/insertarTripulacion")
	public String mostrarFormularioInsertar() {
		return "tripulaciones/insertarTripulacion";
	}

	@PostMapping("/insertarTripulacion")
	public String insertarTripulacion(@RequestParam(name = "nombre") String nombre,
			@RequestParam(name = "barco") String barco,
			@RequestParam(name = "estaActiva", required = false) String estaActivaStr, ModelMap model) {

		String nombreLimpio = (nombre == null || nombre.trim().isEmpty()) ? null : nombre.trim();
		String barcoLimpio = (barco == null || barco.trim().isEmpty()) ? null : barco.trim();

		int activa = "on".equals(estaActivaStr) || "true".equalsIgnoreCase(estaActivaStr) || "1".equals(estaActivaStr)
				? 1
				: 0;

		Integer idGenerado = tripulacionService.insertarTripulacion(nombreLimpio, barcoLimpio, activa);

		model.addAttribute("resultado", idGenerado);
		return "tripulaciones/insertarTripulacion";
	}

	@GetMapping("/detallesTripulacion")
	public String detallesTripulacion(@RequestParam(name = "id") Integer id, ModelMap model) {

		TripulacionDTO tripulacion = tripulacionService.obtenerTripulacionPorId(id);
		if (tripulacion == null) {
			model.addAttribute("error", "La tripulación con ID " + id + " no existe.");
			return "tripulaciones/listadoTripulaciones";
		}

		ArrayList<MiembroTripulacionDTO> miembros = tripulacionService.obtenerMiembrosActivosDeTripulacion(id);
		ArrayList<PirataDTO> piratasDisponibles = pirataService.listarPiratasActivosNoEnTripulacion(id);

		model.addAttribute("tripulacion", tripulacion);
		model.addAttribute("miembros", miembros);
		model.addAttribute("piratasActivos", piratasDisponibles);

		return "tripulaciones/detallesTripulacion";
	}

	@PostMapping("/agregarMiembro")
	public String agregarMiembro(@RequestParam(name = "idTripulacion") Integer idTripulacion,
			@RequestParam(name = "idPirata") Integer idPirata, @RequestParam(name = "rol") String rol, ModelMap model) {

		String rolLimpio = (rol == null || rol.trim().isEmpty()) ? "Miembro" : rol.trim();

		tripulacionService.agregarMiembroATripulacion(idPirata, idTripulacion, rolLimpio);

		return "redirect:/tripulaciones/detallesTripulacion?id=" + idTripulacion;
	}

	@PostMapping("/eliminarMiembro")
	public String eliminarMiembro(@RequestParam(name = "idPirata") Integer idPirata,
			@RequestParam(name = "idTripulacion") Integer idTripulacion, ModelMap model) {

		tripulacionService.eliminarMiembroDeTripulacion(idPirata, idTripulacion);

		return "redirect:/tripulaciones/detallesTripulacion?id=" + idTripulacion;
	}

	@GetMapping("/formularioActualizarTripulaciones")
	public String formularioActualizarTripulaciones() {
		return "tripulaciones/actualizarTripulaciones";
	}

	@PostMapping("/formularioActualizarTripulaciones")
	public String buscarParaActualizar(@RequestParam(name = "id", required = false) Integer id,
			@RequestParam(name = "nombre", required = false) String nombre,
			@RequestParam(name = "estaActiva", required = false) String estaActivaStr, ModelMap model) {

		String nombreFiltro = (nombre == null || nombre.trim().isEmpty()) ? null : nombre.trim();
		Integer activo = null;
		if ("1".equals(estaActivaStr) || "on".equals(estaActivaStr) || "true".equalsIgnoreCase(estaActivaStr)) {
			activo = 1;
		} else if ("0".equals(estaActivaStr)) {
			activo = 0;
		}

		ArrayList<TripulacionDTO> lista = tripulacionService.listarTripulacionesFiltros(nombreFiltro, null, activo);
		model.addAttribute("lista", lista);

		return "tripulaciones/actualizarTripulaciones";
	}

	@PostMapping("/actualizarTripulacion")
	public String actualizarTripulacion(@RequestParam(name = "id") Integer id,
			@RequestParam(name = "nombre") String nombre, @RequestParam(name = "barco") String barco,
			@RequestParam(name = "estaActiva", required = false) String estaActivaStr, ModelMap model) {

		String nombreLimpio = nombre.trim();
		String barcoLimpio = barco.trim();
		int activa = "on".equals(estaActivaStr) || "true".equalsIgnoreCase(estaActivaStr) || "1".equals(estaActivaStr) ? 1 : 0;

		int filasAfectadas = tripulacionService.actualizarTripulacion(id, nombreLimpio, barcoLimpio, activa);

		model.addAttribute("resultado", filasAfectadas);
		return "tripulaciones/actualizarTripulaciones";
	}

	@GetMapping("/formularioBorrarTripulaciones")
	public String formularioBorrarTripulaciones() {
		return "tripulaciones/borrarTripulaciones";
	}

	@PostMapping("/formularioBorrarTripulaciones")
	public String buscarParaBorrar(@RequestParam(name = "id", required = false) Integer id,
			@RequestParam(name = "nombre", required = false) String nombre, ModelMap model) {

		String nombreFiltro = (nombre == null || nombre.trim().isEmpty()) ? null : nombre.trim();

		ArrayList<TripulacionDTO> lista = tripulacionService.listarTripulacionesFiltros(nombreFiltro, null, 1);
		model.addAttribute("lista", lista);

		return "tripulaciones/borrarTripulaciones";
	}

	@PostMapping("/borrarTripulacion")
	public String borrarTripulacion(@RequestParam(name = "id") Integer id, ModelMap model) {

		int resultado = tripulacionService.desactivarTripulacion(id);
		model.addAttribute("resultado", resultado);

		return "tripulaciones/borrarTripulaciones";
	}
}