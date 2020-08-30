package com.tip.lunchbox.model;

import com.google.gson.annotations.SerializedName;

public class Location {

    @SerializedName("address")
        private String address;
    
    @SerializedName("locality")
        private String locality;
    
    @SerializedName("city")
        private String city;
    
    @SerializedName("latitude")
        private String latitude;
    
    @SerializedName("longitude")
        private String longitude;
    
    @SerializedName("zipcode")
        private String zipcode;
    
    @SerializedName("country_id")
        private String countryId;

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
