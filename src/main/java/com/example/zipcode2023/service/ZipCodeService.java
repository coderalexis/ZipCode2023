package com.example.zipcode2023.service;

import com.example.zipcode2023.exception.ZipCodeNotFoundException;
import com.example.zipcode2023.model.Settlements;
import com.example.zipcode2023.model.ZipCode;
import org.springframework.stereotype.Service;
import java.util.concurrent.ConcurrentHashMap;
@Service
public class ZipCodeService {

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

}


