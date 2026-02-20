package com.daw.onepiece.controladores.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daw.onepiece.dtos.RecompensaDTO;
import com.daw.onepiece.servicio.interfaces.IRecompensaService;

@RestController
@RequestMapping("/v2")
public class RecompensasRestControllerV2 {

    @Autowired
    private IRecompensaService recompensaService;

    @GetMapping("/recompensas")
    public List<RecompensaDTO> listarTodasRecompensas() {
        return recompensaService.listarTodasRecompensas();
    }

    @GetMapping(value = "/recompensas", params = {"nombrePirata", "idTripulacion", "cantidadMin", "vigente"})
    public List<RecompensaDTO> listarRecompensasPorFiltros(
            @RequestParam(value = "nombrePirata", required = false) String nombrePirata,
            @RequestParam(value = "idTripulacion", required = false) Integer idTripulacion,
            @RequestParam(value = "cantidadMin", required = false) Long cantidadMin,
            @RequestParam(value = "vigente", required = true) Integer vigente) {

        if (vigente != 0 && vigente != 1) {
            throw new org.springframework.web.server.ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "vigente debe ser 0 o 1");
        }

        String nombreLimpio = (nombrePirata != null && !nombrePirata.trim().isEmpty()) 
                ? nombrePirata.trim() : null;

        return recompensaService.buscarRecompensasFiltradas(
                nombreLimpio, 
                idTripulacion, 
                cantidadMin, 
                vigente
        );
    }

    @GetMapping("/recompensas/{id}")
    public ResponseEntity<RecompensaDTO> obtenerRecompensaPorId(@PathVariable Integer id) {
        List<RecompensaDTO> resultados = recompensaService.buscarRecompensasFiltradas(null, null, null, 1);
        for (RecompensaDTO r : resultados) {
            if (r.getId() == id) {
                return ResponseEntity.ok(r);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/recompensas")
    public ResponseEntity<String> emitirRecompensa(@RequestBody RecompensaDTO recompensa) {
        if (recompensa.getIdPirata() <= 0) {
            return ResponseEntity.badRequest().body("El idPirata es obligatorio");
        }
        if (recompensa.getCantidad() == null || recompensa.getCantidad() <= 0) {
            return ResponseEntity.badRequest().body("La cantidad debe ser mayor que 0");
        }

        try {
            Integer idGenerado = recompensaService.emitirRecompensa(
                    recompensa.getIdPirata(),
                    recompensa.getCantidad()
            );
            return new ResponseEntity<>("Recompensa emitida con ID: " + idGenerado, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al emitir la recompensa: " + e.getMessage());
        }
    }

    @PutMapping("/recompensas/{id}")
    public ResponseEntity<?> actualizarRecompensa(
            @PathVariable Integer id,
            @RequestBody RecompensaDTO recompensa) {

        if (!id.equals(recompensa.getId())) {
            return ResponseEntity.badRequest().body("El ID de la URL no coincide con el de la recompensa");
        }

        RecompensaDTO existente = null;
        List<RecompensaDTO> resultados = recompensaService.buscarRecompensasFiltradas(null, null, null, null);
        for (RecompensaDTO r : resultados) {
            if (r.getId() == id) {
                existente = r;
                break;
            }
        }
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }

        Long cantidadFinal = (recompensa.getCantidad() != null && recompensa.getCantidad() > 0)
                ? recompensa.getCantidad()
                : existente.getCantidad();

        int vigenteFinal = (recompensa.getEstaVigente() == 0 || recompensa.getEstaVigente() == 1)
                ? recompensa.getEstaVigente()
                : existente.getEstaVigente();

        int resultado = recompensaService.actualizarRecompensa(
                id,
                recompensa.getIdPirata() <= 0 ? recompensa.getIdPirata() : existente.getIdPirata(),
                cantidadFinal,
                vigenteFinal
        );

        if (resultado <= 0) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("No se pudo actualizar la recompensa");
        }

        return ResponseEntity.ok(obtenerRecompensaPorId(id).getBody());
    }

    @DeleteMapping("/recompensas/{id}")
    public ResponseEntity<?> marcarNoVigente(@PathVariable Integer id) {
        List<RecompensaDTO> resultados = recompensaService.buscarRecompensasFiltradas(null, null, null, null);
        RecompensaDTO existente = null;
        for (RecompensaDTO r : resultados) {
            if (r.getId() == id) {
                existente = r;
                break;
            }
        }
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }

        int resultado = recompensaService.marcarRecompensaNoVigente(id);

        if (resultado <= 0) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al marcar la recompensa como no vigente");
        }

        return new ResponseEntity<>("Recompensa marcada como no vigente correctamente", HttpStatus.OK);
    }
}