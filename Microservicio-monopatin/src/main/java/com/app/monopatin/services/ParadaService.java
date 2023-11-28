package com.app.monopatin.services;

import com.app.monopatin.dtos.ParadaDto;
import com.app.monopatin.entitys.Parada;
import com.app.monopatin.repositorys.ParadaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParadaService {

    @Autowired
    private ParadaRepository repository;


    @Transactional
    public ParadaDto getById(@PathVariable Integer id) {

        Optional<Parada> parada= repository.findById(id);
        System.out.println(parada);
        if(parada.isPresent()) {
            return (this.convertDto(parada.get()));
        }
        else {
            return null;
        }
    }

    @Transactional
    public ParadaDto create(Parada parada) throws Exception {
        try{
            repository.save(parada);
            return this.convertDto(parada);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public ParadaDto delete(@PathVariable int id) {

        Optional<Parada> optionalParada = repository.findById(id);

        if (optionalParada.isPresent()) {
            Parada parada = optionalParada.get();
            repository.deleteById(id);
            return this.convertDto(parada);
        } else {
            return null;
        }
    }


    @Transactional
    public List<ParadaDto>getAll(){
        List<Parada> paradas = repository.findAll();

        List<ParadaDto> dtos = paradas.stream()
                .map(parada -> new ParadaDto(parada.getId(), parada.getLatitud(), parada.getLongitud(), parada.getMonopatines().size()))
                .collect(Collectors.toList());

        return dtos;
    }

    @Transactional
    public void update(int id, ParadaDto paradaDto) {

        Optional<Parada> optionalParada = repository.findById(id);

        if(optionalParada!=null) {
            Parada parada = optionalParada.get();
            parada.setLatitud(paradaDto.getLatitud());
            parada.setLongitud(paradaDto.getLongitud());

            repository.save(parada);
        }
    }

    private ParadaDto convertDto(Parada parada) {

        return new ParadaDto(parada.getId(), parada.getLatitud(), parada.getLongitud(), parada.getMonopatines().size());
    }
}
