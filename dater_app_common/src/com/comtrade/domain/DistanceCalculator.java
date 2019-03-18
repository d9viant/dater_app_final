package com.comtrade.domain;


public class DistanceCalculator {
    private static User userOne;
    private static User userTwo;
    private static double distanceInBetween;

    public DistanceCalculator() {

    }

    public DistanceCalculator(User userOne, User userTwo) {
        DistanceCalculator.userOne = userOne;
        DistanceCalculator.userTwo = userTwo;
        DistanceCalculation();
    }


    private double DistanceCalculation() {
            double lat1 = userOne.getL().getLatitude();
            double lon1 = userOne.getL().getLongitude();
            double lat2 = userTwo.getL().getLatitude();
            double lon2 = userTwo.getL().getLatitude();
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

    public static double getDistanceInBetween() {
        return distanceInBetween;
    }



}
