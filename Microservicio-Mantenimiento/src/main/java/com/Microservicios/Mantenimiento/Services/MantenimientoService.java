package com.Microservicios.Mantenimiento.Services;

import com.Microservicios.Mantenimiento.Dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class MantenimientoService {
    @Autowired
    RestTemplate template;


    public List<Reporte> generateReport(boolean incluyePausa){
        List<DtoMonopatin> monopatinList = getAllMonopatines();
        List<Reporte> aux = new ArrayList<>();
        if(incluyePausa){
            for(DtoMonopatin dm:monopatinList){
                aux.add(new ReporteConPausa(dm.getId(),dm.getKilometros(),dm.getTiempoEnUso(),dm.getTiempoEnPausa()));
            }
        }else{
            for(DtoMonopatin dm:monopatinList){
                aux.add(new Reporte(dm.getId(),dm.getKilometros(),dm.getTiempoEnUso()));
            }
        }
        return aux;
    }
    public DtoMonopatin changeStatus(String newStatus,Integer idMono){
        String url = "http://localhost:8081/monopatin/";
        ResponseEntity<DtoMonopatin> response = template.getForEntity(url+idMono,DtoMonopatin.class);
        if(response.getStatusCode().is2xxSuccessful()){
            DtoMonopatin d = response.getBody();
            d.setEstado(newStatus);
            template.put(url+idMono,d);
            return d;
        }else{
            return null;
        }
    }

    private List<DtoMonopatin> getAllMonopatines(){
        String url = "http://localhost:8081/monopatin";
        ResponseEntity<List<DtoMonopatin>> response = template.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<DtoMonopatin>>() {}
        );
        return response.getBody();
    }
}
