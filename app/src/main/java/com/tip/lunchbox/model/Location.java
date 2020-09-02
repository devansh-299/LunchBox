package com.tip.lunchbox.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location {

    @SerializedName("address")
    @Expose
    private String address;
    
    @SerializedName("locality")
    @Expose
    private String locality;
    
    @SerializedName("city")
    @Expose
    private String city;
    
    @SerializedName("latitude")
    @Expose
    private String latitude;
    
    @SerializedName("longitude")
    @Expose
    private String longitude;
    
    @SerializedName("zipcode")
    @Expose
    private String zipcode;
    
    @SerializedName("country_id")
    @Expose
    private String countryId;

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getAddress() {
        return address;
    }

    public String getLocality() {
        return locality;
    }

    public String getCity() {
        return city;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getCountryId() {
        return countryId;
    }
}
