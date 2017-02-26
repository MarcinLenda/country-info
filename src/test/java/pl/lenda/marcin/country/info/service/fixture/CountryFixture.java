package pl.lenda.marcin.country.info.service.fixture;

import pl.lenda.marcin.country.info.repository.Country;

/**
 * Created by Promar on 26.02.2017.
 */
public class CountryFixture {


    public static Country country(){
        Country country = new Country("Poland","PL","Warsaw","PLN");
        return country;
    }
}
