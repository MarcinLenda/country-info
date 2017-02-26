package pl.lenda.marcin.country.info.service;

import org.junit.Test;
import static org.fest.assertions.Assertions.assertThat;


public class CountryNameSanitizerTests {


    @Test
    public void countryNameSanitizer(){

        CountryNameSanitizer countryNameSanitizer = new CountryNameSanitizer();
        String nameCountry = "pOlaNd";

        String result = countryNameSanitizer.sanitizeCountryName(nameCountry);

        validateCountryNameSanitizerResult(result);
    }

    private void validateCountryNameSanitizerResult(String result){
        assertThat(result).isEqualTo("Poland");
    }
}
