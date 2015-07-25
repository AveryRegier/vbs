package com.github.averyregier.vbs.parser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by avery on 7/15/15.
 */
public class VBSForm {
    private Parent parent;
    private List<Child> children = new ArrayList<>(3);
    private Address address;
    private Parent emergencyContact;

    public Parent getParent() {
        return parent;
    }


    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public void addChild(Child child) {
        children.add(child);
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public void setEmergencyContact(Parent emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public Parent getEmergencyContact() {
        return emergencyContact;
    }

    public List<Child> getChildren() {
        return children;
    }
}
