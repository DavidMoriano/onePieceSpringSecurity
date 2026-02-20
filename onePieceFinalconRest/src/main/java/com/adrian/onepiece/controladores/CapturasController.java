package com.adrian.onepiece.controladores;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.adrian.onepiece.dao.interfaces.IDesplegablesDAO;
import com.adrian.onepiece.dtos.CapturaDTO;
import com.adrian.onepiece.dtos.DesplegableDTO;
import com.adrian.onepiece.servicio.interfaces.ICapturasService;

@Controller
@RequestMapping("/capturas")
public class CapturasController {

    @Autowired
    private ICapturasService capturasService;

    @Autowired
    private IDesplegablesDAO desplegablesDAO;

    // -----------------
    // REGISTRAR CAPTURA
    // -----------------
    @GetMapping("/registrarCaptura")
    public String formularioRegistrarCaptura(ModelMap model) {
        ArrayList<DesplegableDTO> piratasActivos = desplegablesDAO.desplegablePiratasActivos();
        model.addAttribute("piratasActivos", piratasActivos);
        return "capturas/registrarCaptura";
    }

    @PostMapping("/registrarCaptura")
    public String registrarCaptura(@RequestParam("idPirata") Integer idPirata,
            @RequestParam("lugarCaptura") String lugarCaptura,
            ModelMap model) {

        ArrayList<DesplegableDTO> piratasActivos = desplegablesDAO.desplegablePiratasActivos();
        model.addAttribute("piratasActivos", piratasActivos);

        try {
            Integer resultado = capturasService.registrarCaptura(idPirata, lugarCaptura);
            model.addAttribute("resultado", resultado);
        } catch (Exception e) {
            model.addAttribute("error", "Error al registrar captura: " + e.getMessage());
        }

        return "capturas/registrarCaptura";
    }

    // -----------------
    // LISTADO CAPTURAS
    // -----------------
    @GetMapping("/listadoCapturas")
    public String formularioListadoCapturas(ModelMap model) {
        return "capturas/listadoCapturas";
    }

    @PostMapping("/listadoCapturas")
    public String listadoCapturas(@RequestParam(value = "nombrePirata", required = false) String nombrePirata,
            @RequestParam(value = "lugarCaptura", required = false) String lugarCaptura,
            @RequestParam(value = "recompensaCobrada", required = false) Long recompensaCobrada, ModelMap model) {

        ArrayList<CapturaDTO> listaCapturas = capturasService.listarCapturasConFiltros(nombrePirata, lugarCaptura,
                recompensaCobrada);

        model.addAttribute("lista", listaCapturas);

        return "capturas/listadoCapturas";
    }
}
