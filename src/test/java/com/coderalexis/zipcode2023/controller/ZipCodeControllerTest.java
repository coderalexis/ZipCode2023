package com.coderalexis.zipcode2023.controller;

import com.coderalexis.zipcode2023.model.Settlements;
import com.coderalexis.zipcode2023.model.ZipCode;
import com.coderalexis.zipcode2023.service.ZipCodeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.is;
@WebMvcTest(ZipCodeController.class)
public class ZipCodeControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ZipCodeService zipCodeService;

    @BeforeEach
    public void setUp() {
        // Si quieres configurar algo antes de cada prueba
    }

    @Test
    public void testGetZipCode() throws Exception {
        // Simular la respuesta del servicio
        Settlements mockSettlements = new Settlements("Condesa","Urbano","Ciudad de México");
        ArrayList<Settlements> settlementsList = new ArrayList<>();
        settlementsList.add(mockSettlements);
        ZipCode mockZipCode = new ZipCode("06140","Ciudad de México", "Ciudad de México","Cuauhtémoc"); // Puedes poner atributos aquí si es necesario
        mockZipCode.setSettlements(settlementsList);
        when(zipCodeService.getZipCode("06140")).thenReturn(mockZipCode);

        MvcResult result = mockMvc.perform(get("/zip-code/06140")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        MockHttpServletResponse response = result.getResponse();
        // Aquí puedes agregar más verificaciones, como comprobar el contenido de la respuesta.
    }

}

