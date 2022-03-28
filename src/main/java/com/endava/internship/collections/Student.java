package com.endava.internship.collections;

import java.time.LocalDate;
import java.util.Objects;

/**
 * The class that defines the element that will be contained by your collection
 */
public class Student implements Comparable<Student> //TODO consider implementing any interfaces necessary for your collection
{
    private final String name;
    private final LocalDate dateOfBirth;
    private final String details;

    public Student(String name, LocalDate dateOfBirth, String details) {
        if(Objects.isNull(name)) {
            throw new NullPointerException("The name can not be null");
        }

        if(Objects.isNull(dateOfBirth)) {
            throw new NullPointerException("Date of birth can not be null");
        }

        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.details = details;
    }

    public String getName() { return name; }

    public LocalDate getDateOfBirth() { return dateOfBirth; }

    public String getDetails() { return details; }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Student student = (Student) o;
        return name.equals(student.name) && dateOfBirth.equals(student.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dateOfBirth);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", details='" + details + '\'' +
                '}';
    }

    @Override
    public int compareTo(Student st) {
        if(Objects.isNull(st)){
            throw new NullPointerException();
        }
        int compareName = this.getName().compareTo(st.getName());
        return compareName == 0 ?  this.getDateOfBirth().compareTo(st.getDateOfBirth()) : compareName;
    }

    /*
    TODO consider overriding any methods for this object to function properly within a collection:
        1. A student is considered unique by a combination of their name and dateOfBirth
        2. Student names are sorted alphabetically, if two students have the same name, then the older one is
        placed before the younger student in an ordered student list.
    */
}
