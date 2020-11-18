package com.tip.lunchbox.utilities;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class LocationHelper {

    /**
     *
     * @param context context using which {@link Geocoder} object is instantiated.
     * @param latitude latitude of the user's location
     * @param longitude longitude of the user's location
     * @param addressLineNumber 0 for cityName, 1 for stateName and 2 for countryName
     * @return String containing the name of address corresponding to the line number
     */
    public static String getUserAddressLine(
            Context context, String latitude, String longitude, int addressLineNumber)
            throws IOException {
        Double lat = Double.parseDouble(latitude);
        Double lon = Double.parseDouble(longitude);
        return getUserAddressLine(context, lat, lon, addressLineNumber);
    }

    // Overloaded function
    public static String getUserAddressLine(
            Context context, Double latitude, Double longitude, int addressLineNumber)
            throws IOException {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
        // in case of invalid addressLineNumber, return cityName
        String defaultOutput = addresses.get(0).getAddressLine(0);
        if (addressLineNumber == 0 || addressLineNumber == 1 || addressLineNumber == 2) {
            return addresses.get(0).getAddressLine(addressLineNumber);
        }
        return defaultOutput;
    }
}
