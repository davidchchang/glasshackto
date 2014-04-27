package com.nascent.android.glass.glasshackto.greenpfinder.model;

import java.util.Comparator;
import java.util.Date;

import android.util.Log;

public class GreenPSpotComparator implements Comparator<ParkingLot> {
	
	public GreenPSpotComparator() {
	}
	
	@Override
	public int compare(ParkingLot arg0, ParkingLot arg1) {
		Log.d("test", "sorting " + new Date());
		float distance1 = arg0.getDistanceFromReferencePoint();
		float distance2 = arg1.getDistanceFromReferencePoint();
		return (int) (distance1 - distance2);
	}

}
