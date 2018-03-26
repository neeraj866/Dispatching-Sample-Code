package com.demoapp.google_map_models;

/**
 * Created by android on 15/3/16.
 */
import java.util.ArrayList;
import java.util.List;

public class Route {

    private List<Leg> legs = new ArrayList<Leg>();


    /**
     *
     * @return
     * The legs
     */
    public List<Leg> getLegs() {
        return legs;
    }

    /**
     *
     * @param legs
     * The legs
     */
    public void setLegs(List<Leg> legs) {
        this.legs = legs;
    }


}