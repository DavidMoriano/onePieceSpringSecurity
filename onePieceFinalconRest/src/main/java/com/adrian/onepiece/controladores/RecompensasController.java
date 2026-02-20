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
import com.adrian.onepiece.dtos.RecompensaDTO;
import com.adrian.onepiece.servicio.interfaces.IRecompensasService;

@Controller
@RequestMapping("/recompensas")
public class RecompensasController {

    @Autowired
    private IRecompensasService recompensasService;

    @Autowired
    private IDesplegablesDAO desplegablesDAO;

    // -----------------
    // EMITIR RECOMPENSA
    // -----------------
    @GetMapping("/emitirRecompensa")
    public String formularioEmitirRecompensa(ModelMap model) {
        ArrayList<DesplegableDTO> piratasActivos = desplegablesDAO.desplegablePiratasActivos();
        model.addAttribute("piratasActivos", piratasActivos);
        return "recompensas/emitirRecompensa";
    }

    @PostMapping("/emitirRecompensa")
    public String emitirRecompensa(@RequestParam("idPirata") Integer idPirata,
            @RequestParam("cantidad") Long cantidad, ModelMap model) {

        ArrayList<DesplegableDTO> piratasActivos = desplegablesDAO.desplegablePiratasActivos();
        model.addAttribute("piratasActivos", piratasActivos);

        // Validar que la cantidad sea mayor que 0
        if (cantidad <= 0) {
            model.addAttribute("error", "La cantidad debe ser mayor que 0");
            return "recompensas/emitirRecompensa";
        }

        try {
            Integer resultado = recompensasService.emitirRecompensa(idPirata, cantidad);
            model.addAttribute("resultado", resultado);
        } catch (Exception e) {
            model.addAttribute("error", "Error al emitir recompensa: " + e.getMessage());
        }

        return "recompensas/emitirRecompensa";
    }

    // -----------------
    // LISTADO RECOMPENSAS
    // -----------------
    @GetMapping("/listadoRecompensas")
    public String formularioListadoRecompensas(ModelMap model) {
        ArrayList<DesplegableDTO> tripulacionesActivas = desplegablesDAO.desplegableTripulacionesActivas();
        model.addAttribute("tripulacionesActivas", tripulacionesActivas);
        return "recompensas/listadoRecompensas";
    }

    @PostMapping("/listadoRecompensas")
    public String listadoRecompensas(@RequestParam(value = "nombrePirata", required = false) String nombrePirata,
            @RequestParam(value = "idTripulacion", required = false) Integer idTripulacion,
            @RequestParam(value = "cantidad", required = false) Long cantidad,
            @RequestParam(value = "estaVigente", required = false) String estaVigente, ModelMap model) {

        int vigente = (estaVigente != null) ? 1 : 0;

        ArrayList<RecompensaDTO> listaRecompensas = recompensasService.listarRecompensasConFiltros(nombrePirata,
                idTripulacion, cantidad, vigente);

        ArrayList<DesplegableDTO> tripulacionesActivas = desplegablesDAO.desplegableTripulacionesActivas();

        model.addAttribute("lista", listaRecompensas);
        model.addAttribute("tripulacionesActivas", tripulacionesActivas);

        return "recompensas/listadoRecompensas";
    }

    // -------------------------
    // MODIFICAR RECOMPENSA
    // -------------------------
    @GetMapping(value = "/formularioActualizarRecompensas")
    public String formularioModificarRecompensas(ModelMap model) {
        ArrayList<DesplegableDTO> piratasActivos = desplegablesDAO.desplegablePiratasActivos();
        model.addAttribute("piratasActivos", piratasActivos);
        return "recompensas/actualizarRecompensas";
    }

    @PostMapping(value = "/formularioActualizarRecompensas")
    public String formularioModificarRecompensas(@RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "nombrePirata", required = false) String nombrePirata,
            @RequestParam(value = "estaVigente", required = false) String estaVigente, ModelMap model) {

        Integer vigente = estaVigente != null ? 1 : 0;

        ArrayList<RecompensaDTO> listaRecompensas = recompensasService.buscarRecompensasPorFiltros(id, nombrePirata,
                vigente);

        ArrayList<DesplegableDTO> piratasActivos = desplegablesDAO.desplegablePiratasActivos();

        model.addAttribute("lista", listaRecompensas);
        model.addAttribute("piratasActivos", piratasActivos);
        return "recompensas/actualizarRecompensas";
    }

    @PostMapping(value = "/actualizarRecompensa")
    public String modificarRecompensas(@RequestParam("id") Integer id, @RequestParam("idPirata") Integer idPirata,
            @RequestParam("cantidad") Long cantidad,
            @RequestParam(value = "estaVigente", required = false) Integer estaVigente, ModelMap model) {

        Integer vigente = estaVigente != null ? 1 : 0;

        Integer resultado = recompensasService.actualizarRecompensa(id, idPirata, cantidad, vigente);

        ArrayList<DesplegableDTO> piratasActivos = desplegablesDAO.desplegablePiratasActivos();
        model.addAttribute("piratasActivos", piratasActivos);
        model.addAttribute("resultado", resultado);
        return "recompensas/actualizarRecompensas";
    }

    // -------------------------
    // BORRAR RECOMPENSA
    // -------------------------
    @GetMapping(value = "/formularioBorrarRecompensas")
    public String getFormularioEliminarRecompensas() {
        return "recompensas/borrarRecompensas";
    }

    @PostMapping(value = "/formularioBorrarRecompensas")
    public String formularioEliminarRecompensas(@RequestParam(value = "id", required = false) Integer id,
            @RequestParam("nombrePirata") String nombrePirata, ModelMap model) {

        Integer vigente = 1;

        ArrayList<RecompensaDTO> listaRecompensas = recompensasService.buscarRecompensasPorFiltros(id, nombrePirata,
                vigente);

        model.addAttribute("lista", listaRecompensas);
        return "recompensas/borrarRecompensas";
    }

    @PostMapping(value = "/borrarRecompensa")
    public String eliminarRecompensas(@RequestParam("id") Integer id, ModelMap model) {
        Integer resultado = recompensasService.eliminarRecompensa(id);

        model.addAttribute("resultado", resultado);
        return "recompensas/borrarRecompensas";
    }
}
