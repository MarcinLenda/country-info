package pl.lenda.marcin.country.info.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import pl.lenda.marcin.country.info.rest.model.CountryInfoModel;
import pl.lenda.marcin.country.info.service.CountryInfoCache;
import pl.lenda.marcin.country.info.ws.CountryClient;
import pl.lenda.marcin.country.info.ws.wsdl.CountryISOCodeResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.lenda.marcin.country.info.ws.wsdl.FullCountryInfoResponse;
import java.util.function.Supplier;


@Controller
@RequestMapping(CountryController.PATH)
public class CountryController {


    public static final String PATH = "/country/name/";
    private final CountryClient countryClient;
    private final CountryInfoCache countryInfoCache;


    @Autowired
    public CountryController(CountryClient countryClient, CountryInfoCache countryInfoCache) {
        this.countryClient = countryClient;
        this.countryInfoCache = countryInfoCache;
    }


    @RequestMapping(method = RequestMethod.GET, value = "{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CountryInfoModel> getCountryInfo(@PathVariable("name") String countryName) {
        return countryInfoCache.maybeCountryInfo(countryName)
                .map(CountryInfoModel::of)
                .map(ResponseEntity::ok)
                .orElseGet(soapSupplier(countryName));
    }


    private Supplier<ResponseEntity<CountryInfoModel>> soapSupplier(String countryName) {
        return () -> {
            CountryISOCodeResponse countryIsoCodeResponse = countryClient.getCountryIsoCode(countryName);
            return countryIsoCodeResponse.getCountryISOCodeResult()
                    .map(countryClient::getFullCountryInfo)
                    .map(FullCountryInfoResponse::getFullCountryInfoResult)
                    .map(countryInfoCache::push)
                    .map(CountryInfoModel::of)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
        };
    }
}
