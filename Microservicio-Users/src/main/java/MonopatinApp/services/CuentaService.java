package MonopatinApp.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import MonopatinApp.Dto.DtoCuenta;
import MonopatinApp.entities.Cuenta;
import MonopatinApp.repositories.CuentaRepository;
import jakarta.transaction.Transactional;

@Service
public class CuentaService {
	
	@Autowired
	CuentaRepository repository;
	
	@Transactional
	public List<DtoCuenta>getAll(){
		
		/*ArrayList<DtoCuenta>dto= new ArrayList<>();
		List<Cuenta>u= repository.findAll();
		
		for(int i=0;i<u.size();i++) {
			Cuenta c= u.get(i);
			DtoCuenta cDto= new DtoCuenta(c.getIdCuenta(),c.getSaldo(),c.getFechaAlta());
			dto.add(cDto);
		}
		return dto;*/
	    List<Cuenta> cuentas = repository.findAll();
	    
	    List<DtoCuenta> dtos = cuentas.stream()
	        .map(cuenta -> new DtoCuenta(cuenta.getIdCuenta(), cuenta.getSaldo(), cuenta.getFechaAlta()))
	        .collect(Collectors.toList());
	    
	    return dtos;
		
	}
	
	@Transactional
	public DtoCuenta getById(@PathVariable Integer id) {
		
		Optional<Cuenta>cu=repository.findById(id);
		
		if(cu.isPresent()) {
			Cuenta cuenta=cu.get();
			DtoCuenta dCuenta= new DtoCuenta(cuenta.getIdCuenta(),cuenta.getSaldo(),cuenta.getFechaAlta());
			return dCuenta;
		}
		else {
			return null;
		}
	}
	
	@Transactional
	public DtoCuenta create(@RequestBody Cuenta c) {

		if(!repository.existsById(c.getIdCuenta())) {
			repository.save(c);
			return transformDTO(c);
		}else{
			return null;
		}
	}
	
	@Transactional
	public DtoCuenta update(Cuenta c,int id) {
		
		if(repository.existsById(c.getIdCuenta()) && c.getIdCuenta() == id) {
			repository.save(c);
			return transformDTO(c);
		}else return null;
	}
	
	@Transactional
	public DtoCuenta delete (int id) throws Exception {
		Optional<Cuenta>cuenta=repository.findById(id);

		if(cuenta.isPresent()){

			try {
				repository.deleteById(id);
				return transformDTO(cuenta.get());
			}catch (Exception e){
				throw new Exception();
			}

		}else return null;

	}
	private DtoCuenta transformDTO(Cuenta c){
		return 	new DtoCuenta(c.getIdCuenta(),c.getSaldo(),c.getFechaAlta());
	}
}
