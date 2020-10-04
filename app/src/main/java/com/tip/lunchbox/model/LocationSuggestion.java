package com.tip.lunchbox.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LocationSuggestion {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("country_id")
    @Expose
    private int countryId;
    @SerializedName("country_name")
    @Expose
    private String countryName;
    @SerializedName("country_flag_url")
    @Expose
    private String countryFlagUrl;
    @SerializedName("should_experiment_with")
    @Expose
    private int shouldExperimentWith;
    @SerializedName("has_go_out_tab")
    @Expose
    private int hasGoOutTab;
    @SerializedName("discovery_enabled")
    @Expose
    private int discoveryEnabled;
    @SerializedName("has_new_ad_format")
    @Expose
    private int hasNewAdFormat;
    @SerializedName("is_state")
    @Expose
    private int isState;
    @SerializedName("state_id")
    @Expose
    private int stateId;
    @SerializedName("state_name")
    @Expose
    private String stateName;
    @SerializedName("state_code")
    @Expose
    private String stateCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryFlagUrl() {
        return countryFlagUrl;
    }

    public void setCountryFlagUrl(String countryFlagUrl) {
        this.countryFlagUrl = countryFlagUrl;
    }

    public int getShouldExperimentWith() {
        return shouldExperimentWith;
    }

    public void setShouldExperimentWith(int shouldExperimentWith) {
        this.shouldExperimentWith = shouldExperimentWith;
    }

    public int getHasGoOutTab() {
        return hasGoOutTab;
    }

    public void setHasGoOutTab(int hasGoOutTab) {
        this.hasGoOutTab = hasGoOutTab;
    }

    public int getDiscoveryEnabled() {
        return discoveryEnabled;
    }

    public void setDiscoveryEnabled(int discoveryEnabled) {
        this.discoveryEnabled = discoveryEnabled;
    }

    public int getHasNewAdFormat() {
        return hasNewAdFormat;
    }

    public void setHasNewAdFormat(int hasNewAdFormat) {
        this.hasNewAdFormat = hasNewAdFormat;
    }

    public int getIsState() {
        return isState;
    }

    public void setIsState(int isState) {
        this.isState = isState;
    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

}
