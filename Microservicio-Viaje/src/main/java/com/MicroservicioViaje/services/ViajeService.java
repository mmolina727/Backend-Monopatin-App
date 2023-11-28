package com.MicroservicioViaje.services;

import com.MicroservicioViaje.Dto.DtoCuenta;
import com.MicroservicioViaje.Dto.MonopatinDto;
import com.MicroservicioViaje.Dto.ViajeDto;
import com.MicroservicioViaje.entities.Viaje;
import com.MicroservicioViaje.repositories.ViajeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class ViajeService {
    @Autowired
    private ViajeRepository viajeRepository;

    @Autowired
    private RestTemplate restTemplate;
    
    
    @Transactional
    
    public void updatePrecio(double precio,Date fecha) {
    	viajeRepository.updatePrecio(precio, fecha);
    }
    
    @Transactional
    public double getRecaudacion(Date inicio,Date fin) {
    	try {
			return  viajeRepository.getRecaudacion(inicio,fin);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
    }
    
    @Transactional
    public ViajeDto getById(@PathVariable Integer id){
        Optional<Viaje> v = viajeRepository.findById(id);
        if(v.isPresent()){
            return transformDTO(v.get());
        }else {
            return null;
        }
    }
    @Transactional
    public List<ViajeDto> getAll(){
        List<ViajeDto> aux = new ArrayList<>();
        for(Viaje v: viajeRepository.findAll()){
            aux.add(transformDTO(v));
        }
        return aux;
    }

    @Transactional
    public ViajeDto create(Viaje v){
        DtoCuenta response_cuenta = restTemplate.getForObject("http://localhost:8080/api/cuenta/"+v.getId_cuenta(),DtoCuenta.class);
        MonopatinDto response_monopatin = restTemplate.getForObject("http://localhost:8081/monopatin/"+v.getId_monopatin(),MonopatinDto.class);
        try{
            v.setId_monopatin(response_monopatin.getId());
            v.setId_cuenta(response_cuenta.getIdCuenta());
            viajeRepository.save(v);
            return transformDTO(v);
        }catch (Exception exc){
            System.out.println();
            return null;
        }
    }
    @Transactional
    public ViajeDto update(Viaje v){
        Optional<Viaje> viajeViejo = viajeRepository.findById(v.getId_viaje());
        if(!viajeViejo.isPresent()){
            return null;
        }else{
            try {
                Viaje aux = viajeRepository.save(v);
                return transformDTO(aux);
            }catch (Exception e){
                return null;
            }
        }
    }
    @Transactional
    public ViajeDto delete(int id){
        Optional<Viaje> opc = viajeRepository.findById(id);
        if(opc.isPresent()){
            viajeRepository.delete(opc.get());
            return transformDTO(opc.get());
        }else{
            return null;
        }
    }
    private ViajeDto transformDTO(Viaje v){
        return new ViajeDto(v.getId_viaje(), v.getId_cuenta(),
                v.getId_monopatin(),v.getFecha_inicio(),v.getFecha_fin(),
                v.getKm_recorridos(),v.getPrecio(),v.getTarifa_extra());

    }

    public List<Integer> getAllViajesInYear(int year, int cant) {
        List<Integer> list = new ArrayList<>();
        list.addAll(viajeRepository.getAllIn(year,cant));
        return list;
    }

    public List<MonopatinDto> getAllMonopatines(List<Integer> list) {
        List<MonopatinDto> listaMonopatin = restTemplate.postForObject("http://localhost:8081/monopatin/inlist", list, List.class);
        return listaMonopatin;
    }
}
