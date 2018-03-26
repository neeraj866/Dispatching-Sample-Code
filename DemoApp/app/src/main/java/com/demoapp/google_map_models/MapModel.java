package com.demoapp.google_map_models;

/**
 * Created by android on 15/3/16.
 */
import java.util.ArrayList;
import java.util.List;

public class MapModel {

    private List<Route> routes = new ArrayList<Route>();
    private String status;

    /**
     *
     * @return
     * The routes
     */
    public List<Route> getRoutes() {
        return routes;
    }

    /**
     *
     * @param routes
     * The routes
     */
    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    /**
     *
     * @return
     * The status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

}