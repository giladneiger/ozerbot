package com.psu;

import java.util.HashMap;

/**
 * Created by Omri on 8/18/2018
 */
public class OzerBot {  // todo add init method that gets all the features

    private HashMap<String, FeatureHandler> features = new HashMap<>();

    public OzerBot(HashMap<String, FeatureHandler> features) {
        this.features = features;
//        features.put("drunk", new DrunkFeature());  // example
    }

    public String sayToOzer(String featureName) {
        FeatureHandler feature = features.get(featureName);
        return feature.handle();
    }

}
