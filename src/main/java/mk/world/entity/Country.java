package mk.world.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="country")
public class Country implements Serializable {

    @Id
    private String code;

    private String name;

    private String continent;

    private Long population;

    @Column(name="life_expectancy")
    private Double lifeExpectancy;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "country")
    private List<CountryLanguage> countryLanguage;

    public String getCode() {
        return code;
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

    public Double getLifeExpectancy() {
        return lifeExpectancy;
    }

    public List<CountryLanguage> getCountryLanguage() {
        return countryLanguage;
    }

    public Country(String code, String name, String continent, Long population, Double lifeExpectancy, List<CountryLanguage> countryLanguage) {
        this.code = code;
        this.name = name;
        this.continent = continent;
        this.population = population;
        this.lifeExpectancy = lifeExpectancy;
        this.countryLanguage = countryLanguage;
    }
}
