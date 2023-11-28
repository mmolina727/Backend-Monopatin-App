package com.MicroservicioViaje.utils;


import com.MicroservicioViaje.entities.Viaje;
import com.MicroservicioViaje.repositories.ViajeRepository;
import jakarta.annotation.PostConstruct;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class CargaDeDatos {
    @Autowired
    private final ViajeRepository vRepository;
    public CargaDeDatos(ViajeRepository n) {
        this.vRepository = n;
    }
    @PostConstruct
    public void readCsvFile() {
        try {
            FileReader fileReader = new FileReader("C:\\Users\\joseg\\IdeaProjects\\Arquitecturas\\DAOProyect\\Microservicios\\Microservicio-Viaje\\src\\main\\resources\\viaje.csv");
            CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withHeader());
            for (CSVRecord record : csvParser) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                int idC = Integer.parseInt(record.get("id_cuenta"));
                int idM = Integer.parseInt(record.get("id_monopatin"));
                Date fIni = dateFormat.parse(record.get("fecha_inicio"));
                Date fFin = dateFormat.parse(record.get("fecha_fin"));
                double kmRec = Double.parseDouble(record.get("km_recorridos"));
                double precio = Double.parseDouble(record.get("precio"));
                double tExt = Double.parseDouble(record.get("tarifa_extra"));

                Viaje v = new Viaje(idC,idM,fIni,precio,tExt);
                v.setKm_recorridos(kmRec);
                v.setFecha_fin(fFin);

                vRepository.save(v);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
} /*
            */