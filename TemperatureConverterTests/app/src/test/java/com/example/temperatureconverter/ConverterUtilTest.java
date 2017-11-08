package com.example.temperatureconverter;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Patrick on 11/7/2017.
 */

public class ConverterUtilTest {

    @Test
    public void addition_isCorrect_CtoF() throws Exception {
        float boilingPointFActual = ConverterUtil.convertCelsiusToFahrenheit(100);
        float boilingPointFExpected = 212;
        assertEquals(boilingPointFExpected, boilingPointFActual, 0.001);
    }

    @Test
    public void addition_isCorrect_FtoC() throws Exception {
        float boilingPointCActual = ConverterUtil.convertFahrenheitToCelsius(212);
        float boilingPointCExpected = 100;
        assertEquals(boilingPointCExpected, boilingPointCActual, 0.001);
    }

}
