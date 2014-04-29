package com.nascent.android.glass.glasshackto.greenpfinder.model;

import java.util.Comparator;

public class GreenPSpotComparator implements Comparator<ParkingLot> {
	
	public GreenPSpotComparator() {
	}
	
	@Override
	public int compare(ParkingLot arg0, ParkingLot arg1) {
		float distance1 = arg0.getDistanceFromReferencePoint();
		float distance2 = arg1.getDistanceFromReferencePoint();
		float difference = distance1 - distance2;
		if (difference > 0) {
			return 1;
		} else if (difference < 0) {
			return -1;
		}
		return 0;
	}

}
