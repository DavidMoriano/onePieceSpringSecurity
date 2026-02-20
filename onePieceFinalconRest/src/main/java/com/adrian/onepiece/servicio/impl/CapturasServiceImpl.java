package com.adrian.onepiece.servicio.impl;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adrian.onepiece.dao.interfaces.ICapturasDAO;
import com.adrian.onepiece.dtos.CapturaDTO;
import com.adrian.onepiece.servicio.interfaces.ICapturasService;

@Service
public class CapturasServiceImpl implements ICapturasService {

    @Autowired
    private ICapturasDAO capturasDAO;

    @Override
    public Integer registrarCaptura(Integer idPirata, String lugarCaptura) throws Exception {
        return capturasDAO.registrarCaptura(idPirata, lugarCaptura);
    }

    @Override
    public ArrayList<CapturaDTO> listarCapturasConFiltros(String nombrePirata, String lugarCaptura,
            Long recompensaCobrada) {
        return capturasDAO.listarCapturasConFiltros(nombrePirata, lugarCaptura, recompensaCobrada);
    }

}
