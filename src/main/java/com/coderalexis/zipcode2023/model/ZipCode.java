package com.coderalexis.zipcode2023.model;

import lombok.Data;
import java.util.ArrayList;

@Data
public class ZipCode {
    private String zip_code;
    private String locality;
    private String federal_entity;
    private ArrayList<Settlements> settlements;
    private String municipality;

    public ZipCode(String zipCode, String locality, String federalEntity, String municipality) {
        this.zip_code = zipCode;
        this.locality = locality;
        this.federal_entity = federalEntity;
        this.municipality = municipality;
        this.settlements = new ArrayList<>();
    }

}
