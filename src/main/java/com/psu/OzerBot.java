package com.psu;

import java.util.Map;

/**
 * Created by Omri on 8/18/2018
 */
public class OzerBot {

    private Map<String, FeatureHandler> features;

    public OzerBot(Map<String, FeatureHandler> features) {
        this.features = features;
    }

    public String sayToOzer(String featureName) {
        FeatureHandler feature = features.get(featureName);
        return feature.handle();
    }

}
