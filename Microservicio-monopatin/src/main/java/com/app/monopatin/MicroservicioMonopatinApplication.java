package com.app.monopatin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.app.monopatin.utils.CargaDeDatos;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroservicioMonopatinApplication {
	
	@Autowired
	private static CargaDeDatos csv;

    public static void main(String[] args) {
        SpringApplication.run(MicroservicioMonopatinApplication.class, args);
    }
    
     //El metodo se  debe ejecutara solo por primera vez al correr la aplicacion, luego comentarlo para evitar duplicacion de datos//
    /*public void run(String[] args) throws Exception {
    	csv.readCsvFile();
    }*/

}
