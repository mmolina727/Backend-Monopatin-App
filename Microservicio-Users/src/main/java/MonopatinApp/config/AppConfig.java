package MonopatinApp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
	
	public RestTemplate createRestTemplate() {
		return new RestTemplate();
	}

}
