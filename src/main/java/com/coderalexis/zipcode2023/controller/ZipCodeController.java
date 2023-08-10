package com.coderalexis.zipcode2023.controller;

import com.coderalexis.zipcode2023.model.Settlements;
import com.coderalexis.zipcode2023.model.ZipCode;
import com.coderalexis.zipcode2023.service.ZipCodeService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.regex.Pattern;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST})
public class ZipCodeController {

    private final ZipCodeService zipCodeService;

    public ZipCodeController(ZipCodeService zipCodeService) {
        this.zipCodeService = zipCodeService;
    }

    @GetMapping(value="/zip-code/{zipcode}", produces = "application/json")
    public ResponseEntity<ZipCode> zipcodes(@PathVariable("zipcode") String zipcode) {
        return new ResponseEntity<>(zipCodeService.getZipCode(zipcode), HttpStatus.OK);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initialConfiguration() {
        int countLine = 0;
        String filePath = "/home/CPdescarga.txt";
        BufferedReader buffer;
        String line;
        try {
            buffer = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.ISO_8859_1));
            try {
                while ((line = buffer.readLine()) != null) {
                    countLine++;
                    if (countLine < 2)
                        continue;

                    String separator = Pattern.quote("|");
                    String[] words = line.split(separator);
                    ZipCode zipcode = new ZipCode();
                    zipcode.setZip_code(words[0]);
                    zipcode.setLocality(words[4]);
                    zipcode.setFederal_entity(words[5]);

                    Settlements settlements = new Settlements();
                    settlements.setName(words[1]);
                    settlements.setZone_type((words.length < 15) ? words[words.length - 1] : words[words.length - 2]);
                    settlements.setSettlement_type(words[2]);

                    zipcode.setMunicipality(words[3]);
                    zipcode.setSettlements(new ArrayList<>());

                    zipCodeService.addZipCode(zipcode, settlements);
                }
                buffer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}


