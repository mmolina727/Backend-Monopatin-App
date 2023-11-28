package MonopatinApp.controllers;

import java.util.List;

import MonopatinApp.Dto.MonopatinDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import MonopatinApp.Dto.DtoUsuario;
import MonopatinApp.entities.Cuenta;
import MonopatinApp.entities.Usuario;
import MonopatinApp.services.UsuarioService;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
	
	@GetMapping("")
	public ResponseEntity<?>getCuentas(){
		
		List<DtoUsuario>dto=service.getAll();
		
		if(dto!=null) {
			return ResponseEntity.status(HttpStatus.OK).body(dto);
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al listar usuarios");
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?>getById(@PathVariable Integer id){
		
		DtoUsuario u= service.getById(id);
		
		if(u!=null) {
			return ResponseEntity.status(HttpStatus.OK).body(u);
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
		}
	}
	
	@PostMapping("")
	public ResponseEntity<?>create(@RequestBody Usuario u){
		try {
			service.create(u);
			return ResponseEntity.status(HttpStatus.CREATED).body(u);
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al dar de alta.Verifique de ingresar todos los campos requeridos");
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?>update(@RequestBody Usuario u,@PathVariable Integer id ){
		
		DtoUsuario aux =service.update(u,id);
		if (aux != null)
			return ResponseEntity.status(HttpStatus.OK).body(u);
			
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al modificar.Verifique que la cuenta exista");
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id){
		
		DtoUsuario aux =	service.delete(id);
		if(aux != null)
			return ResponseEntity.status(HttpStatus.OK).body("Usuario eliminado");
			
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error.El usuario que desea eliminar no existe");
		}
	}

	@GetMapping("/monopatines/cerca")
	public ResponseEntity<?> getMonopatinesCercanos(@RequestParam(name = "latitud") double latitud,
															  @RequestParam(name = "longitud") double longitud,
															  @RequestParam(name = "distancia") double distancia) {
		List<MonopatinDto> lista = service.getMonopatinesCercanos(latitud,longitud,distancia);

		if (!lista.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body(lista);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se encontraron monopatines cerca");
		}
	}

}
