package com.adrian.onepiece.dao.interfaces;

import java.time.LocalDate;
import java.util.ArrayList;

import com.adrian.onepiece.dtos.CapturaDTO;

public interface ICapturasDAO {

    Integer registrarCaptura(Integer idPirata, String lugarCaptura) throws Exception;

    ArrayList<CapturaDTO> listarCapturasConFiltros(String nombrePirata, String lugarCaptura,
            Long recompensaCobrada);
}
