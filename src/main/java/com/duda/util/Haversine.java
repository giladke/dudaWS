package com.duda.util;

public class Haversine {

    private static final int EARTH_RADIUS = 6371; // Approx Earth radius in KM

    public static double distance(double startLat, double startLong,
                                  double endLat, double endLong) {
        double dLatInRadians  = Math.toRadians((endLat - startLat));
        double dLongInRadians = Math.toRadians((endLong - startLong));

        double startLatRadians = Math.toRadians(startLat);
        double endLatRadians   = Math.toRadians(endLat);

        double a = haversin(dLatInRadians) + Math.cos(startLatRadians) * Math.cos(endLatRadians) * haversin(dLongInRadians);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c;
    }

    private static double haversin(double val) {
        return Math.pow(Math.sin(val / 2), 2);
    }
}
