package com.example.studentenrollmentsystem;

import java.util.Date;

public class StudentAdapterModel
{
    private int id;
    private String firstName;
    private String lastName;
    private String middleName;
   private String course;

    public StudentAdapterModel(int id, String firstName, String lastName, String middleName, String course)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.course = course;
    }

    public int getId()
    {
        return id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getMiddleName()
    {
        return middleName;
    }

    public String getCourse()
    {
        return course;
    }

    public String getStudentName()
    {
        return firstName + " " + middleName + " " + lastName;
    }
}
