package com.MicroservicioViaje;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.MicroservicioViaje.utils.CargaDeDatos;

@SpringBootApplication
public class MicroservicioViajeApplication {
	
	@Autowired
	private static CargaDeDatos csv;

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioViajeApplication.class, args);
	}
	
	/*public void run(String [] args) throws Exception{
		csv.readCsvFile();
	}*/

}
