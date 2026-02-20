package com.daw.onepiece.dao.interfaces;

import java.util.ArrayList;
import com.daw.onepiece.dtos.RecompensaDTO;

public interface IRecompensaDAO {
	ArrayList<RecompensaDTO> listarTodasRecompensas();

	ArrayList<RecompensaDTO> buscarRecompensasFiltradas(String nombrePirata, Integer idTripulacion, Long cantidadMin,
			Integer vigente);

	Integer emitirRecompensa(Integer idPirata, Long cantidad);

	int actualizarRecompensa(Integer id, Integer idPirata, Long cantidad, int vigente);

	int marcarNoVigente(Integer id);

	ArrayList<RecompensaDTO> buscarRecompensasParaActualizar(Integer id, String nombrePirata, Integer vigente);

	ArrayList<RecompensaDTO> buscarRecompensasParaBorrar(Integer id, String nombrePirata);
}