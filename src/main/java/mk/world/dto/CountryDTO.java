package mk.world.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import mk.world.entity.Country;
import mk.world.entity.CountryLanguage;

import java.util.Comparator;

public class CountryDTO {

    private String name;

    private String continent;

    private Long population;

    private Long lifeExpectancy;

    private String countryLanguage;

    public CountryDTO(Country country) {
        this.name = country.getName();
        this.continent = country.getContinent();
        this.population = country.getPopulation();
        this.lifeExpectancy = Math.round(country.getLifeExpectancy());
        this.countryLanguage = country.getCountryLanguage().stream()
                .max(Comparator.comparing(CountryLanguage::getPercentage))
                .get().getLanguage();
    }

    public String getName() {
        return name;
    }

    public String getContinent() {
        return continent;
    }

    public Long getPopulation() {
        return population;
    }

    @JsonProperty("life_expectancy")
    public Long getLifeExpectancy() {
        return lifeExpectancy;
    }

    @JsonProperty("country_language")
    public String getCountryLanguage() {
        return countryLanguage;
    }

}
