package com.daw.onepiece.controladores.rest;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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

import com.daw.onepiece.dtos.PirataDTO;
import com.daw.onepiece.servicio.interfaces.IPirataService;

@RestController
@RequestMapping("/v2")
public class PiratasRestControllerV2 {

    @Autowired
    private IPirataService pirataService;

    @GetMapping("/piratas")
    public List<PirataDTO> listarTodosPiratas() {
        return pirataService.listarTodosPiratasService();
    }

    @GetMapping(value = "/piratas", params = {"id", "nombre", "frutaDiablo", "activo"})
    public List<PirataDTO> listarPiratasPorFiltros(
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "nombre", required = false) String nombre,
            @RequestParam(value = "frutaDiablo", required = false) String frutaDiablo,
            @RequestParam(value = "activo", required = false) Integer activo) {

        String nombreLimpio = (nombre != null && !nombre.trim().isEmpty()) ? nombre.trim() : null;
        String frutaLimpia = (frutaDiablo != null && !frutaDiablo.trim().isEmpty()) ? frutaDiablo.trim() : null;

        Integer activoValido = (activo != null && (activo == 0 || activo == 1)) ? activo : null;

        return pirataService.listarPiratasFiltros(id, nombreLimpio, frutaLimpia, activoValido != null ? activoValido : 1);
    }

    @GetMapping("/piratas/{id}")
    public ResponseEntity<PirataDTO> obtenerPirataPorId(@PathVariable Integer id) {
        PirataDTO pirata = pirataService.obtenerPirataPorId(id);
             if (pirata == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(pirata);
    }

    @PostMapping("/piratas")
    public ResponseEntity<String> insertarPirata(@RequestBody PirataDTO pirata) {

        if (pirata.getNombre() == null || pirata.getNombre().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El nombre del pirata es obligatorio");
        }
        if (pirata.getIdIsla() <= 0) {
            return ResponseEntity.badRequest().body("La isla es obligatoria");
        }

        String frutaFinal = (pirata.getFrutaDelDiablo() != null && !pirata.getFrutaDelDiablo().trim().isEmpty())
                ? pirata.getFrutaDelDiablo().trim() : null;

        Date fechaFinal;
        if (pirata.getFechaNacimiento() != null) {
            fechaFinal = pirata.getFechaNacimiento();
        } else {
            fechaFinal = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        }

        int activoFinal = (pirata.getEstaActivo() == 0 || pirata.getEstaActivo() == 1)
                ? pirata.getEstaActivo() : 1;

        try {
            int idGenerado = pirataService.insertarPirata(
                    pirata.getNombre().trim(),
                    frutaFinal,
                    fechaFinal,
                    pirata.getIdIsla(),
                    activoFinal
            );
            return new ResponseEntity<>("Pirata creado con ID: " + idGenerado, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear pirata: " + e.getMessage());
        }
    }

    @PutMapping("/piratas/{id}")
    public ResponseEntity<?> actualizarPirata(
            @PathVariable Integer id,
            @RequestBody PirataDTO pirata) {

        if (!id.equals(pirata.getId())) {
            return ResponseEntity.badRequest().body("El ID de la URL no coincide con el del pirata");
        }

        // Verificación de existencia (usando método temporal)
        ResponseEntity<PirataDTO> busqueda = obtenerPirataPorId(id);
        if (busqueda.getStatusCode() == HttpStatus.NOT_FOUND) {
            return ResponseEntity.notFound().build();
        }

        String nombreFinal = (pirata.getNombre() != null && !pirata.getNombre().trim().isEmpty())
                ? pirata.getNombre().trim() : null;

        String frutaFinal = (pirata.getFrutaDelDiablo() != null && !pirata.getFrutaDelDiablo().trim().isEmpty())
                ? pirata.getFrutaDelDiablo().trim() : null;

        Date fechaFinal = pirata.getFechaNacimiento();
        if (fechaFinal == null) {
            fechaFinal = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        }

        String idIslaStr = (pirata.getIdIsla() > 0) ? String.valueOf(pirata.getIdIsla()) : null;

        int activoFinal = (pirata.getEstaActivo() == 0 || pirata.getEstaActivo() == 1)
                ? pirata.getEstaActivo() : 1;

        int resultado = pirataService.actualizarPirata(
                id,
                nombreFinal,
                frutaFinal,
                idIslaStr,
                fechaFinal,
                activoFinal
        );

        if (resultado <= 0) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("No se pudo actualizar el pirata");
        }

        return ResponseEntity.ok(obtenerPirataPorId(id).getBody());
    }

    @DeleteMapping("/piratas/{id}")
    public ResponseEntity<?> borrarPirata(@PathVariable Integer id) {

        ResponseEntity<PirataDTO> busqueda = obtenerPirataPorId(id);
        if (busqueda.getStatusCode() == HttpStatus.NOT_FOUND) {
            return ResponseEntity.notFound().build();
        }

        int resultado = pirataService.borrarPirata(id);

        if (resultado <= 0) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al desactivar el pirata");
        }

        return new ResponseEntity<>("Pirata desactivado correctamente", HttpStatus.OK);
    }
}