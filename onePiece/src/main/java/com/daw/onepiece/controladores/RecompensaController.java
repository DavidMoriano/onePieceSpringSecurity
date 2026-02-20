package com.daw.onepiece.controladores;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.daw.onepiece.dtos.PirataDTO;
import com.daw.onepiece.dtos.RecompensaDTO;
import com.daw.onepiece.dtos.TripulacionDTO;
import com.daw.onepiece.servicio.interfaces.IPirataService;
import com.daw.onepiece.servicio.interfaces.IRecompensaService;
import com.daw.onepiece.servicio.interfaces.ITripulacionService;

@Controller
@RequestMapping("/recompensas")
public class RecompensaController {

	@Autowired
	private IRecompensaService recompensaService;

	@Autowired
	private IPirataService pirataService;

	@Autowired
	private ITripulacionService tripulacionService;

	@GetMapping("/listadoRecompensas")
	public String listadoRecompensas(ModelMap model) {
		ArrayList<RecompensaDTO> lista = recompensaService.listarTodasRecompensas();
		ArrayList<TripulacionDTO> tripulacionesActivas = tripulacionService.listarTodasTripulacionesService();
		model.addAttribute("lista", lista);
		model.addAttribute("tripulacionesActivas", tripulacionesActivas);
		return "recompensas/listadoRecompensas";
	}

	@PostMapping("/listadoRecompensas")
	public String listadoRecompensasFiltrado(@RequestParam(name = "nombrePirata", required = false) String nombrePirata,
			@RequestParam(name = "idTripulacion", required = false) Integer idTripulacion,
			@RequestParam(name = "cantidad", required = false) Long cantidadMin,
			@RequestParam(name = "estaVigente", required = false) String vigenteStr, ModelMap model) {

		String nombreFiltro = (nombrePirata == null || nombrePirata.trim().isEmpty()) ? null : nombrePirata.trim();
		Integer vigente = null;
		if ("on".equals(vigenteStr) || "1".equals(vigenteStr) || "true".equalsIgnoreCase(vigenteStr)) {
			vigente = 1;
		} else if ("0".equals(vigenteStr)) {
			vigente = 0;
		}

		ArrayList<RecompensaDTO> listaFiltrada = recompensaService.buscarRecompensasFiltradas(nombreFiltro,
				idTripulacion, cantidadMin, vigente);

		ArrayList<TripulacionDTO> tripulacionesActivas = tripulacionService.listarTodasTripulacionesService();

		model.addAttribute("lista", listaFiltrada);
		model.addAttribute("tripulacionesActivas", tripulacionesActivas);

		return "recompensas/listadoRecompensas";
	}

	@GetMapping("/emitirRecompensa")
	public String mostrarFormularioEmitir(ModelMap model) {
		ArrayList<PirataDTO> piratasActivos = pirataService.listarPiratasActivosNoEnTripulacion(null);
		model.addAttribute("piratasActivos", piratasActivos);
		return "recompensas/emitirRecompensa";
	}

	@PostMapping("/emitirRecompensa")
	public String emitirRecompensa(@RequestParam(name = "idPirata") Integer idPirata,
			@RequestParam(name = "cantidad") Long cantidad, ModelMap model) {

		if (cantidad == null || cantidad <= 0) {
			model.addAttribute("error", "La cantidad de berries debe ser mayor que 0.");
			ArrayList<PirataDTO> piratasActivos = pirataService.listarPiratasActivosNoEnTripulacion(null);
			model.addAttribute("piratasActivos", piratasActivos);
			return "recompensas/emitirRecompensa";
		}

		Integer idGenerado = recompensaService.emitirRecompensa(idPirata, cantidad);

		if (idGenerado != null && idGenerado > 0) {
			model.addAttribute("resultado", idGenerado);
		} else {
			model.addAttribute("error", "No se pudo emitir la recompensa.");
		}

		ArrayList<PirataDTO> piratasActivos = pirataService.listarPiratasActivosNoEnTripulacion(null);
		model.addAttribute("piratasActivos", piratasActivos);

		return "recompensas/emitirRecompensa";
	}

	@GetMapping("/formularioActualizarRecompensas")
	public String mostrarFormularioActualizar(ModelMap model) {
		ArrayList<PirataDTO> piratasActivos = pirataService.listarPiratasActivosNoEnTripulacion(null);
		model.addAttribute("piratasActivos", piratasActivos);
		return "recompensas/actualizarRecompensas";
	}

	@PostMapping("/formularioActualizarRecompensas")
	public String buscarParaActualizar(@RequestParam(name = "id", required = false) Integer id,
			@RequestParam(name = "nombrePirata", required = false) String nombrePirata,
			@RequestParam(name = "estaVigente", required = false) String vigenteStr, ModelMap model) {

		String nombreFiltro = (nombrePirata == null || nombrePirata.trim().isEmpty()) ? null : nombrePirata.trim();
		Integer vigente = null;
		if ("on".equals(vigenteStr) || "1".equals(vigenteStr) || "true".equalsIgnoreCase(vigenteStr)) {
			vigente = 1;
		} else if ("0".equals(vigenteStr)) {
			vigente = 0;
		}

		ArrayList<RecompensaDTO> lista = recompensaService.buscarRecompensasParaActualizar(id, nombreFiltro, vigente);
		model.addAttribute("lista", lista);

		ArrayList<PirataDTO> piratasActivos = pirataService.listarPiratasActivosNoEnTripulacion(null);
		model.addAttribute("piratasActivos", piratasActivos);

		return "recompensas/actualizarRecompensas";
	}

	@PostMapping("/actualizarRecompensa")
	public String actualizarRecompensa(@RequestParam(name = "id") Integer id,
			@RequestParam(name = "idPirata") Integer idPirata, @RequestParam(name = "cantidad") Long cantidad,
			@RequestParam(name = "estaVigente", required = false) String vigenteStr, ModelMap model) {

		int vigente = "on".equals(vigenteStr) || "1".equals(vigenteStr) || "true".equalsIgnoreCase(vigenteStr) ? 1 : 0;
		int filasAfectadas = recompensaService.actualizarRecompensa(id, idPirata, cantidad, vigente);
		model.addAttribute("resultado", filasAfectadas);

		ArrayList<PirataDTO> piratasActivos = pirataService.listarPiratasActivosNoEnTripulacion(null);
		model.addAttribute("piratasActivos", piratasActivos);

		return "recompensas/actualizarRecompensas";
	}

	@GetMapping("/formularioBorrarRecompensas")
	public String mostrarFormularioBorrar(ModelMap model) {
		return "recompensas/borrarRecompensas";
	}

	@PostMapping("/formularioBorrarRecompensas")
	public String buscarParaBorrar(@RequestParam(name = "id", required = false) Integer id,
			@RequestParam(name = "nombrePirata", required = false) String nombrePirata, ModelMap model) {

		String nombreFiltro = (nombrePirata == null || nombrePirata.trim().isEmpty()) ? null : nombrePirata.trim();
		ArrayList<RecompensaDTO> lista = recompensaService.buscarRecompensasParaBorrar(id, nombreFiltro);
		model.addAttribute("lista", lista);

		return "recompensas/borrarRecompensas";
	}

	@PostMapping("/borrarRecompensa")
	public String borrarRecompensa(@RequestParam(name = "id") Integer id, ModelMap model) {

		int resultado = recompensaService.marcarRecompensaNoVigente(id);
		model.addAttribute("resultado", resultado);

		return "recompensas/borrarRecompensas";
	}
}