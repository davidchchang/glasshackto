package com.nascent.android.glass.glasshackto.greenpfinder.model;

public class LatLongCoordinate {
	private final double mLatitude;
    private final double mLongitude;
    
    public LatLongCoordinate(double latitude, double longitude) {
        mLatitude = latitude;
        mLongitude = longitude;
    }

    /**
     * Gets the latitude of the place.
     *
     * @return the latitude of the place
     */
    public double getLatitude() {
        return mLatitude;
    }

    /**
     * Gets the longitude of the place.
     *
     * @return the longitude of the place
     */
    public double getLongitude() {
        return mLongitude;
    }
}
