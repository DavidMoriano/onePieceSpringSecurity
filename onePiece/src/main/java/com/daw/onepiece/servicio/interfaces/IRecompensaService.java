package com.daw.onepiece.servicio.interfaces;

import java.util.ArrayList;
import com.daw.onepiece.dtos.RecompensaDTO;

public interface IRecompensaService {
	ArrayList<RecompensaDTO> listarTodasRecompensas();

	ArrayList<RecompensaDTO> buscarRecompensasFiltradas(String nombrePirata, Integer idTripulacion, Long cantidadMin,
			Integer vigente);

	Integer emitirRecompensa(Integer idPirata, Long cantidad);

	int actualizarRecompensa(Integer id, Integer idPirata, Long cantidad, int vigente);

	int marcarRecompensaNoVigente(Integer id);

	ArrayList<RecompensaDTO> buscarRecompensasParaActualizar(Integer id, String nombrePirata, Integer vigente);

	ArrayList<RecompensaDTO> buscarRecompensasParaBorrar(Integer id, String nombrePirata);
}