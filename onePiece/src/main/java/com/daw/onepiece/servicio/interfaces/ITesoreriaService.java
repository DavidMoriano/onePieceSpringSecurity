package com.daw.onepiece.servicio.interfaces;

import java.util.ArrayList;

import com.daw.onepiece.dtos.TesoreriaDTO;


public interface ITesoreriaService {

    ArrayList<TesoreriaDTO> listarTodasLasOperaciones();
}
