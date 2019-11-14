package mk.world.controller;

import mk.world.dto.CountryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountryController {
        private final mk.world.service.CountryService countryService;

        @Autowired
        public CountryController(mk.world.service.CountryService countryService) {
            this.countryService = countryService;
        }

        @GetMapping("/{countryTag}")
        public CountryDTO getCountryInfo(@PathVariable(name = "countryTag") String countryTag) {
            return new CountryDTO(countryService.getCountryInfo(countryTag));
        }
}
