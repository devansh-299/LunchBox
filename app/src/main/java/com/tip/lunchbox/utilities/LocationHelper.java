package com.tip.lunchbox.utilities;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.tip.lunchbox.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import androidx.core.app.ActivityCompat;


public class LocationHelper {

    private static final String TAG = "LocationHelper";
    private static final int permissionCode = 100;
    private static Geocoder geocoder;

    /**
     *
     * @param context context using which {@link LocationManager} be instantiated
     *                and permissions will be asked.
     * @return used to denote the status of the method call. True for successful
     * location fetch, false otherwise.
     */
    public static boolean getLocation(Context context) {
        LocationManager locationManager =
                (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = locationManager.getBestProvider(criteria, false);

        if (ActivityCompat.checkSelfPermission(
                context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // request permission
            PermissionHelper.requestPermission(
                    context, Manifest.permission.ACCESS_FINE_LOCATION, permissionCode);
            return false;
        }

        if (provider == null) {
            Log.d(TAG, "provider not found");
            return false;
        }

        Location location = locationManager.getLastKnownLocation(provider);

        // Initialize the location fields
        if (location != null) {
            Log.d(TAG, "location fetch successful");
            String latitudeString = Double.toString(location.getLatitude());
            String longitudeString = Double.toString(location.getLongitude());

            // save longitude and latitude
            if (!TextUtils.isEmpty(latitudeString) && !TextUtils.isEmpty(longitudeString)) {
                SharedPreferencesUtil.setStringPreference(
                        context, Constants.PREF_USER_LATITUDE, latitudeString);
                SharedPreferencesUtil.setStringPreference(
                        context, Constants.PREF_USER_LONGITUDE, longitudeString);
                return true;
            }
        } else {
            Toast.makeText(context, R.string.error_location, Toast.LENGTH_SHORT).show();
        }
        return false;
    }


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
        geocoder = new Geocoder(context, Locale.getDefault());
        List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
        // in case of invalid addressLineNumber, return cityName
        String defaultOutput = addresses.get(0).getAddressLine(0);
        if (addressLineNumber == 0 || addressLineNumber == 1 || addressLineNumber == 2) {
            return addresses.get(0).getAddressLine(addressLineNumber);
        }
        return defaultOutput;
    }
}
