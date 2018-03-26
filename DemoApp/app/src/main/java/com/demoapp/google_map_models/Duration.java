package com.demoapp.google_map_models;

/**
 * Created by android on 15/3/16.
 */


public class Duration {

    private String text;
    private Integer value;

    /**
     *
     * @return
     * The text
     */
    public String getText() {
        return text;
    }

    /**
     *
     * @param text
     * The text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     *
     * @return
     * The value
     */
    public Integer getValue() {
        return value;
    }

    /**
     *
     * @param value
     * The value
     */
    public void setValue(Integer value) {
        this.value = value;
    }

}