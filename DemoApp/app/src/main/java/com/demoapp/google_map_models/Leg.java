package com.demoapp.google_map_models;

/**
 * Created by android on 15/3/16.
 */

import java.util.ArrayList;
import java.util.List;

public class Leg {

    private Duration duration;
    private List<Step> steps = new ArrayList<Step>();


    /**
     * @return The duration
     */
    public Duration getDuration() {
        return duration;
    }

    /**
     * @param duration The duration
     */
    public void setDuration(Duration duration) {
        this.duration = duration;
    }


    /**
     * @return The steps
     */
    public List<Step> getSteps() {
        return steps;
    }

    /**
     * @param steps The steps
     */
    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

}