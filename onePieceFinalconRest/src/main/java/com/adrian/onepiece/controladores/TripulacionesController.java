package com.adrian.onepiece.controladores;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.adrian.onepiece.dao.interfaces.IDesplegablesDAO;
import com.adrian.onepiece.dtos.DesplegableDTO;
import com.adrian.onepiece.dtos.PirataDTO;
import com.adrian.onepiece.dtos.TripulacionDTO;
import com.adrian.onepiece.servicio.interfaces.ITripulacionesService;

@Controller
@RequestMapping("/tripulaciones")
public class TripulacionesController {

    @Autowired
    private ITripulacionesService tripulacionesService;

    // -----------------
    // INSERTAR TRIPULACION
    // -----------------
    @GetMapping("/insertarTripulacion")
    public String formularioInsertarTripulacion() {
        return "tripulaciones/insertarTripulacion";
    }

    @PostMapping("/insertarTripulacion")
    public String insertarTripulacion(@RequestParam("nombre") String nombre, @RequestParam("barco") String barco,
            @RequestParam(value = "estaActiva", required = false) String estaActiva, ModelMap model) {

        Integer activa = estaActiva != null ? 1 : 0;

        Integer resultado = tripulacionesService.insertarTripulacion(nombre, barco, activa);

        model.addAttribute("resultado", resultado);

        return "tripulaciones/insertarTripulacion";
    }

    // -----------------
    // LISTADO TRIPULACIONES
    // -----------------
    @GetMapping("/listadoTripulaciones")
    public String formularioListadoTripulaciones() {
        return "tripulaciones/listadoTripulaciones";
    }

    @PostMapping("/listadoTripulaciones")
    public String listadoTripulaciones(@RequestParam(value = "nombre", required = false) String nombre,
            @RequestParam(value = "barco", required = false) String barco,
            @RequestParam(value = "estaActiva", required = false) String estaActiva, ModelMap model) {

        int activa = (estaActiva != null) ? 1 : 0;

        ArrayList<TripulacionDTO> listaTripulaciones = tripulacionesService.listarTripulacionesConMiembros(nombre,
                barco,
                activa);

        model.addAttribute("lista", listaTripulaciones);

        return "tripulaciones/listadoTripulaciones";
    }

    // -----------------
    // DETALLES TRIPULACION
    // -----------------
    @Autowired
    private IDesplegablesDAO desplegablesDAO;

    @GetMapping("/detallesTripulacion")
    public String detallesTripulacion(@RequestParam("id") Integer id, ModelMap model) {

        TripulacionDTO tripulacion = tripulacionesService.obtenerTripulacionPorId(id);
        ArrayList<PirataDTO> miembros = tripulacionesService.obtenerMiembrosDeTripulacion(id);
        ArrayList<DesplegableDTO> piratasActivos = desplegablesDAO.desplegablePiratasActivos();

        model.addAttribute("tripulacion", tripulacion);
        model.addAttribute("miembros", miembros);
        model.addAttribute("piratasActivos", piratasActivos);

        return "tripulaciones/detallesTripulacion";
    }

    // Eliminar miembro de tripulación
    @PostMapping("/eliminarMiembro")
    public String eliminarMiembro(@RequestParam("idPirata") Integer idPirata,
            @RequestParam("idTripulacion") Integer idTripulacion, ModelMap model) {

        tripulacionesService.eliminarMiembroDeTripulacion(idPirata, idTripulacion);
        
      	TripulacionDTO tripulacion = tripulacionesService.obtenerTripulacionPorId(idTripulacion); 
    	ArrayList<PirataDTO> miembros = tripulacionesService.obtenerMiembrosDeTripulacion(idTripulacion);
    	ArrayList<DesplegableDTO> piratasActivos = desplegablesDAO.desplegablePiratasActivos(); 
    	model.addAttribute("tripulacion", tripulacion); 
    	model.addAttribute("miembros", miembros); 
    	model.addAttribute("piratasActivos", piratasActivos);
    	
        return "tripulaciones/detallesTripulacion";
    }

    // Agregar miembro a tripulación
    @PostMapping("/agregarMiembro")
    public String agregarMiembro(@RequestParam("idPirata") Integer idPirata,
            @RequestParam("idTripulacion") Integer idTripulacion, @RequestParam("rol") String rol, ModelMap model) {

        tripulacionesService.agregarMiembroATripulacion(idPirata, idTripulacion, rol);
        
      	TripulacionDTO tripulacion = tripulacionesService.obtenerTripulacionPorId(idTripulacion); 
    	ArrayList<PirataDTO> miembros = tripulacionesService.obtenerMiembrosDeTripulacion(idTripulacion);
    	ArrayList<DesplegableDTO> piratasActivos = desplegablesDAO.desplegablePiratasActivos(); 
    	model.addAttribute("tripulacion", tripulacion); 
    	model.addAttribute("miembros", miembros); 
    	model.addAttribute("piratasActivos", piratasActivos);
        
        model.addAttribute("idTripulacion", idTripulacion);
        return "tripulaciones/detallesTripulacion";
    }

    // -------------------------
    // MODIFICAR TRIPULACION
    // -------------------------

    @GetMapping(value = "/formularioActualizarTripulaciones")
    public String formularioModificarTripulaciones(ModelMap model) {
        return "tripulaciones/actualizarTripulaciones";
    }

    @PostMapping(value = "/formularioActualizarTripulaciones")
    public String formularioModificarTripulaciones(@RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "nombre", required = false) String nombre,
            @RequestParam(value = "estaActiva", required = false) String estaActiva, ModelMap model) {

        Integer activa = estaActiva != null ? 1 : 0;

        ArrayList<TripulacionDTO> listaTripulaciones = tripulacionesService.buscarTripulacionesPorFiltros(id, nombre,
                activa);

        model.addAttribute("lista", listaTripulaciones);
        return "tripulaciones/actualizarTripulaciones";
    }

    @PostMapping(value = "/actualizarTripulacion")
    public String modificarTripulaciones(@RequestParam("id") Integer id, @RequestParam("nombre") String nombre,
            @RequestParam("barco") String barco,
            @RequestParam(value = "estaActiva", required = false) Integer estaActiva, ModelMap model) {

        Integer activa = estaActiva != null ? 1 : 0;

        Integer resultado = tripulacionesService.actualizarTripulacion(id, nombre, barco, activa);

        model.addAttribute("resultado", resultado);
        return "tripulaciones/actualizarTripulaciones";
    }

    // -------------------------
    // BORRAR TRIPULACION
    // -------------------------
    @GetMapping(value = "/formularioBorrarTripulaciones")
    public String getFormularioEliminarTripulaciones() {
        return "tripulaciones/borrarTripulaciones";
    }

    @PostMapping(value = "/formularioBorrarTripulaciones")
    public String formularioEliminarTripulaciones(@RequestParam(value = "id", required = false) Integer id,
            @RequestParam("nombre") String nombre, ModelMap model) {

        Integer activa = 1;

        ArrayList<TripulacionDTO> listaTripulaciones = tripulacionesService.buscarTripulacionesPorFiltros(id, nombre,
                activa);

        model.addAttribute("lista", listaTripulaciones);
        return "tripulaciones/borrarTripulaciones";
    }

    @PostMapping(value = "/borrarTripulacion")
    public String eliminarTripulaciones(@RequestParam("id") Integer id, ModelMap model) {
        Integer resultado = tripulacionesService.borrarTripulacion(id);

        model.addAttribute("resultado", resultado);
        return "tripulaciones/borrarTripulaciones";
    }
}
