package mk.world.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="country_language")
@IdClass(CountryLanguageId.class)
public class CountryLanguage implements Serializable {

    @Id
    @Column(name="country_code")
    private String countryCode;

    @Id
    private String language;

    private Double percentage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_code", nullable = false, insertable = false, updatable = false)
    private Country country;

    public String getLanguage() {
        return language;
    }

    public Double getPercentage() {
        return percentage;
    }

    public CountryLanguage(String countryCode, String language, Double percentage, Country country) {
        this.countryCode = countryCode;
        this.language = language;
        this.percentage = percentage;
        this.country = country;
    }
}
