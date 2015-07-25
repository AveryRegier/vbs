package com.github.averyregier.vbs.parser;

/**
 * Created by avery on 7/15/15.
 */
public class Child extends Person {
    private int age = -1;
    private String grade;
    private String note;

    public void setAge(int age) {
        this.age = age;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isEmpty() {
        return  isEmpty(getGivenName()) &&
                isEmpty(getSurname()) &&
                isEmpty(grade) &&
                isEmpty(note) &&
                age == -1;
    }

    private static boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    public int getAge() {
        return age;
    }

    public String getGrade() {
        return grade;
    }

    public String getNote() {
        return note;
    }
}
