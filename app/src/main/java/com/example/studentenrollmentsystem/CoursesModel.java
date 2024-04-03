package com.example.studentenrollmentsystem;

import java.util.ArrayList;
import java.util.List;

public class CoursesModel
{
    private String courseName;
    private String teacher;
    private String day;

    public CoursesModel(String courseName, String day, String teacher)
    {
        this.courseName = courseName;
        this.teacher = teacher;
        this.day = day;
    }

    public String getCourseName()
    {
        return courseName;
    }

    public String getTeacher()
    {
        return teacher;
    }

    public String getDay()
    {
        return day;
    }
}

