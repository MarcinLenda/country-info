package pl.lenda.marcin.country.info.config;

import pl.lenda.marcin.country.info.service.CountryNameSanitizer;
import pl.lenda.marcin.country.info.ws.CountryClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;


@Configuration
public class WsConfiguration {


    @Value("${ws.country.client.url}")
    private String wsUrl;


    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("pl.lenda.marcin.country.info.ws.wsdl");

        return marshaller;
    }


    @Bean
    public CountryClient countryClient(Jaxb2Marshaller marshaller, CountryNameSanitizer countryNameSanitizer) {
        CountryClient client = new CountryClient(countryNameSanitizer);
        client.setDefaultUri(wsUrl);
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);

        return client;
    }
}
