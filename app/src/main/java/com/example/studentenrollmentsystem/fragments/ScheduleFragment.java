package com.example.studentenrollmentsystem.fragments;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.studentenrollmentsystem.DBHelper;
import com.example.studentenrollmentsystem.R;


public class ScheduleFragment extends Fragment
{
    String username,password;

    DBHelper db;

    TextView tv_greetings, tv_course;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_schedule, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        initValues();
    }

    private void initValues()
    {
        db = new DBHelper(this.getContext());
        tv_greetings = getView().findViewById(R.id.greetings_tv);
        tv_course = getView().findViewById(R.id.tv_course);
         username = getActivity().getIntent().getStringExtra("username");
         password = getActivity().getIntent().getStringExtra("password");
         Cursor c = db.GetStudentName(username, password);
         tv_greetings.setText("Hello, " + c.getString(1) + " " + c.getString(2) + " " + c.getString(3));
         if(c.getString(9) == null){
             tv_course.setText("No course selected yet. Please enroll!");
         }else{
             tv_course.setText("Course: " + c.getString(9));
         }


    }

    private String getUsernameExtra()
    {
        return getActivity().getIntent().getStringExtra("username");
    }


}