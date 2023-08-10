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
        zipCodesMap.putIfAbsent(zipCode.getZip_code(), zipCode);
        if (zipCodesMap.containsKey(zipCode.getZip_code())) {
            zipCodesMap.get(zipCode.getZip_code()).getSettlements().add(settlement);
        }
    }

    public ZipCode getZipCode(String zipCode) {
        System.out.println(zipCodesMap.size());
        if(!zipCodesMap.containsKey(zipCode)) {
            throw new ZipCodeNotFoundException("No se encontro el codigo postal:"+zipCode);
        }
        return zipCodesMap.get(zipCode);
    }

}


