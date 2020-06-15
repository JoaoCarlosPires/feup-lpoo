package com.aor.refactoring.example6;

public class Location {

    public String locationLatitude;
    public String locationLongitude;
    public String locationName;

    public Location(String locationLatitude, String locationLongitude, String locationName) {
        this.locationLatitude = locationLatitude;
        this.locationLongitude = locationLongitude;
        this.locationName = locationName;
    }

    public String getLocationLatitude() {
        return locationLatitude;
    }

    public String getLocationLongitude() {
        return locationLongitude;
    }

    public String getLocationName() {
        return locationName;
    }
}
