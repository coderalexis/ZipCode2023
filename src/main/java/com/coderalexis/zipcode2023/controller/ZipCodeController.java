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

}


