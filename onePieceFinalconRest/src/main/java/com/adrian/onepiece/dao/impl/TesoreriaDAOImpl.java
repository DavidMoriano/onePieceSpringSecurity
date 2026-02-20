package com.adrian.onepiece.dao.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.adrian.onepiece.dao.interfaces.ITesoreriaDAO;
import com.adrian.onepiece.dtos.TesoreriaDTO;
import com.adrian.onepiece.repositorios.TesoreriaRepository;

@Repository
public class TesoreriaDAOImpl implements ITesoreriaDAO {

    @Autowired
    private TesoreriaRepository tesoreriaRepository;

    @Override
    public ArrayList<TesoreriaDTO> listarTodasLasOperaciones() {
        return tesoreriaRepository.listarTodasLasOperaciones();
    }
}
