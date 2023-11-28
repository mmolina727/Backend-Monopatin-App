package com.MicroservicioViaje.controllers;

import com.MicroservicioViaje.Dto.MonopatinDto;
import com.MicroservicioViaje.Dto.ViajeDto;
import com.MicroservicioViaje.entities.Viaje;
import com.MicroservicioViaje.services.ViajeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/viaje")
public class ViajeController {
    @Autowired
    private ViajeService viajeService;

    @GetMapping("")
    public ResponseEntity<?> getViajes(){
        List<ViajeDto> aux = viajeService.getAll();
        if(aux != null){
            return ResponseEntity.status(HttpStatus.OK).body(aux);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al listar todos los viajes");
        }
    }
    
    @PutMapping("/actualizar")
    public ResponseEntity<?>updatePrecio(@RequestParam(name="precio")double precio,@RequestParam(name="fecha")String fecha){
    	try {
    		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaConvertida = dateFormat.parse(fecha);
    		viajeService.updatePrecio(precio, fechaConvertida);
    		return ResponseEntity.status(HttpStatus.OK).body("Precios actualizados exitosamente");
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error.Precios no actualizados");
    	}
    }
    
    @GetMapping("/recaudacion")
    public ResponseEntity<?>getRecaudacion(@RequestParam(name="anio")int anio,@RequestParam(name="mesIni")int mesIni,@RequestParam(name="mesFin")int mesFin){
    	try {
    		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = String.format("%04d-%02d-%02d", anio, mesIni, 1);
            Date dateI = dateFormat.parse(dateString);
            dateString = String.format("%04d-%02d-%02d", anio, mesFin, 28);
            Date dateF = dateFormat.parse(dateString);
            System.out.println(dateI);
            System.out.println(dateF);
    		double recaudacion= viajeService.getRecaudacion(dateI,dateF);
    		return ResponseEntity.status(HttpStatus.OK).body(recaudacion);
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error en generar la consulta");
    	}
    }
    
    @GetMapping("/{id}")

    public ResponseEntity<?> getByID(@PathVariable int id){

        ViajeDto aux = viajeService.getById(id);

        if(aux != null){
            return ResponseEntity.status(HttpStatus.OK).body(aux);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Viaje no encontrado");
        }
    }
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody Viaje v){
        ViajeDto aux = viajeService.create(v);
        if(aux!=null){
            return ResponseEntity.status(HttpStatus.CREATED).body(aux);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al crear viaje, ingrese campos correctos");
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Viaje v, @PathVariable int id){
        ViajeDto aux = viajeService.update(v);
        if(aux!=null){
            return ResponseEntity.status(HttpStatus.OK).body(aux);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El viaje no existe o ingresa incorrectamente los campos");
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        ViajeDto aux = viajeService.delete(id);
        if(aux!=null){
            return ResponseEntity.status(HttpStatus.OK).body(aux);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El viaje no existe");
        }
    }

    @GetMapping("/year/{year}")
    public ResponseEntity<?> getInYear(@PathVariable int year,@RequestParam(name = "cantidad") int cant){
        List<Integer> list = viajeService.getAllViajesInYear(year, cant);
        if(list.isEmpty()){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay viajes que cumplan la condicion");
        }else{
            List<MonopatinDto> listMonopatin = viajeService.getAllMonopatines(list);
            return ResponseEntity.status(HttpStatus.OK).body(listMonopatin);
        }
    }
}
