package com.daw.onepiece.dao.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.daw.onepiece.dao.interfaces.ITesoreriaDAO;
import com.daw.onepiece.dtos.TesoreriaDTO;
import com.daw.onepiece.repositorios.TesoreriaRepository;


@Repository
public class TesoreriaDAOImpl implements ITesoreriaDAO {

    @Autowired
    private TesoreriaRepository tesoreriaRepository;

    @Override
    public ArrayList<TesoreriaDTO> listarTodasLasOperaciones() {
        return tesoreriaRepository.listarTodasLasOperaciones();
    }
}
