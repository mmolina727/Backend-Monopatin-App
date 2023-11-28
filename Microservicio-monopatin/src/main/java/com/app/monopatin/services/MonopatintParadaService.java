package com.app.monopatin.services;

import com.app.monopatin.entitys.Monopatin;
import com.app.monopatin.entitys.Parada;
import com.app.monopatin.repositorys.MonopatinRepository;
import com.app.monopatin.repositorys.ParadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonopatintParadaService {

    @Autowired
    private MonopatinRepository monopatinRepository;

    @Autowired
    private ParadaRepository paradaRepository;


    public void generarYGuardarMonopatinesYParadas() {
        for (int i = 1; i <= 10; i++) {
            Parada parada = createRandomParada();
            // Guarda la parada en la base de datos
            paradaRepository.save(parada);
        }

        // Luego, crea y guarda los monopatines asociados a las paradas
        for (int i = 1; i <= 10; i++) {
            Parada parada = paradaRepository.findById(i).orElse(null);
            if (parada != null) {
                Monopatin monopatin = createRandomMonopatin(i, parada);
                // Asocia el monopatín a la parada
                monopatin.setParada(parada);
                // Guarda el monopatín en la base de datos
                monopatinRepository.save(monopatin);
            }
        }
    }

    private Parada createRandomParada() {
        // Establece los datos ficticios de la parada
        String latitud = String.format("%.6f", Math.random() * 90);
        String longitud = String.format("%.6f", Math.random() * 180);
        // Otras propiedades de Parada
        Parada parada = new Parada(latitud, longitud);
        return parada;
    }

    private Monopatin createRandomMonopatin(int id, Parada parada) {
        // Genera datos ficticios para un Monopatin
        // Puedes personalizar estos valores según tus necesidades
        String[] estados = {"En uso", "Disponible", "En mantenimiento"};
        String estado = estados[(int) (Math.random() * estados.length)];

        String latitud = String.format("%.6f", Math.random() * 90);
        String longitud = String.format("%.6f", Math.random() * 180);

        Monopatin monopatin = new Monopatin(id, estado, latitud, longitud, parada);
        return monopatin;
    }
}
