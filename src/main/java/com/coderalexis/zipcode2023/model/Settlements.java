package com.coderalexis.zipcode2023.model;

import lombok.Data;
import java.io.Serializable;

@Data
public class Settlements implements Serializable{
    private String settlement_Name;
    private String zone_type;
    private String settlement_type;

    public Settlements(String name, String zoneType, String settlementType) {
        this.settlement_Name = name;
        this.zone_type = zoneType;
        this.settlement_type = settlementType;
    }
}
