package com.github.averyregier.vbs.parser;

/**
 * Created by avery on 7/15/15.
 */
public class Parent extends Person {
    private String email;
    private String primaryPhone;
    private String secondaryPhone;
    private String relationship;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrimaryPhone() {
        return primaryPhone;
    }

    public void setPrimaryPhone(String primaryPhone) {
        this.primaryPhone = primaryPhone;
    }

    public void setSecondaryPhone(String secondaryPhone) {
        this.secondaryPhone = secondaryPhone;
    }

    public String getSecondaryPhone() {
        return secondaryPhone;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getRelationship() {
        return relationship;
    }
}
