package com.coderalexis.zipcode2023.service;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class ZipCodeServiceTest {

    @InjectMocks
    private ZipCodeService zipCodeService;

    @Mock
    private ZipCodeFileReader zipCodeFileReader;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoadZipCodesFromFile() {
        // Supongamos que el método readFileContents de zipCodeFileReader devuelve una lista con estas líneas:
        // Arrange
        List<String> mockLines = Arrays.asList(
                "HEADER LINE 1",
                "HEADER LINE 2",
                "01000|San Ángel|Colonia|Álvaro Obregón|Ciudad de México|Ciudad de México|01001|09|01001||09|010|0001|Urbano|01",
                "01010|Los Alpes|Colonia|Álvaro Obregón|Ciudad de México|Ciudad de México|01001|09|01001||09|010|0005|Urbano|01",
                "01020|Guadalupe Inn|Colonia|Álvaro Obregón|Ciudad de México|Ciudad de México|01001|09|01001||09|010|0006|Urbano|01"
        );
        when(zipCodeFileReader.readFileContents(null)).thenReturn(mockLines);

        // Act
        zipCodeService.loadZipCodesFromFile();
        // Assertions:
        assertEquals(3, zipCodeService.getTotalZipCodes(), "The total number of zip codes should be 2");
        assertNotNull(zipCodeService.getZipCode("01000"), "Zip code 01010 should be present");
        assertNotNull(zipCodeService.getZipCode("01010"), "Zip code 01010 should be present");
        assertNotNull(zipCodeService.getZipCode("01020"), "Zip code 01020 should be present");
    }

    // Puedes agregar más métodos de prueba para otros métodos de ZipCodeService
}
