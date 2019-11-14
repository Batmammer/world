package mk.world.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import mk.world.dto.CountryDTO;
import mk.world.entity.Country;
import mk.world.entity.CountryLanguage;
import mk.world.exception.CountryNotFoundException;
import mk.world.service.CountryService;
import org.hibernate.exception.JDBCConnectionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.postgresql.util.PSQLException;
import org.postgresql.util.ServerErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.sql.SQLException;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CountryController.class)
class CountryControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CountryService service;

    @DisplayName("Check happy path for BHR")
    @Test
    void getCountryInfoBHR() throws Exception {
        CountryLanguage bhrLang = new CountryLanguage("BHR", "Arabic", 99d, null);
        Country bhr = new Country("BHR", "Bahrain", "Asia", 617000l, 73d, Collections.singletonList(bhrLang));

        Mockito.when(service.getCountryInfo(bhr.getCode())).thenReturn(bhr);

        MvcResult mvcResult = mvc.perform(get("/BHR")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        assertThat(objectMapper.writeValueAsString(new CountryDTO(bhr)))
                .isEqualToIgnoringWhitespace(mvcResult.getResponse().getContentAsString());
    }

    @DisplayName("Check non existing country XXX")
    @Test
    void getCountryInfoXXX() throws Exception {
        Mockito.when(service.getCountryInfo("XXX")).thenThrow(new CountryNotFoundException("XXX"));

        MvcResult mvcResult = mvc.perform(get("/XXX")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError()).andReturn();
        assertThat("INVALID_COUNTRY_CODE")
                .isEqualToIgnoringWhitespace(mvcResult.getResponse().getContentAsString());
    }

    @DisplayName("Check db connection error")
    @Test
    void getCountryInfoConnectionError() throws Exception {
        Mockito.when(service.getCountryInfo(any())).thenThrow(new JDBCConnectionException("Connection", new SQLException()));

        MvcResult mvcResult = mvc.perform(get("/ERR")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError()).andReturn();
        assertThat("INTERNAL_ERROR")
                .isEqualToIgnoringWhitespace(mvcResult.getResponse().getContentAsString());
    }

}