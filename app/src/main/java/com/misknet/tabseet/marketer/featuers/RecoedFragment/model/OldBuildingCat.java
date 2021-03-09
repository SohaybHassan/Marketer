package com.misknet.tabseet.marketer.featuers.RecoedFragment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OldBuildingCat {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("building_cat_name")
    @Expose
    private String buildingCatName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBuildingCatName() {
        return buildingCatName;
    }

    public void setBuildingCatName(String buildingCatName) {
        this.buildingCatName = buildingCatName;
    }
}
