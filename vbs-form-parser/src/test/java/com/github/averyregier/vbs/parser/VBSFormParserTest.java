package com.github.averyregier.vbs.parser;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by avery on 7/15/15.
 */
public class VBSFormParserTest {
    private VBSFormParser parser = new VBSFormParser();

    @Test
    public void parseParentGivenName() {
        String toParse = "Parent/Legal Guardian Name First Name: Bob";
        Parent person = parseParent(toParse);
        assertEquals("Bob", person.getGivenName());
    }

    @Test
    public void parseParentSurname() {
        String toParse = "Parent/Legal Guardian Name Last Name: Barker";
        Parent person = parseParent(toParse);
        assertEquals("Barker", person.getSurname());
    }

    @Test
    public void parseParentEmail() {
        String toParse = "Parent/Guardian Email:: a@b.com";
        Parent person = parseParent(toParse);
        assertEquals("a@b.com", person.getEmail());
    }

    @Test
    public void parseParentPhone() {
        String toParse = "Primary Contact Phone Number:: 555-555-5555 ext.";
        Parent person = parseParent(toParse);
        assertEquals("555-555-5555 ext.", person.getPrimaryPhone());
    }

    @Test
    public void parseParentSecondaryPhone() {
        String toParse = "Secondary Contact Phone Number:: 555-555-5555 ext.";
        Parent person = parseParent(toParse);
        assertEquals("555-555-5555 ext.", person.getSecondaryPhone());
    }

    private Parent parseParent(String toParse) {
        VBSForm result = parser.parse(toParse);
        Parent person = result.getParent();
        assertNotNull(person);
        return person;
    }

//    Address: Street Address: 33836 Amherst St
//    Address: Other Address:
//    Address: City: Des Moines
//    Address: State/Province: IA
//    Address: Zip/Postal Code: 50313
//    Address: Region:
//    Address: Country: United States
//    Emergency Contact Name: First Name: Sandra
//    Emergency Contact Name: Last Name: McIntire
//    Emergency Contact Phone Number:: 515-490-0774 ext.
//    Emergency Contact Relationship To Child: grandmother
//    Name of Child #1 First Name: Alivia
//    Name of Child #1 Last Name: McIntire
//    Age:: 5
//    Last Grade Completed:: preschoolna
//    Notes: (Allergies, Special Needs, etc...): na
//    Name of Child #2 First Name:
//    Name of Child #2 Last Name:
//    Age::
//    Last Grade Completed::
//    Notes: (Allergies, Special Needs, etc...):
//    Name of Child #3: First Name:
//    Name of Child #3: Last Name:
//    Age::
//    Last Grade Completed::
//    Notes: (Allergies, Special Needs, etc...):


}