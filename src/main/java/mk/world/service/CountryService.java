package mk.world.service;

import mk.world.entity.Country;
import mk.world.exception.CountryNotFoundException;
import mk.world.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryService {
        private final CountryRepository countryRepository;

        @Autowired
        public CountryService(CountryRepository countryRepository) {
            this.countryRepository = countryRepository;
        }

        public Country getCountryInfo(String countryTag) {
            return countryRepository.findById(countryTag)
                    .orElseThrow(() -> new CountryNotFoundException("Country not found " + countryTag));
        }
}
