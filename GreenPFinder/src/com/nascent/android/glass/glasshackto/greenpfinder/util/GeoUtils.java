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

package com.nascent.android.glass.glasshackto.greenpfinder.util;

import java.util.ArrayList;

import com.nascent.android.glass.glasshackto.greenpfinder.model.LatLongCoordinate;

/**
 * A utility class containing helper methods for geo-location.
 */
public class GeoUtils {

    
    /**
     * Takes a list of (latitude,longitude) coordinates and a point of origin and finds the closest point in the list.
     *
     * @param origin point for comparison
     * @param points list of (latitude, longitude) coordinates
     * @return <LatLongCoordinate> closest point in the list
     */
    public static LatLongCoordinate closestPoint(LatLongCoordinate origin, ArrayList<LatLongCoordinate> points) {
    	double latitude1 = origin.getLatitude(),
    			longitude1 = origin.getLongitude(),
    			latitude2 = 0, longitude2 = 0, distanceToPoint = 0;
    	LatLongCoordinate closestPoint = null;
    	double distanceToClosestPoint = Double.POSITIVE_INFINITY;
    	
    	for (LatLongCoordinate latLongCoordinate : points) {
    		distanceToPoint = MathUtils.getDistance(latitude1, longitude1, latitude2, longitude2); 
			if (distanceToPoint < distanceToClosestPoint) {
				distanceToClosestPoint = distanceToPoint;
				closestPoint = latLongCoordinate;
			}
		}
    	
        return closestPoint;
    }
}
