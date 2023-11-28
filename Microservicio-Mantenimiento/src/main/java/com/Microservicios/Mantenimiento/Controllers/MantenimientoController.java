package com.Microservicios.Mantenimiento.Controllers;

import com.Microservicios.Mantenimiento.Dto.*;
import com.Microservicios.Mantenimiento.Services.MantenimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mantenimiento")
public class MantenimientoController {
    @Autowired
    MantenimientoService service;

    @GetMapping("/reporte")
    public ResponseEntity<?> getReporte(@RequestParam(name = "pausa",required = false) boolean pausa){

        List<Reporte> res = service.generateReport(pausa);
        if(res.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron monopatines");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> changeStatus(@RequestBody Estado estado,@PathVariable Integer id){

        try{
            DtoMonopatin monopatin = service.changeStatus(estado.getEstado(),id);
            return ResponseEntity.status(HttpStatus.OK).body(monopatin);
        }catch (Exception e){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el monopatin");
        }
    }
}
