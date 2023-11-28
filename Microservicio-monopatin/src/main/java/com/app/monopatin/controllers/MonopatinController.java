package com.app.monopatin.controllers;

import com.app.monopatin.dtos.MonopatinDto;
import com.app.monopatin.dtos.ViajeDto;
import com.app.monopatin.entitys.Monopatin;
import com.app.monopatin.entitys.Parada;
import com.app.monopatin.services.MonopatinService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/monopatin")
public class MonopatinController {

    @Autowired
    private MonopatinService service;

    
    @GetMapping("/disponibles")
    
    public ResponseEntity<?>getCantMonopatinDisponible(){
    	try {
    		Integer cantidad=service.getCantMonopatinDisponible();
    		return ResponseEntity.status(HttpStatus.OK).body(cantidad);
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error en realizar la consulta");
    	}
    }
    
    @GetMapping("/mantenimiento")
    
    public ResponseEntity<?>getCantMonopatinMantenimiento(){
    	try {
    		int cantidad=service.getCantMonopatinMantenimiento();
    		return ResponseEntity.status(HttpStatus.OK).body(cantidad);
    	}
    	catch(Exception e) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error en realizar la consulta");
    	}
    }

    @GetMapping("")
    public ResponseEntity<?>getAll(){

        List<MonopatinDto> dto=service.getAll();

        if(dto!=null) {
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al listar monopatines");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>getById(@PathVariable Integer id){

        MonopatinDto m= service.getById(id);

        if(m!=null) {
            return ResponseEntity.status(HttpStatus.OK).body(m);
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. Monopatin no encontrado");
        }
    }

    @PostMapping("")
    public ResponseEntity<?>create(@RequestBody MonopatinDto m) throws Exception {

        try{
        	service.create(m);
            return ResponseEntity.status(HttpStatus.CREATED).body(m);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al crear monopatin, ingrese campos correctos");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?>update(@PathVariable Integer id, @RequestBody MonopatinDto m){

        try {
            service.update(id, m);
            return ResponseEntity.status(HttpStatus.OK).body(m);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al modificar.Verifique que el monopatin exista");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>delete(@PathVariable Integer id){

        MonopatinDto aux = service.delete(id);
        if(aux != null)
            return ResponseEntity.status(HttpStatus.OK).body("Monopatin eliminado");
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. El monopatin que desea eliminar no existe");
        }
    }

    @GetMapping("/cercanos")
    public ResponseEntity<?>getMonopatinesCercanosAMonopatin(@RequestParam(name = "latitud") double latitud,
                                                             @RequestParam(name = "longitud") double longitud,
                                                             @RequestParam(name = "distancia") double distancia){
        List<MonopatinDto> dto = service.getMonopatinesCercanosAMonopatin(latitud,longitud, distancia);

        if (!dto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se encontraron monopatines cerca");
        }

    }
    @PostMapping("/inlist")
    public ResponseEntity<?> getMonopatinesInList(@RequestBody List<Integer> list){
        List<MonopatinDto> listaDTO = service.getAllIn(list);
        return ResponseEntity.status(HttpStatus.OK).body(listaDTO);
    }
}
