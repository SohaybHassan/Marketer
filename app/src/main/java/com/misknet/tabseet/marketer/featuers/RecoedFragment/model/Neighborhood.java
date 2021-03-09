package com.misknet.tabseet.marketer.featuers.RecoedFragment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Neighborhood {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("neighborhood_name")
    @Expose
    private String neighborhoodName;
    @SerializedName("city_id")
    @Expose
    private String cityId;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNeighborhoodName() {
        return neighborhoodName;
    }

    public void setNeighborhoodName(String neighborhoodName) {
        this.neighborhoodName = neighborhoodName;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

}