/*
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nascent.android.glass.glasshackto.greenpfinder.model;

/**
 * This class represents a point of interest that has geographical coordinates (latitude and
 * longitude) and an address that is displayed to the user.
 */
public class ParkingLot {

    private final LatLongCoordinate mCoordinate;
    private final String mAddress;
    private final String mRate;  
    private final int mId;
    
    // for sorting/caching performance
    private float mDistanceFromReferencePoint;

    /**
     * Initializes a new place with the specified coordinates and name.
     *
     * @param latitude the latitude of the place
     * @param longitude the longitude of the place
     * @param address the name of the place
     */
    public ParkingLot(int id, double latitude, double longitude, String address, String rate) {
        mCoordinate = new LatLongCoordinate(latitude, longitude);
        mAddress = address;
        mId = id;
        mRate = rate;
    }

    /**
     * Gets the latitude of the place.
     *
     * @return the latitude of the place
     */
    public double getLatitude() {
        return mCoordinate.getLatitude();
    }

    /**
     * Gets the longitude of the place.
     *
     * @return the longitude of the place
     */
    public double getLongitude() {
        return mCoordinate.getLongitude();
    }

    /**
     * Gets the address of the parking lot.
     *
     * @return the address of the parking lot
     */
    public String getAddress() {
        return mAddress;
    }
    

    /**
     * Gets the rate of the parking lot.
     *
     * @return the rate of the parking lot
     */
    public String getRate() {
        return mRate;
    }
    
    /**
     * Gets the ID of the parking lot.
     *
     * @return the ID of the parking lot
     */
    public int getId() {
        return mId;
    }
    
    public float getDistanceFromReferencePoint() {
    	return mDistanceFromReferencePoint;
    }
    
    public void setDistanceFromReferencePoint(float f) {
    	mDistanceFromReferencePoint = f;
    }
    
    public String toString() {
    	return this.getAddress() + " (" + this.getLatitude() + ", " + this.getLongitude() + ")";
    }
}
