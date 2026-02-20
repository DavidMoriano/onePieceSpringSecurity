package com.adrian.onepiece.servicio.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adrian.onepiece.dao.interfaces.ITesoreriaDAO;
import com.adrian.onepiece.dtos.TesoreriaDTO;
import com.adrian.onepiece.servicio.interfaces.ITesoreriaService;

@Service
public class TesoreriaServiceImpl implements ITesoreriaService {

    @Autowired
    private ITesoreriaDAO tesoreriaDAO;

    @Override
    public ArrayList<TesoreriaDTO> listarTodasLasOperaciones() {
        return tesoreriaDAO.listarTodasLasOperaciones();
    }
}
