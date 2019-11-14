package mk.world.entity;

import java.io.Serializable;

public class CountryLanguageId implements Serializable {

    private String countryCode;

    private String language;

    public CountryLanguageId(String countryCode, String language) {
        this.countryCode = countryCode;
        this.language = language;
    }

    public CountryLanguageId() {}
}
