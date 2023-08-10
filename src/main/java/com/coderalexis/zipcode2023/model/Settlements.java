package com.coderalexis.zipcode2023.model;

import lombok.Data;
import java.io.Serializable;

@Data
public class Settlements implements Serializable{
    private String name;
    private String zone_type;
    private String settlement_type;
}
