package com.example.studentenrollmentsystem;

import java.util.ArrayList;
import java.util.List;

public class StudentTestModel
{
    private String name;
    private String course;
    private List<CoursesModel> courses;
    private List<List<String>> schedule;
    private List<String> teachers;

    public StudentTestModel(String name, String course, List<String> teachers)
    {
        this.name = name;
        this.course = course;
        this.courses = new ArrayList<>();
        this.schedule = new ArrayList<>();
        this.teachers = teachers;
    }

    public String getName()
    {
        return name;
    }

    public String getCourse()
    {
        return course;
    }

    public void addCourses(CoursesModel course1)
    {
       courses.add(course1);
    }

    public List<CoursesModel> getCourses()
    {
        return courses;
    }

    public void addSched(String subject)
    {
    }


}
