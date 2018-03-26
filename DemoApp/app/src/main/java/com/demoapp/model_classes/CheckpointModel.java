package com.demoapp.model_classes;

import java.io.Serializable;

public class CheckpointModel implements Serializable {

    private String time;
    private String latitude;
    private String longitude;
    private String type;

    /**
     * No args constructor for use in serialization
     */
    public CheckpointModel() {
    }

    /**
     * @param time
     * @param longitude
     * @param latitude
     * @param type
     */
    public CheckpointModel(String time, String latitude, String longitude, String type) {
        this.time = time;
        this.latitude = latitude;
        this.longitude = longitude;
        this.type = type;
    }

    /**
     * @return The time
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time The time
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * @return The latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * @param latitude The latitude
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * @return The longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * @param longitude The longitude
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * @return The type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type The type
     */
    public void setType(String type) {
        this.type = type;
    }

}