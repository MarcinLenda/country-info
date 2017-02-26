package pl.lenda.marcin.country.info.service;

import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;


@Component
public class CountryNameSanitizer {

    private static final String SEPARATOR = " ";


    public String sanitizeCountryName(String inputName) {
        return Arrays.stream(inputName.split(SEPARATOR))
                .map(insertCapitalLetter())
                .collect(Collectors.joining(SEPARATOR));
    }


    private Function<String, String> insertCapitalLetter() {
        return (countryPart) -> countryPart.toUpperCase().charAt(0) + countryPart.toLowerCase().substring(1);
    }
}
