package com.coderalexis.zipcode2023.service;

import com.coderalexis.zipcode2023.model.Settlements;
import com.coderalexis.zipcode2023.model.ZipCode;
import com.coderalexis.zipcode2023.exception.ZipCodeNotFoundException;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ZipCodeService {

    private static final String SEPARATOR = Pattern.quote("|");
    private static final int SKIP_HEADER_LINES = 2;
    private static final int MINIMUM_EXPECTED_WORDS = 15;
    private static final int ZIP_CODE_INDEX = 0;
    private static final int LOCALITY_INDEX = 4;
    private static final int FEDERAL_ENTITY_INDEX = 5;
    private static final int MUNICIPALITY_INDEX = 3;

    private static final int SETTLEMENT_NAME_INDEX = 1;
    //private static final int SETTLEMENT_ZONE_TYPE_INDEX = 3;
    private static final int SETTLEMENT_SETTLEMENT_TYPE_INDEX = 5;


    private static final Logger LOGGER = LoggerFactory.getLogger(ZipCodeService.class);

    @Value("${zipcode.filePath:/home/CPdescarga.txt}")
    private String filePath;

    @Autowired
    private ZipCodeFileReader zipCodeFileReader;

    private final ConcurrentHashMap<String, ZipCode> zipCodesMap = new ConcurrentHashMap<>();

    public void addZipCode(ZipCode zipCode, Settlements settlement) {
        zipCodesMap.compute(zipCode.getZip_code(), (key, existingZipCode) -> {
            if (existingZipCode == null) {
                zipCode.getSettlements().add(settlement);
                return zipCode;
            } else {
                existingZipCode.getSettlements().add(settlement);
                return existingZipCode;
            }
        });
    }

    public ZipCode getZipCode(String zipCode) {
        if(!zipCodesMap.containsKey(zipCode)) {
            throw new ZipCodeNotFoundException("No se encontro el codigo postal:"+zipCode);
        }
        return zipCodesMap.get(zipCode);
    }

    @PostConstruct
    public void loadZipCodesFromFile() {
        LOGGER.info("Cargando códigos postales desde: {}", filePath);
        List<String> lines = zipCodeFileReader.readFileContents(filePath);
        lines.stream()
                .skip(SKIP_HEADER_LINES)
                .forEach(this::processLine);
        LOGGER.info("Total de códigos postales cargados: {}", getTotalZipCodes());
    }

    private void processLine(String line) {
        String[] words = line.split(SEPARATOR);
        ZipCode zipcode = createZipCodeFromWords(words);
        Settlements settlements = createSettlementFromWords(words);
        addZipCode(zipcode, settlements);
    }

    private ZipCode createZipCodeFromWords(String[] words) {
        return new ZipCode(
                words[ZIP_CODE_INDEX],
                words[LOCALITY_INDEX],
                words[FEDERAL_ENTITY_INDEX],
                words[MUNICIPALITY_INDEX]
        );
    }

    private Settlements createSettlementFromWords(String[] words) {
        return new Settlements(
                words[SETTLEMENT_NAME_INDEX],
                isShorterLine(words) ? words[words.length - 1] : words[words.length - 2],
                words[SETTLEMENT_SETTLEMENT_TYPE_INDEX]
        );
    }

    private boolean isShorterLine(String[] words) {
        return words.length < MINIMUM_EXPECTED_WORDS;
    }

    public int getTotalZipCodes() {
        return zipCodesMap.size();
    }
}


