package MonopatinApp;

import MonopatinApp.utils.CargaDeDatos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DemoApplication {
	
	@Autowired
	private static CargaDeDatos csv;
	

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
		
		//comentar el metodo luego de ejecutar la aplicacion por primera vez para evitar duplicacion de datos//
		//csv.readCsvFile();
	}

}
