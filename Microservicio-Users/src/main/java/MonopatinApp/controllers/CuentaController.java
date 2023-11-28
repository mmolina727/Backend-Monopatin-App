package MonopatinApp.controllers;

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
import org.springframework.web.bind.annotation.RestController;

import MonopatinApp.Dto.DtoCuenta;
import MonopatinApp.entities.Cuenta;
import MonopatinApp.services.CuentaService;

@RestController
@RequestMapping("/api/cuenta")
public class CuentaController {
	
	@Autowired
	private CuentaService service;
	
	
	@GetMapping("")
	public ResponseEntity<?>getCuentas(){
		
		List<DtoCuenta>dto=service.getAll();
		
		if(dto!=null) {
			return ResponseEntity.status(HttpStatus.OK).body(dto);
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al listar cuentas");
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?>getById(@PathVariable Integer id){
		
		DtoCuenta c= service.getById(id);
		
		if(c!=null) {
			return ResponseEntity.status(HttpStatus.OK).body(c);
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. La cuenta no existe");
		}
	}
	
	@PostMapping("")
	public ResponseEntity<?>create(@RequestBody Cuenta c){
		DtoCuenta aux =	service.create(c);
		if(aux!=null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(aux);
		}
		else{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al dar de alta.Verifique de ingresar todos los campos requeridos");
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?>update(@RequestBody Cuenta c,@PathVariable int id ){
		
		DtoCuenta aux =	service.update(c,id);
		if(aux != null)
			return ResponseEntity.status(HttpStatus.OK).body(c);
			
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al modificar.Verifique que la cuenta exista");
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?>delete(@PathVariable Integer id){
		
		try {
			DtoCuenta dto =	service.delete(id);
			if(dto!=null)
				return ResponseEntity.status(HttpStatus.OK).body(dto);
			else  {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error.La cuenta que desea eliminar no existe");
			}
		}catch (Exception e){

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. Tiene usuarios asociados");
		}


	}

}
