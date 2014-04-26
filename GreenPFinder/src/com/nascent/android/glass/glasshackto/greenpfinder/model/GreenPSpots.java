/*
 * Copyright (C) 2013 The Android Open Source Project
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

import com.nascent.android.glass.glasshackto.greenpfinder.R;
import com.nascent.android.glass.glasshackto.greenpfinder.util.MathUtils;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * This class provides access to a list of hard-coded Green P spots (located in
 * {@code res/raw/landmarks.json}) that will appear on the compass when the user is near them.
 */
public class GreenPSpots {

    private static final String TAG = GreenPSpots.class.getSimpleName();

    /**
     * The threshold used to display a landmark on the compass.
     */
    private static final double MAX_DISTANCE_KM = 10;

    /**
     * The list of Green P parking lots loaded from resources.
     */
    private final ArrayList<ParkingLot> mParkingLots;

    /**
     * Initializes a new {@code ParkingLot} object by loading the parking lots from the resource
     * bundle.
     */
    public GreenPSpots(Context context) {
        mParkingLots = new ArrayList<ParkingLot>();

        // This class will be instantiated on the service's main thread, and doing I/O on the
        // main thread can be dangerous if it will block for a noticeable amount of time. In
        // this case, we assume that the landmark data will be small enough that there is not
        // a significant penalty to the application. If the landmark data were much larger,
        // we may want to load it in the background instead.
        String jsonString = readLandmarksResource(context);
        populatePlaceList(jsonString);
    }

    /**
     * Gets a list of the ten closest nearby parking lots. This 
     * function will never return null.
     */
    public List<ParkingLot> getClosestParkingLots(double latitude, double longitude) {
        ArrayList<ParkingLot> nearbyPlaces = new ArrayList<ParkingLot>();

        // TODO: fetch list of closest parking lots by distance
        for (ParkingLot knownPlace : mParkingLots) {
            if (MathUtils.getDistance(latitude, longitude,
                    knownPlace.getLatitude(), knownPlace.getLongitude()) <= MAX_DISTANCE_KM) {
                nearbyPlaces.add(knownPlace);
            }
        }

        return nearbyPlaces;
    }

    /**
     * Populates the internal places list from places found in a JSON string. This string should
     * contain a root object with a "landmarks" property that is an array of objects that represent
     * places. A place has three properties: name, latitude, and longitude.
     */
    private void populatePlaceList(String jsonString) {
        try {
            JSONObject json = new JSONObject(jsonString);
            JSONArray array = json.optJSONArray("landmarks");

            if (array != null) {
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object = array.optJSONObject(i);
                    ParkingLot parkingLot = jsonObjectToParkingLot(object);
                    if (parkingLot != null) {
                        mParkingLots.add(parkingLot);
                    }
                }
            }
        } catch (JSONException e) {
            Log.e(TAG, "Could not parse landmarks JSON string", e);
        }
    }

    /**
     * Converts a JSON object that represents a place into a {@link Place} object.
     */
    private ParkingLot jsonObjectToParkingLot(JSONObject object) {
        String name = object.optString("name");
        double latitude = object.optDouble("latitude", Double.NaN);
        double longitude = object.optDouble("longitude", Double.NaN);

        if (!name.isEmpty() && !Double.isNaN(latitude) && !Double.isNaN(longitude)) {
            return new ParkingLot(0, latitude, longitude, name, "");
        } else {
            return null;
        }
    }

    /**
     * Reads the text from {@code res/raw/landmarks.json} and returns it as a string.
     */
    private static String readLandmarksResource(Context context) {
        InputStream is = context.getResources().openRawResource(R.raw.landmarks);
        StringBuffer buffer = new StringBuffer();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
                buffer.append('\n');
            }
        } catch (IOException e) {
            Log.e(TAG, "Could not read landmarks resource", e);
            return null;
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    Log.e(TAG, "Could not close landmarks resource stream", e);
                }
            }
        }

        return buffer.toString();
    }
}
