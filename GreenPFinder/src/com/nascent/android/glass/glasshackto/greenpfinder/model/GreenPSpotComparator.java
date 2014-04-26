package com.nascent.android.glass.glasshackto.greenpfinder.model;

import java.util.Comparator;

import com.nascent.android.glass.glasshackto.greenpfinder.util.MathUtils;

public class GreenPSpotComparator implements Comparator<ParkingLot> {

	LatLongCoordinate mReferencePoint;
	
	public GreenPSpotComparator(LatLongCoordinate referencePoint) {
		mReferencePoint = referencePoint;
	}
	
	@Override
	public int compare(ParkingLot arg0, ParkingLot arg1) {
		return (int) (MathUtils.getDistance(mReferencePoint.getLatitude(), mReferencePoint.getLongitude(), 
				arg0.getLatitude(), arg0.getLongitude()) 
				- MathUtils.getDistance(mReferencePoint.getLatitude(), mReferencePoint.getLongitude(), 
				arg1.getLatitude(), arg1.getLongitude()));
	}

}
