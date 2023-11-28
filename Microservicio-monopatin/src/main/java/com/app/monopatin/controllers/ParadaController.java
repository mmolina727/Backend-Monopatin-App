package com.app.monopatin.controllers;

import com.app.monopatin.dtos.ParadaDto;
import com.app.monopatin.entitys.Parada;
import com.app.monopatin.services.ParadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/parada")
public class ParadaController {

    @Autowired
    private ParadaService service;

    @GetMapping("")
    public ResponseEntity<?>getAll(){

        List<ParadaDto> dto=service.getAll();

        if(dto!=null) {
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al listar paradas");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>getById(@PathVariable Integer id){

        ParadaDto c= service.getById(id);

        if(c!=null) {
            return ResponseEntity.status(HttpStatus.OK).body(c);
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
        }
    }

    @PostMapping("")
    public ResponseEntity<?>create(@RequestBody ParadaDto p){
        try {
            service.create(new Parada(p.getLatitud(), p.getLongitud()));
            return ResponseEntity.status(HttpStatus.CREATED).body(p);
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al dar de alta.Verifique de ingresar todos los campos requeridos");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?>update(@PathVariable Integer id, @RequestBody ParadaDto p){

        try {
            service.update(id, p);
            return ResponseEntity.status(HttpStatus.OK).body(p);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Error al modificar.Verifique que la parada exista");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>delete(@PathVariable Integer id){

        try {
            service.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Parada eliminada");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. La parada que desea eliminar no existe");
        }
    }
}
