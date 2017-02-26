package pl.lenda.marcin.country.info.repository;

import pl.lenda.marcin.country.info.rest.model.CountryInfo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Country implements CountryInfo {


    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "NAME", nullable = false, unique = true)
    private String name;
    @Column(name = "ISO", nullable = false, unique = true)
    private String iso;
    @Column(name = "CAPITAL", nullable = false)
    private String capital;
    @Column(name = "CURRENCY", nullable = false)
    private String currency;


    private Country() {
    }


    public Country(String name, String iso, String capital, String currency) {
        this.name = name;
        this.iso = iso;
        this.capital = capital;
        this.currency = currency;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public static Country of(CountryInfo info) {
        return new Country(
                info.getName(),
                info.getIso(),
                info.getCapital(),
                info.getCurrency());
    }
}
