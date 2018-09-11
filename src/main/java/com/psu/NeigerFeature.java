package com.psu;

import java.util.Random;

/**
 * Created by Omri on 8/3/2018
 */
public class NeigerFeature implements FeatureHandler {

    private static final int MAX_TIMES_TO_SAY_NEIGER = 9;

    @Override
    public String handle() {
        int timesToSayNeiger = getTimesToSayNeiger();
        return getNeigerString(timesToSayNeiger);
    }

    private int getTimesToSayNeiger() {
        Random random = new Random();
        return random.nextInt(MAX_TIMES_TO_SAY_NEIGER);
    }

    private String getNeigerString(int timesToSayNeiger) {
        StringBuilder stringBuilder = new StringBuilder("Neiger");
        for (int i = 0; i < timesToSayNeiger; i++) {
            stringBuilder.append(" Neiger");
        }
        return stringBuilder.toString();
    }
}
