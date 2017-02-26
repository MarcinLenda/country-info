package pl.lenda.marcin.country.info.rest.model;

public class CountryInfoModel {

    private final String name;
    private final String iso;
    private final String capital;
    private final String currency;


    private CountryInfoModel(String name, String iso, String capital, String currency) {
        this.name = name;
        this.iso = iso;
        this.capital = capital;
        this.currency = currency;
    }


    public String getName() {
        return name;
    }

    public String getIso() {
        return iso;
    }

    public String getCapital() {
        return capital;
    }

    public String getCurrency() {
        return currency;
    }

    public static CountryInfoModel of(CountryInfo info) {
        return new CountryInfoModel(
                info.getName(),
                info.getIso(),
                info.getCapital(),
                info.getCurrency());
    }
}
