package com.github.averyregier.vbs.parser;

import java.util.List;

/**
 * Created by avery on 7/15/15.
 */
public class VBSFormParser {


    public VBSForm parse(String toParse) {
        List<FormField> formFields = new LineParser().parse(toParse);
        return map(formFields);
    }

    public VBSForm map(List<FormField> formFields) {
        VBSForm vbsForm = new VBSForm();
        Parent parent = new Parent();
        vbsForm.setParent(parent);
        parent.setGivenName(findUniqueValue(formFields, "Parent/Legal Guardian Name First Name"));
        parent.setSurname(findUniqueValue(formFields, "Parent/Legal Guardian Name Last Name"));
        parent.setEmail(findUniqueValue(formFields, "Parent/Guardian Email"));
        parent.setPrimaryPhone(findUniqueValue(formFields, "Primary Contact Phone Number"));
        parent.setSecondaryPhone(findUniqueValue(formFields, "Secondary Contact Phone Number"));

        Address address = new Address();
        address.setStreet(findUniqueValue(formFields, "Address: Street Address"));
        address.setStreet2(findUniqueValue(formFields, "Address: Other Address"));
        address.setCity(findUniqueValue(formFields, "Address: City"));
        address.setState(findUniqueValue(formFields, "Address: State/Province"));
        address.setRegion(findUniqueValue(formFields, "Address: Region"));
        address.setCountry(findUniqueValue(formFields, "Address: Country"));
        vbsForm.setAddress(address);

        Parent emergency = new Parent();
        emergency.setGivenName(findUniqueValue(formFields, "Emergency Contact Name: First Name"));
        emergency.setSurname(findUniqueValue(formFields, "Emergency Contact Name: Last Name"));
        emergency.setPrimaryPhone(findUniqueValue(formFields, "Emergency Contact Phone Number"));
        emergency.setRelationship(findUniqueValue(formFields, "Emergency Contact Relationship To Child"));
        vbsForm.setEmergencyContact(emergency);

        for(int i=1; i<=3; i++) {
            Child child = new Child();
            child.setGivenName(findNthValue(formFields, "Name of Child", "First Name", i));
            child.setSurname(findNthValue(formFields, "Name of Child", "Last Name", i));
            if(!child.isEmpty()) {
                String age = findNthValue(formFields, "Age", i);
                if(age != null && !age.isEmpty()) {
                    if(age.endsWith("th")) {
                        age = age.substring(0, age.length()-2);
                    }
                    child.setAge(Integer.parseInt(age.trim().split("//W+")[0]));
                }
                child.setGrade(findNthValue(formFields, "Last Grade Completed", i));
                child.setNote(findNthValue(formFields, "Notes", i));
                vbsForm.addChild(child);
            }
        }

        return vbsForm;
    }

    private String findUniqueValue(List<FormField> formFields, String fieldName) {
        return formFields.stream()
                .filter(f -> f.getKey().startsWith(fieldName))
                .findFirst()
                .map(FormField::getValue)
                .orElse(null);
    }

    private String findNthValue(List<FormField> formFields, String fieldName, int count) {
        return formFields.stream()
                .filter(f -> f.getKey().startsWith(fieldName))
                .skip(count - 1)
                .findFirst()
                .map(FormField::getValue)
                .orElse(null);
    }

    private String findNthValue(List<FormField> formFields, String firstPart, String secondPart, int count) {
        return formFields.stream()
                .filter(f -> f.getKey().startsWith(firstPart) && f.getKey().contains(secondPart))
                .skip(count-1)
                .findFirst()
                .map(FormField::getValue)
                .orElse(null);
    }
}
