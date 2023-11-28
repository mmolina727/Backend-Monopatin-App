package MonopatinApp.services;

import java.time.LocalDate;
import java.util.*;

import MonopatinApp.Dto.MonopatinDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import MonopatinApp.Dto.DtoUsuario;
import MonopatinApp.entities.Cuenta;
import MonopatinApp.entities.Usuario;
import MonopatinApp.repositories.CuentaRepository;
import MonopatinApp.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
public class UsuarioService {
	@Autowired
	UsuarioRepository repository;
	
	@Autowired
	CuentaRepository cRepository;

	RestTemplate template;

	public UsuarioService(UsuarioRepository repository) {
		super();
		this.repository = repository;
		this.template = new RestTemplate();
	}
	
	@Transactional
	public List<DtoUsuario>getAll(){
		
		ArrayList<DtoUsuario>dto= new ArrayList<>();
		List<Usuario>u= repository.findAll();
		
		for(int i=0;i<u.size();i++) {
			Usuario us= u.get(i);
			DtoUsuario a= new DtoUsuario(us.getIdUsuario(),us.getNombre(),us.getApellido(),us.getMail(),us.getNumTelefono(),us.getCuentas());
			dto.add(a);
		}
		return dto;
	}
	
	@Transactional
	public DtoUsuario getById(@PathVariable Integer id) {
		
		Optional<Usuario>usuario=repository.findById(id);
		
		if(usuario.isPresent()) {
			Usuario us=usuario.get();
			DtoUsuario user= new DtoUsuario(us.getIdUsuario(),us.getNombre(),us.getApellido(),us.getMail(),us.getNumTelefono(),us.getCuentas());
			return user;
		}
		else {
			return null;
		}
	}
	
	@Transactional
	public void create(@RequestBody Usuario u) {
		
		if(!repository.existsById(u.getIdUsuario())) {
			repository.save(u);
		}
	}
	
	@Transactional
	public DtoUsuario update(Usuario u,Integer i) {
		
		if(repository.existsById(i) && u.getIdUsuario() == i) {
			repository.save(u);
			return transformDTO(u);
		}else
			return null;
	}
	
	@Transactional
	public DtoUsuario delete (@PathVariable Integer id) {
		Optional<Usuario>user=repository.findById(id);
		
		if(user.isPresent()) {
			Usuario us= user.get();
			repository.delete(us);
			return transformDTO(us);
		}else return null;
	}

	@Transactional
	public List<MonopatinDto> getMonopatinesCercanos(double lat,double lon,double distancia){

		String url = "http://localhost:8081/monopatin/cercanos?latitud="+lat+"&longitud="+lon+"&distancia="+distancia;

		ResponseEntity<List> response = template.getForEntity(url,List.class);
		if(response.getStatusCode().is2xxSuccessful()){
			return response.getBody();
		}else{
			return new ArrayList<>();
		}

	}

	private DtoUsuario transformDTO(Usuario u){
		return new DtoUsuario(u.getIdUsuario(),u.getNombre(), u.getApellido(), u.getMail(),u.getNumTelefono(),u.getCuentas());
	}
}
