/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.formatters;

import com.linhv.pojo.AdmissionType;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author prodi
 */
public class AdmissionTypeFormatter implements Formatter<AdmissionType>{

    @Override
    public String print(AdmissionType type, Locale locale) {
        return String.valueOf(type.getId());
    }

    @Override
    public AdmissionType parse(String typeId, Locale locale) throws ParseException {
        return new AdmissionType(Integer.parseInt(typeId));
    }

}
