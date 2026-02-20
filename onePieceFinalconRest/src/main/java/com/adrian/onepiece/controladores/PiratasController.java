package com.adrian.onepiece.controladores;

import java.time.LocalDate;
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
import com.adrian.onepiece.servicio.interfaces.IPiratasService;

@Controller
@RequestMapping("/piratas")
public class PiratasController {

    @Autowired
    private IPiratasService piratasService;

    @Autowired
    private IDesplegablesDAO desplegables;

    // -----------------
    // INSERTAR PIRATA
    // -----------------
    @GetMapping("/insertarPirata")
    public String formularioInsertarPirata(ModelMap model) {
        ArrayList<DesplegableDTO> listaIslas = desplegables.desplegableIslas();
        model.addAttribute("desplegableIslas", listaIslas);
        return "piratas/insertarPirata";
    }

    @PostMapping("/insertarPirata")
    public String insertarPirata(@RequestParam(value="id", required = false) Integer id,
            @RequestParam("nombre") String nombre,
            @RequestParam(value = "frutaDiablo", required = false) String frutaDiablo,
            @RequestParam("fechaNacimiento") String fechaNacimiento,
            @RequestParam(value = "activo", required = false) String activo,
            @RequestParam("islas") Integer idIsla,
            ModelMap model) {

        ArrayList<DesplegableDTO> listaIslas = desplegables.desplegableIslas();
        model.addAttribute("desplegableIslas", listaIslas);

        Integer act = activo != null ? 1 : 0;
        LocalDate fecha = LocalDate.parse(fechaNacimiento);
        if(frutaDiablo.isBlank() || frutaDiablo.isEmpty())
        	frutaDiablo = null;

        Integer resultado = piratasService.insertarPirata(id, nombre, frutaDiablo, fecha, act, idIsla);

        model.addAttribute("resultado", resultado);

        return "piratas/insertarPirata";
    }

    // -----------------
    // LISTADO PIRATAS
    // -----------------
    @GetMapping("/listadoPiratas")
    public String formularioListadoPiratas() {
        return "piratas/listadoPiratas";
    }

    @PostMapping("/listadoPiratas")
    public String listadoPiratas(@RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "nombre", required = false) String nombre,
            @RequestParam(value = "frutaDiablo", required = false) String frutaDiablo,
            @RequestParam(value = "activo", required = false) String activo,
            ModelMap model) {

        int act = (activo != null) ? 1 : 0;
        if(frutaDiablo.isBlank() || frutaDiablo.isEmpty())
        	frutaDiablo = null;

        ArrayList<PirataDTO> listaPiratas = piratasService.obtenerPiratasPorFiltros(id, nombre, frutaDiablo, act);

        model.addAttribute("lista", listaPiratas);

        return "piratas/listadoPiratas";
    }

    // -------------------------
    // MODIFICAR PIRATA
    // -------------------------

    // Método que simplemente nos mostrará el formulario
    @GetMapping(value = "/formularioActualizarPiratas")
    public String formularioModificarPiratas(ModelMap model) {
        return "piratas/actualizarPiratas";
    }

    // Método que se encarga de la búsqueda de piratas para actualizar
    @PostMapping(value = "/formularioActualizarPiratas")
    public String formularioModificarPiratas(@RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "nombre", required = false) String nombre,
            @RequestParam(value = "frutaDiablo", required = false) String frutaDiablo,
            @RequestParam(value = "activo", required = false) String activo,
            ModelMap model) {

        ArrayList<DesplegableDTO> listaIslas = desplegables.desplegableIslas();
        model.addAttribute("desplegableIslas", listaIslas);

        Integer act = activo != null ? 1 : 0;

        ArrayList<PirataDTO> listaPiratas = piratasService.obtenerPiratasPorFiltros(id, nombre, frutaDiablo, act);

        model.addAttribute("lista", listaPiratas);
        return "piratas/actualizarPiratas";
    }

    // Método que lleva a cabo la actualización
    @PostMapping(value = "/actualizarPirata")
    public String modificarPiratas(@RequestParam("id") Integer id,
            @RequestParam("nombre") String nombre,
            @RequestParam(value = "frutaDiablo", required = false) String frutaDiablo,
            @RequestParam("fechaNacimiento") String fechaNacimiento,
            @RequestParam(value = "activo", required = false) Integer activo,
            @RequestParam("isla") Integer idIsla,
            ModelMap model) {

        ArrayList<DesplegableDTO> listaIslas = desplegables.desplegableIslas();
        model.addAttribute("desplegableIslas", listaIslas);

        Integer act = activo != null ? 1 : 0;
        LocalDate fecha = LocalDate.parse(fechaNacimiento);

        Integer resultado = piratasService.actualizarPirata(id, nombre, frutaDiablo, fecha, act, idIsla);

        model.addAttribute("resultado", resultado);
        return "piratas/actualizarPiratas";
    }

    // -------------------------
    // BORRAR PIRATA
    // -------------------------
    // Método que se usa simplemente para mostrar el formulario.
    @GetMapping(value = "/formularioBorrarPiratas")
    public String getFormularioEliminarPiratas() {
        return "piratas/borrarPiratas";
    }

    // Método que se usa para buscar los registros a borrar
    @PostMapping(value = "/formularioBorrarPiratas")
    public String formularioEliminarPiratas(@RequestParam(value = "id", required = false) Integer id,
            @RequestParam("nombre") String nombre,
            @RequestParam(value = "frutaDiablo", required = false) String frutaDiablo,
            ModelMap model) {

        Integer act = 1;

        ArrayList<PirataDTO> listaPiratas = piratasService.obtenerPiratasPorFiltros(id, nombre, frutaDiablo, act);

        model.addAttribute("lista", listaPiratas);
        return "piratas/borrarPiratas";
    }

    @PostMapping(value = "/borrarPirata")
    public String eliminarPiratas(@RequestParam("id") Integer id, ModelMap model) {
        Integer resultado = piratasService.borrarPirata(id);

        model.addAttribute("resultado", resultado);
        return "piratas/borrarPiratas";
    }
}
