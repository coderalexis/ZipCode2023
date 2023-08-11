package com.coderalexis.zipcode2023.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class ZipCodeFileReader {

    public List<String> readFileContents(String filePath) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.ISO_8859_1))) {
            String line;
            while ((line = buffer.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error leyendo el archivo de códigos postales.", e);
        }
        return lines;
    }
}
