package com.daw.onepiece.controladores;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.daw.onepiece.dtos.TesoreriaDTO;
import com.daw.onepiece.servicio.interfaces.ITesoreriaService;

@Controller
@RequestMapping("/tesoreria")
public class TesoreriaController {

    @Autowired
    private ITesoreriaService tesoreriaService;

    @GetMapping("/listadoOperaciones")
    public String listadoOperaciones(ModelMap model) {
        ArrayList<TesoreriaDTO> listaOperaciones = tesoreriaService.listarTodasLasOperaciones();
        model.addAttribute("lista", listaOperaciones);
        return "tesoreria/listadoOperaciones";
    }
}
