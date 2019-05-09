package com.comtrade.domain;


public class DistanceCalculator {
    private static User userOne;
    private static User userTwo;
    private static double distanceInBetween;

    public DistanceCalculator() {

    }



    public static double DistanceCalculation(User userOne, User userTwo) {
        double lat1 = userOne.getLocation().getLatitude();
        double lon1 = userOne.getLocation().getLongitude();
        double lat2 = userTwo.getLocation().getLatitude();
        double lon2 = userTwo.getLocation().getLatitude();
            if ((lat1 == lat2) && (lon1 == lon2)) {
                distanceInBetween = 0;
            } else {
                double theta = lon1 - lon2;
                double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
                dist = Math.acos(dist);
                dist = Math.toDegrees(dist);
                dist = dist * 1.609344;
                distanceInBetween = dist;
            }


        return distanceInBetween;
    }

    public static User getUserOne() {
        return userOne;
    }

    public static void setUserOne(User userOne) {
        DistanceCalculator.userOne = userOne;
    }

    public static User getUserTwo() {
        return userTwo;
    }

    public static void setUserTwo(User userTwo) {
        DistanceCalculator.userTwo = userTwo;
    }

    public static double getDistanceInBetween(User u, User currentUser) {
        return distanceInBetween;
    }



}
