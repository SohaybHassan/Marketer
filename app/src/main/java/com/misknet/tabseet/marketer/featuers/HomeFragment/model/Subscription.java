package com.misknet.tabseet.marketer.featuers.HomeFragment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Subscription {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("assessment_desc")
    @Expose
    private Object assessmentDesc;
    @SerializedName("unit_count")
    @Expose
    private int unitCount;
    @SerializedName("duration_in_months")
    @Expose
    private int durationInMonths;
    @SerializedName("cost")
    @Expose
    private int cost;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("address_details")
    @Expose
    private String addressDetails;
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("end_date")
    @Expose
    private String endDate;
    @SerializedName("regular_visits_num")
    @Expose
    private int regularVisitsNum;
    @SerializedName("optional_visits_num")
    @Expose
    private int optionalVisitsNum;
    @SerializedName("client_id")
    @Expose
    private int clientId;
    @SerializedName("building_cat_id")
    @Expose
    private int buildingCatId;
    @SerializedName("building_type_id")
    @Expose
    private int buildingTypeId;
    @SerializedName("plan_id")
    @Expose
    private int planId;
    @SerializedName("city_id")
    @Expose
    private int cityId;
    @SerializedName("neighborhood_id")
    @Expose
    private int neighborhoodId;
    @SerializedName("payment_method_id")
    @Expose
    private int paymentMethodId;
    @SerializedName("supervisor_id")
    @Expose
    private Object supervisorId;
    @SerializedName("is_installment")
    @Expose
    private String isInstallment;
    @SerializedName("remain_regular")
    @Expose
    private int remainRegular;
    @SerializedName("remain_optional")
    @Expose
    private int remainOptional;
    @SerializedName("city")
    @Expose
    private City city;
    @SerializedName("group_id")
    @Expose
    private String groupId;
    @SerializedName("building_cat")
    @Expose
    private BuildingCat buildingCat;
    @SerializedName("building_type")
    @Expose
    private BuildingType buildingType;
    @SerializedName("neighborhood")
    @Expose
    private Neighborhood neighborhood;
    @SerializedName("client")
    @Expose
    private Client client;
    @SerializedName("group")
    @Expose
    private Group group;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Neighborhood getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(Neighborhood neighborhood) {
        this.neighborhood = neighborhood;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getAssessmentDesc() {
        return assessmentDesc;
    }

    public void setAssessmentDesc(Object assessmentDesc) {
        this.assessmentDesc = assessmentDesc;
    }

    public int getUnitCount() {
        return unitCount;
    }

    public void setUnitCount(int unitCount) {
        this.unitCount = unitCount;
    }

    public int getDurationInMonths() {
        return durationInMonths;
    }

    public void setDurationInMonths(int durationInMonths) {
        this.durationInMonths = durationInMonths;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getAddressDetails() {
        return addressDetails;
    }

    public void setAddressDetails(String addressDetails) {
        this.addressDetails = addressDetails;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getRegularVisitsNum() {
        return regularVisitsNum;
    }

    public void setRegularVisitsNum(int regularVisitsNum) {
        this.regularVisitsNum = regularVisitsNum;
    }

    public int getOptionalVisitsNum() {
        return optionalVisitsNum;
    }

    public void setOptionalVisitsNum(int optionalVisitsNum) {
        this.optionalVisitsNum = optionalVisitsNum;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getBuildingCatId() {
        return buildingCatId;
    }

    public void setBuildingCatId(int buildingCatId) {
        this.buildingCatId = buildingCatId;
    }

    public int getBuildingTypeId() {
        return buildingTypeId;
    }

    public void setBuildingTypeId(int buildingTypeId) {
        this.buildingTypeId = buildingTypeId;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getNeighborhoodId() {
        return neighborhoodId;
    }

    public void setNeighborhoodId(int neighborhoodId) {
        this.neighborhoodId = neighborhoodId;
    }

    public int getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(int paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public Object getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(Object supervisorId) {
        this.supervisorId = supervisorId;
    }

    public String getIsInstallment() {
        return isInstallment;
    }

    public void setIsInstallment(String isInstallment) {
        this.isInstallment = isInstallment;
    }

    public int getRemainRegular() {
        return remainRegular;
    }

    public void setRemainRegular(int remainRegular) {
        this.remainRegular = remainRegular;
    }

    public int getRemainOptional() {
        return remainOptional;
    }

    public void setRemainOptional(int remainOptional) {
        this.remainOptional = remainOptional;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public BuildingCat getBuildingCat() {
        return buildingCat;
    }

    public void setBuildingCat(BuildingCat buildingCat) {
        this.buildingCat = buildingCat;
    }

    public BuildingType getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(BuildingType buildingType) {
        this.buildingType = buildingType;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}