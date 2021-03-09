package com.misknet.tabseet.marketer.featuers.RecoedFragment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OldBuildingType {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("building_cat_id")
    @Expose
    private int buildingCatId;
    @SerializedName("building_name")
    @Expose
    private String buildingName;
    @SerializedName("unit")
    @Expose
    private String unit;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBuildingCatId() {
        return buildingCatId;
    }

    public void setBuildingCatId(int buildingCatId) {
        this.buildingCatId = buildingCatId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

}
