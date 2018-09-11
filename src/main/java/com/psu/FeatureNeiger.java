package com.psu;

import java.util.Random;

/**
 * Created by Omri on 8/3/2018
 */
public class FeatureNeiger implements FeatureHandler {
    @Override
    public String handle() {
        int timesToSayNeiger = getTimesToSayNeiger();
        return getNeigerString(timesToSayNeiger);
    }

    private int getTimesToSayNeiger() {
        Random random = new Random();
        return random.nextInt(9);
    }

    private String getNeigerString(int timesToSayNeiger) {
        StringBuilder stringBuilder = new StringBuilder("Neiger");
        for (int i = 0; i < timesToSayNeiger; i++) {
            stringBuilder.append(" Neiger");
        }
        return stringBuilder.toString();
    }
}
