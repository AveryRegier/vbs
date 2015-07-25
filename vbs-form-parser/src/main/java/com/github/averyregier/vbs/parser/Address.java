package com.github.averyregier.vbs.parser;

/**
 * Created by avery on 7/16/15.
 */
public class Address {
    private String street;
    private String street2;
    private String city;
    private String state;
    private String region;
    private String country;

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getStreet2() {
        return street2;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegion() {
        return region;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }
}
