package com.daw.onepiece.servicio.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.onepiece.dao.interfaces.ITesoreriaDAO;
import com.daw.onepiece.dtos.TesoreriaDTO;
import com.daw.onepiece.servicio.interfaces.ITesoreriaService;


@Service
public class TesoreriaServiceImpl implements ITesoreriaService {

    @Autowired
    private ITesoreriaDAO tesoreriaDAO;

    @Override
    public ArrayList<TesoreriaDTO> listarTodasLasOperaciones() {
        return tesoreriaDAO.listarTodasLasOperaciones();
    }
}
