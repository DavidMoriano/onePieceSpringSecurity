package com.adrian.onepiece.servicio.interfaces;

import java.util.ArrayList;

import com.adrian.onepiece.dtos.CapturaDTO;

public interface ICapturasService {

        public Integer registrarCaptura(Integer idPirata, String lugarCaptura) throws Exception;

        ArrayList<CapturaDTO> listarCapturasConFiltros(String nombrePirata, String lugarCaptura,
                        Long recompensaCobrada);
}
