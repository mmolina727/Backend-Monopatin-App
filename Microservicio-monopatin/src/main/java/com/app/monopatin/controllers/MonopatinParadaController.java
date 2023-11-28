package com.app.monopatin.controllers;

import com.app.monopatin.services.MonopatintParadaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("monopatinParada")
public class MonopatinParadaController {

    private MonopatintParadaService service;
    @PostMapping("/generar-y-cargar")
    public ResponseEntity<String> generarYGuardarMonopatinesYParadas() {
        service.generarYGuardarMonopatinesYParadas();
        if(ResponseEntity.ok()!=null){
            return ResponseEntity.status(HttpStatus.CREATED).body("Registros generados y guardados en la base de datos.");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al crear registros, ingrese campos correctos");
        }
    }
}
