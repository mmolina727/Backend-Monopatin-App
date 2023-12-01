package Microservicioadmin.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import Microservicioadmin.Dto.DtoCuenta;
import Microservicioadmin.Dto.DtoMonopatin;
import Microservicioadmin.Dto.DtoParada;

import java.util.Date;
import java.util.List;

@Service
public class AdminService {
	
	@Autowired
	RestTemplate template;
	
	public void saveMonopatin(@RequestBody DtoMonopatin monopatin) {

		template.postForObject("http://localhost:8081/monopatin", monopatin, DtoMonopatin.class);
	}
	
	public void saveParada(@RequestBody DtoParada parada) {
		
		template.postForObject("http://localhost:8081/api/parada", parada, DtoParada.class);
	}
	
	public DtoCuenta saveCuenta(@RequestBody DtoCuenta cuenta) {
		return template.postForObject("http://localhost:8080/api/cuenta", cuenta, DtoCuenta.class);
	}
	
	public void deleteMonopatin(Integer id) {
		
		template.delete("http://localhost:8081/monopatin/"+id);
	}
	
	public void deleteParada(Integer id) {
		
		template.delete("http://localhost:8081/api/parada/"+id);
	}
	
	public void deleteCuenta(Integer id) {
		template.delete("http://localhost:8080/api/cuenta/"+id);
	}
	
	public void updatePrecio(double precio,String fecha) {
		template.put("http://localhost:8082/api/viaje/actualizar?precio="+precio+"&fecha="+fecha,null);
	}
	
	public double getRecaudacion(int a,int i,int f) {
		ResponseEntity<Double>response= template.getForEntity("http://localhost:8082/api/viaje/recaudacion?anio="+a+"&mesIni="+i+"&mesFin="+f ,Double.class);
		
		if(response.getStatusCode().is2xxSuccessful()) {
			Double recaudacion= response.getBody();
			return recaudacion;
		}
		else {
			return 0;
		}
	}
	
	public int getCantMonopatinDisponible() {
		ResponseEntity<Integer>response= template.getForEntity("http://localhost:8081/monopatin/disponibles", Integer.class);
		
		if(response.getStatusCode().is2xxSuccessful()) {
			Integer cantidad= response.getBody();
			return cantidad;
		}
		else {
			return -1;
		}
	}
	
	public int getCantMonopatinMantenimiento() {
		ResponseEntity<Integer>response= template.getForEntity("http://localhost:8081/monopatin/mantenimiento", Integer.class);
		
		if(response.getStatusCode().is2xxSuccessful()) {
			Integer cantidad= response.getBody();
			return cantidad;
		}
		else {
			return -1;
		}
	}

	public List<DtoMonopatin> getAllMonopatinesByYear(Integer year, Integer cantidad) throws Exception {
		String url = "http://localhost:8082/api/viaje/year/"+year+"?cantidad="+cantidad;
		try{
			ResponseEntity<List> response = template.getForEntity(url,List.class);

			if(response.getStatusCode().is4xxClientError()){

				return null;
			}else{
				return response.getBody();
			}
		}catch (Exception e){
			throw new Exception(e.getMessage());
		}
	}
}
