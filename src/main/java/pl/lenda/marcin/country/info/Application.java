package pl.lenda.marcin.country.info;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import pl.lenda.marcin.country.info.config.PersistenceConfig;
import pl.lenda.marcin.country.info.config.WsConfiguration;

@SpringBootApplication(scanBasePackages = { "pl.lenda.marcin.country.info.rest", "pl.lenda.marcin.country.info.service" })
@Import(value = { WsConfiguration.class, PersistenceConfig.class})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
