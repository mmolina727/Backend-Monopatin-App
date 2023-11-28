package com.app.monopatin.services;

import com.app.monopatin.dtos.MonopatinDto;
import com.app.monopatin.dtos.ViajeDto;
import com.app.monopatin.entitys.Monopatin;
import com.app.monopatin.entitys.Parada;
import com.app.monopatin.repositorys.MonopatinRepository;
import com.app.monopatin.repositorys.ParadaRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MonopatinService {

    @Autowired
    private MonopatinRepository repository;
    @Autowired
    private ParadaRepository pRepository;
    
    @Autowired
    RestTemplate template;
    
    @Transactional
    
    public Integer getCantMonopatinDisponible() {
    	return repository.getCantMonopatinDisponible();
    }
    
    @Transactional
    
    public int getCantMonopatinMantenimiento() {
    	return repository.getCantMonopatinMantenimiento();
    }

    @Transactional
    public MonopatinDto getById(@PathVariable Integer id) {

        Optional<Monopatin> monopatin= repository.findById(id);

        if(monopatin.isPresent()) {
        	return this.convertDto(monopatin.get());
        }
        else {
        	return null;
        }
    }

    @SuppressWarnings("deprecation")
	@Transactional
    public void create(MonopatinDto monopatin) throws Exception {
        try{
        	if(!repository.existsById(monopatin.getId())) {
        	Parada parada=pRepository.getById(monopatin.getIdParada());
        	Monopatin m= new Monopatin(monopatin.getId(),monopatin.getEstado(),monopatin.getLatitud(),monopatin.getLongitud(),parada);
            repository.save(m);

        	}
            //this.convertDto(monopatin,id);
        }catch (Exception e){
        	e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public MonopatinDto delete(@PathVariable int id) {

        Optional<Monopatin> optionalMonopatin = repository.findById(id);

        if (optionalMonopatin.isPresent()) {
            Monopatin monopatin = optionalMonopatin.get();
            repository.deleteById(id);
            return convertDto(monopatin);
        }else{
            return null;
        }
    }

    @Transactional
    public List<MonopatinDto>getAll(){
        List<Monopatin> monopatines = repository.findAll();

        List<MonopatinDto> dtos = monopatines.stream()
                .map(monopatin -> new MonopatinDto(monopatin.getId(), monopatin.getEstado(), monopatin.getKilometros() ,monopatin.getLatitud(), monopatin.getLongitud(), monopatin.getTiempoEnUso(), monopatin.getTiempoEnPausa(),monopatin.getParada().getId()))
                .collect(Collectors.toList());

        return dtos;
    }

    @Transactional
    public void update(int id, MonopatinDto monopatinDto) {

        Optional<Monopatin> optionalMonopatin = repository.findById(id);
        Optional<Parada> optionalParada = pRepository.findById(monopatinDto.getIdParada());
        if(optionalMonopatin!=null) {
            Monopatin monopatin = optionalMonopatin.get();

            monopatin.setEstado(monopatinDto.getEstado());
            monopatin.setKilometros(monopatinDto.getKilometros());
            monopatin.setLatitud(monopatinDto.getLatitud());
            monopatin.setLongitud(monopatinDto.getLongitud());
            monopatin.setTiempoEnUso(monopatinDto.getTiempoEnUso());
            monopatin.setTiempoEnPausa(monopatinDto.getTiempoEnPausa());
            monopatin.setParada(optionalParada.get());
            repository.save(monopatin);
        }
    }

    @Transactional
    public List<MonopatinDto>getMonopatinesCercanosAMonopatin(double lat,double lon, double distancia){
        List<Monopatin> monopatines = repository.getMonopatinesCercanosAMonopatin(lat,lon, distancia);

        List<MonopatinDto> monopatinesCercanos = monopatines.stream()
                .map(monopatin -> new MonopatinDto(monopatin.getId(), monopatin.getEstado(), monopatin.getKilometros() ,monopatin.getLatitud(), monopatin.getLongitud(), monopatin.getTiempoEnUso(), monopatin.getTiempoEnPausa(),monopatin.getParada().getId()))
                .collect(Collectors.toList());

        return monopatinesCercanos;

    }

    private MonopatinDto convertDto(Monopatin monopatin) {
    	

        return new MonopatinDto(monopatin.getId(), monopatin.getEstado(), monopatin.getKilometros() ,monopatin.getLatitud(), monopatin.getLongitud(), monopatin.getTiempoEnUso(), monopatin.getTiempoEnPausa(),monopatin.getParada().getId());
    }

    public List<MonopatinDto> getAllIn(List<Integer> listaID) {
        List<Monopatin> monopatines = repository.getAllInList(listaID);
        List<MonopatinDto> aux = new ArrayList<>();
        for(Monopatin m:monopatines){
            aux.add(convertDto(m));
        }
        return aux;
    }
}
