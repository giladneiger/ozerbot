package com.psu;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;

/**
 * Created by Omri on 8/3/2018
 */
public class FeatureNeigerTest {

    private FeatureHandler featureHandler = new FeatureNeiger();

    @Test
    public void handle_returnNonEmptyString() {
        String answer = featureHandler.handle();

        assertThat(answer.length(), is(not(0)));
    }
}
