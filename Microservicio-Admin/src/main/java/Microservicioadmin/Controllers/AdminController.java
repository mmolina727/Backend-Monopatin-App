package Microservicioadmin.Controllers;

import Microservicioadmin.Services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import Microservicioadmin.Dto.DtoCuenta;
import Microservicioadmin.Dto.DtoMonopatin;
import Microservicioadmin.Dto.DtoParada;
import Microservicioadmin.Jwt.AuthResponse;
import Microservicioadmin.Jwt.LoginRequest;
import Microservicioadmin.Jwt.RegisterRequest;
import Microservicioadmin.Services.AdminService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

	@Autowired
	AdminService service;
	@Autowired
	private  AuthService authService;
	
	
	@PutMapping("/viaje/tarifa")
	public ResponseEntity<?>updatePrecio(@RequestParam(name = "precio") double precio,
										 @RequestParam(name="fecha")String fecha){
		try {
			service.updatePrecio(precio, fecha);
			return ResponseEntity.status(HttpStatus.OK).body("Tarifa actualizada");
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al actualizar tarifa");
		}
	}
	
	@GetMapping("/monopatin/reporte")
	public ResponseEntity<?>getEstadoMonopatin(){
		try {
			int disponible= service.getCantMonopatinDisponible();
			int mantenimiento=service.getCantMonopatinMantenimiento();
			return ResponseEntity.status(HttpStatus.OK).body("Hay "+disponible+" monopatines disponibles y "+mantenimiento+" en mantenimiento");
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error en realizar la consulta");
		}
	}
	
	@GetMapping("/viaje/recaudacion")
	public ResponseEntity<?> getRecaudacion(@RequestParam(name = "anio") int anio,
											@RequestParam(name = "mesIni") int mesIni,
											@RequestParam(name = "mesFin") int mesFin
											){
		try {
			double recaudacion= service.getRecaudacion(anio,mesIni,mesFin);
			return ResponseEntity.status(HttpStatus.OK).body(recaudacion);		
			}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error en generar la consulta");
		}
	}
	
	@PostMapping("/monopatin")
	public ResponseEntity<?> saveMonopatin(@RequestBody DtoMonopatin monopatin) {
		System.out.println(monopatin);
		try {

			service.saveMonopatin(monopatin);
			return ResponseEntity.status(HttpStatus.CREATED).body("Monopatin agregado");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al crear un monopatin. Verifique que los campos sean validos");
		}
	}
	
	@PostMapping("/parada")
	public ResponseEntity<?> saveParada(@RequestBody DtoParada parada){
		
		try {

			service.saveParada(parada);
			return ResponseEntity.status(HttpStatus.CREATED).body("Parada agregada");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al crear una parada. Verifique que los campos sean validos");
		}		
	}
	
	@PostMapping("/cuenta")
	public ResponseEntity<?> saveCuenta(@RequestBody DtoCuenta cuenta){

		DtoCuenta aux =	service.saveCuenta(cuenta);
		if(aux!=null){
			System.out.println("bien");
			return ResponseEntity.status(HttpStatus.CREATED).body(aux);
		} else{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al crear cuenta. Verifique que los campos sean validos");
		}
	}
	
	@DeleteMapping("/monopatin/{id}")
	public ResponseEntity<?> deleteMonopatin(@PathVariable Integer id){
		try {
			service.deleteMonopatin(id);
			return ResponseEntity.status(HttpStatus.OK).body("Monopatin eliminado");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. El monopatin no existe");
		}
	}
	
	@DeleteMapping("/parada/{id}")
	public ResponseEntity<?> deleteParada(@PathVariable Integer id){
		try {
			service.deleteParada(id);
			return ResponseEntity.status(HttpStatus.OK).body("Parada eliminada");			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. La parada no existe");
		}
	}
	
	@DeleteMapping("/cuenta/{id}")
	public ResponseEntity<?> deleteCuenta(@PathVariable Integer id){
		try {
			service.deleteCuenta(id);
			return ResponseEntity.status(HttpStatus.OK).body("Cuenta anulada");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error.La cuenta no existe");
		}
	}


	@GetMapping("/monopatin/año/{year}")
	public ResponseEntity<?> getMonopatinByYear(@PathVariable Integer year, @RequestParam(name = "cantidad") Integer cantidad){

		try{
			List<DtoMonopatin> monopatinList = service.getAllMonopatinesByYear(year,cantidad);
			if(monopatinList != null){
				return ResponseEntity.status(HttpStatus.OK).body(monopatinList);
			}else{
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No hay viajes que cumplan la condicion");
			}
		}catch (Exception e){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No hay viajes que cumplan la condicion");
		}
	}

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest){
		return ResponseEntity.ok(authService.login(loginRequest));
	}
	@PostMapping("/register")
	public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest){
		return ResponseEntity.ok(authService.register(registerRequest));
	}

}
