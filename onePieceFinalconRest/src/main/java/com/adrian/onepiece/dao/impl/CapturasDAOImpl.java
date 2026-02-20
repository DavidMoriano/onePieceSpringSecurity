package com.adrian.onepiece.dao.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.adrian.onepiece.dao.interfaces.ICapturasDAO;
import com.adrian.onepiece.dtos.CapturaDTO;
import com.adrian.onepiece.entities.CapturaEntity;
import com.adrian.onepiece.entities.PirataEntity;
import com.adrian.onepiece.entities.ReclutamientoEntity;
import com.adrian.onepiece.entities.RecompensaEntity;
import com.adrian.onepiece.entities.TesoreriaEntity;
import com.adrian.onepiece.repositorios.CapturaRepository;
import com.adrian.onepiece.repositorios.PirataRepository;
import com.adrian.onepiece.repositorios.ReclutamientoRepository;
import com.adrian.onepiece.repositorios.RecompensaRepository;
import com.adrian.onepiece.repositorios.TesoreriaRepository;

@Repository
public class CapturasDAOImpl implements ICapturasDAO {

    @Autowired
    private CapturaRepository capturaRepository;

    @Autowired
    private PirataRepository pirataRepository;

    @Autowired
    private RecompensaRepository recompensaRepository;

    @Autowired
    private ReclutamientoRepository reclutamientoRepository;

    @Autowired
    private TesoreriaRepository tesoreriaRepository;

    @Override
    @Transactional
    public Integer registrarCaptura(Integer idPirata, String lugarCaptura) throws Exception {
        try {
            // 1. Obtener el pirata
            PirataEntity pirata = pirataRepository.findById(idPirata)
                    .orElseThrow(() -> new Exception("Pirata no encontrado"));

            Long recompensaCobrada = 0L;

            // 2. Buscar si el pirata tiene una recompensa vigente
            RecompensaEntity recompensaVigente = recompensaRepository.buscarRecompensaVigentePorPirata(idPirata);

            if (recompensaVigente != null) {
                // 2a. Marcar la recompensa como no vigente
                recompensaVigente.setEstaVigente(0);
                recompensaRepository.save(recompensaVigente);

                // 2b. Establecer recompensaCobrada
                recompensaCobrada = recompensaVigente.getCantidad();

                // 2c. Registrar en TesoreriaMarina (valor negativo = gasto)
                TesoreriaEntity registroTesoreria = new TesoreriaEntity(null, "PAGO_RECOMPENSA_CAPTURA",
                        -recompensaVigente.getCantidad(), LocalDateTime.now());
                tesoreriaRepository.save(registroTesoreria);
            }

            // 3. Marcar al pirata como NO activo
            pirata.setActivo(0);
            pirataRepository.save(pirata);

            // 4. Buscar si el pirata tenía un reclutamiento activo
            ReclutamientoEntity reclutamientoActivo = reclutamientoRepository
                    .buscarReclutamientoActivoPorPirata(idPirata);

            if (reclutamientoActivo != null) {
                // 4a. Marcar el reclutamiento como no actual
                reclutamientoActivo.setEsMiembroActual(0);
                reclutamientoRepository.save(reclutamientoActivo);
            }

            // 5. Crear el registro de captura
            CapturaEntity captura = new CapturaEntity(null, pirata, lugarCaptura, recompensaCobrada);
            capturaRepository.save(captura);

            return captura.getId();

        } catch (Exception e) {
            throw new Exception("Error al registrar captura: " + e.getMessage(), e);
        }
    }

    @Override
    public ArrayList<CapturaDTO> listarCapturasConFiltros(String nombrePirata, String lugarCaptura,
            Long recompensaCobrada) {
        return capturaRepository.listarCapturasConFiltros(nombrePirata, lugarCaptura, recompensaCobrada);
    }
}
