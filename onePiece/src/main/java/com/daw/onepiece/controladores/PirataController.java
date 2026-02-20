package com.daw.onepiece.controladores;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.daw.onepiece.dao.interfaces.IDesplegablesDAO;
import com.daw.onepiece.dtos.DesplegableDTO;
import com.daw.onepiece.dtos.PirataDTO;
import com.daw.onepiece.servicio.interfaces.IPirataService;

@Controller
@RequestMapping("/piratas")
public class PirataController {
	@Autowired
	private IPirataService piratasServ;

	@Autowired
	private IDesplegablesDAO desplegables;

	@GetMapping("/listadoPiratas")
	public String listadoPiratas(ModelMap model) {
		ArrayList<PirataDTO> listaPiratas = piratasServ.listarTodosPiratasService();
		model.addAttribute("lista", listaPiratas);
		return "piratas/listadoPiratas";
	}

	@PostMapping("/listadoPiratas")
	public String listarPiratasFiltros(@RequestParam(name = "id", required = false) Integer id,
			@RequestParam(name = "nombre", required = false) String nombrePirata,
			@RequestParam(name = "frutaDiablo", required = false) String frutaDiablo,
			@RequestParam(name = "activo", required = false) String activo, ModelMap model) {

		String nombreNull = nombrePirata == null || nombrePirata.trim().isEmpty() ? null : nombrePirata.trim();
		String frutaNUll = frutaDiablo == null || frutaDiablo.trim().isEmpty() ? null : frutaDiablo.trim();

		int activoInt = "on".equals(activo) || "true".equalsIgnoreCase(activo) || "1".equals(activo) ? 1 : 0;

		ArrayList<PirataDTO> listaFiltrosPiratas = piratasServ.listarPiratasFiltros(id, nombreNull, frutaNUll,
				activoInt);
		model.addAttribute("lista", listaFiltrosPiratas);
		return "piratas/listadoPiratas";
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@GetMapping("/insertarPirata")
	public String insertarPirata(ModelMap model) {
		ArrayList<DesplegableDTO> desplegableIslas = desplegables.desplegableIslas();
		model.addAttribute("desplegableIslas", desplegableIslas);
		return "/piratas/insertarPirata";
	}

	@PostMapping("/insertarPirata")
	public String formularioInsertarPirata(@RequestParam(name = "nombre") String nombrePirata,
			@RequestParam(name = "frutaDiablo", required = false) String frutaDiablo,
			@RequestParam(name = "fechaNacimiento", required = false) String fecha,
			@RequestParam(name = "islas") int idIsla, @RequestParam(name = "activo", required = false) String activo,
			ModelMap model) {

		Date fechaFiltro = null;
		if (fecha != null && !fecha.trim().isEmpty()) {
			try {
				LocalDate localDate = LocalDate.parse(fecha.trim(), DateTimeFormatter.ISO_LOCAL_DATE);
				fechaFiltro = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
			} catch (DateTimeParseException e) {
				fechaFiltro = new Date();
			}
		}

		String frutaDiabloPosibleNull = frutaDiablo == null || frutaDiablo.trim().isEmpty() ? null : frutaDiablo.trim();
		String nombrePirataPosibleNull = nombrePirata == null || nombrePirata.trim().isEmpty() ? null
				: nombrePirata.trim();

		int activoInt = "on".equals(activo) || "true".equalsIgnoreCase(activo) || "1".equals(activo) ? 1 : 0;

		Integer idGenerado = piratasServ.insertarPirata(nombrePirataPosibleNull, frutaDiabloPosibleNull, fechaFiltro,
				idIsla, activoInt);

		model.addAttribute("resultado", idGenerado.toString());

		return "/piratas/insertarPirata";
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@GetMapping("/formularioActualizarPiratas")
	public String formularioPiratas() {
		return "/piratas/actualizarPiratas";
	}

	@PostMapping("/formularioActualizarPiratas")
	public String formularioBuscarPiratas(@RequestParam(name = "id", required = false) Integer id,
			@RequestParam(name = "nombre", required = false) String nombrePirata,
			@RequestParam(name = "frutaDiablo", required = false) String frutaDiablo,
			@RequestParam(name = "activo", required = false) String activo, ModelMap model) {

		ArrayList<DesplegableDTO> desplegableIslas = desplegables.desplegableIslas();
		model.addAttribute("desplegableIslas", desplegableIslas);

		String nombreNull = nombrePirata == null || nombrePirata.trim().isEmpty() ? null : nombrePirata.trim();
		String frutaNUll = frutaDiablo == null || frutaDiablo.trim().isEmpty() ? null : frutaDiablo.trim();

		int activoInt = "on".equals(activo) || "true".equalsIgnoreCase(activo) || "1".equals(activo) ? 1 : 0;

		ArrayList<PirataDTO> listaFiltrosPiratas = piratasServ.listarPiratasFiltros(id, nombreNull, frutaNUll, activoInt);
		model.addAttribute("lista", listaFiltrosPiratas);
		return "piratas/actualizarPiratas";
	}

	@PostMapping("/actualizarPirata")
	public String actualizarPirata(@RequestParam(name = "id", required = false) Integer id,
			@RequestParam(name = "nombre", required = false) String nombrePirata,
			@RequestParam(name = "frutaDiablo", required = false) String frutaDiablo,
			@RequestParam(name = "activo", required = false) String activo,
			@RequestParam(name = "fechaNacimiento", required = false) String fecha,
			@RequestParam(name = "isla", required = false) String idIsla, ModelMap model) {

		int activoInt = "on".equals(activo) || "true".equalsIgnoreCase(activo) || "1".equals(activo) ? 1 : 0;

		String nombreNull = nombrePirata == null || nombrePirata.trim().isEmpty() ? null : nombrePirata.trim();

		Date fechaFiltro = null;
		if (fecha != null && !fecha.trim().isEmpty()) {
			try {
				LocalDate localDate = LocalDate.parse(fecha.trim(), DateTimeFormatter.ISO_LOCAL_DATE);
				fechaFiltro = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
			} catch (DateTimeParseException e) {
				fechaFiltro = new Date();
			}
		}

		int resultado = piratasServ.actualizarPirata(id, nombreNull, frutaDiablo, idIsla, fechaFiltro, activoInt);

		model.addAttribute("resultado", resultado);
		return "piratas/actualizarPiratas";
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@GetMapping("/formularioBorrarPiratas")
	public String formularioBorrarPiratas() {
		return "piratas/borrarPiratas";
	}

	@PostMapping("/formularioBorrarPiratas")
	public String listadoBorrarPiratas(@RequestParam(name = "id", required = false) Integer id,
			@RequestParam(name = "nombre", required = false) String nombrePirata, ModelMap model) {

		String nombreNull = nombrePirata == null || nombrePirata.trim().isEmpty() ? null : nombrePirata.trim();

		ArrayList<PirataDTO> listaFiltrosPiratas = piratasServ.listarPiratasFiltros(id, nombreNull, null, 1);
		model.addAttribute("lista", listaFiltrosPiratas);

		return "piratas/borrarPiratas";
	}

	@PostMapping("/borrarPirata")
	public String ponerActivoCeroPirata(@RequestParam(name = "id") Integer id, ModelMap model) {
		int pirataBorrado = piratasServ.borrarPirata(id);
		model.addAttribute("resultado", pirataBorrado);

		return "piratas/borrarPiratas";
	}

}
