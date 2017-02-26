package pl.lenda.marcin.country.info.ws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import pl.lenda.marcin.country.info.service.CountryNameSanitizer;
import pl.lenda.marcin.country.info.ws.wsdl.CountryISOCode;
import pl.lenda.marcin.country.info.ws.wsdl.CountryISOCodeResponse;
import pl.lenda.marcin.country.info.ws.wsdl.FullCountryInfo;
import pl.lenda.marcin.country.info.ws.wsdl.FullCountryInfoResponse;


public class CountryClient extends WebServiceGatewaySupport {

    private static final Logger logger = LoggerFactory.getLogger(CountryClient.class);
    private final CountryNameSanitizer countryNameSanitizer;


    public CountryClient(CountryNameSanitizer countryNameSanitizer) {
        this.countryNameSanitizer = countryNameSanitizer;
    }


    public CountryISOCodeResponse getCountryIsoCode(String countryName) {

        String preparedCountryName = countryNameSanitizer.sanitizeCountryName(countryName);

        CountryISOCode request = new CountryISOCode();
        request.setSCountryName(preparedCountryName);

        logger.info("Requesting country ISO code by {}", preparedCountryName);

        return executeWsRequest(request, CountryISOCodeResponse.class);
    }


    public FullCountryInfoResponse getFullCountryInfo(String countryIsoCode) {
        FullCountryInfo request = new FullCountryInfo();
        request.setSCountryISOCode(countryIsoCode);

        logger.info("Requesting full country info by ISO code {}", countryIsoCode);

        return executeWsRequest(request, FullCountryInfoResponse.class);
    }


    private <T> T executeWsRequest(Object request, Class<T> clazz) {
        return clazz.cast(getWebServiceTemplate()
                .marshalSendAndReceive(
                        getDefaultUri(),
                        request,
                        new SoapActionCallback(getDefaultUri() + "/CountryISOCodeResponse")));
    }
}
