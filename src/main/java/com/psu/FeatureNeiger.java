package com.psu;

import java.util.Random;

/**
 * Created by Omri on 8/3/2018
 */
public class FeatureNeiger implements FeatureHandler {
    @Override
    public String handle() {
        Random random = new Random();
        int timesToSayNeiger = random.nextInt(9);
        StringBuilder stringBuilder = new StringBuilder("Neiger");
        for (int i = 0; i < timesToSayNeiger; i++) {
            stringBuilder.append(" Neiger");
        }
        return stringBuilder.toString();
    }
}
