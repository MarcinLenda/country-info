package pl.lenda.marcin.country.info.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lenda.marcin.country.info.repository.Country;
import pl.lenda.marcin.country.info.repository.CountryRepository;
import pl.lenda.marcin.country.info.rest.model.CountryInfo;
import java.util.Optional;


@Service
public class CountryInfoCache {

    private static final Logger logger = LoggerFactory.getLogger(CountryInfoCache.class);
    private final CountryRepository repository;
    private final CountryNameSanitizer countryNameSanitizer;


    @Autowired
    public CountryInfoCache(CountryRepository repository,
                            CountryNameSanitizer countryNameSanitizer) {
        this.repository = repository;
        this.countryNameSanitizer = countryNameSanitizer;
    }


    public Optional<? extends CountryInfo> maybeCountryInfo(String countryName) {
        Optional<Country> maybeCountry = repository.findByName(countryNameSanitizer.sanitizeCountryName(countryName));
        maybeCountry.ifPresent(country -> logger.info("Country {} found in cache", country.getName()));
        return maybeCountry;
    }


    public CountryInfo push(CountryInfo countryInfo) {
        repository.save(Country.of(countryInfo));
        logger.info("Country {} pushed to cache", countryInfo.getName());
        return countryInfo;
    }
}
