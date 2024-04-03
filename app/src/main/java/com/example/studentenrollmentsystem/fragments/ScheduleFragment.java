package com.example.studentenrollmentsystem.fragments;

import android.content.Intent;
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
import com.example.studentenrollmentsystem.StudentViewPdfActivity;
import com.google.android.material.button.MaterialButton;


public class ScheduleFragment extends Fragment
{
    String username, password;
    MaterialButton btnViewPdf;

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
        setListener();
    }

    private void initValues()
    {
        btnViewPdf = getView().findViewById(R.id.btnViewPdfSched);
        db = new DBHelper(this.getContext());
        tv_greetings = getView().findViewById(R.id.greetings_tv);
        tv_course = getView().findViewById(R.id.tv_course);
        username = getActivity().getIntent().getStringExtra("username");
        password = getActivity().getIntent().getStringExtra("password");
        Cursor c = db.GetStudentName(username, password);
        tv_greetings.setText("Hello, " + c.getString(1) + " " + c.getString(2) + " " + c.getString(3));
        if (c.getString(9) == null)
        {
            btnViewPdf.setVisibility(View.GONE);
            tv_course.setText("No course selected yet. Please enroll!");
        } else
        {
            btnViewPdf.setVisibility(View.VISIBLE);
            tv_course.setText("Course: " + c.getString(9));
        }

        db.close();

    }

    private void setListener()
    {
        btnViewPdf.setOnClickListener(viewSched ->
        {
            String[] studName = getStudenName();
            Intent intent = new Intent(getContext(), StudentViewPdfActivity.class);
            intent.putExtra("fname", studName[0]);
            intent.putExtra("lname", studName[1]);
            getContext().startActivity(intent);
        });
    }

    private String getUsernameExtra()
    {
        return getActivity().getIntent().getStringExtra("username");
    }

    private String getPasswordExtra()
    {
        return getActivity().getIntent().getStringExtra("password");
    }

    private String[] getStudenName()
    {
        db = new DBHelper(getContext());

        String[] studName = new String[2];
        Cursor cursor = db.GetStudentName(getUsernameExtra(), getPasswordExtra());
        if(cursor.moveToFirst())
        {
            String fname = cursor.getString(1);
            String lname = cursor.getString(3);

            studName[0] = fname;
            studName[1] = lname;
        }

        db.close();

        return studName;

    }





}