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
}
