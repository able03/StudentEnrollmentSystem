package com.example.studentenrollmentsystem.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.studentenrollmentsystem.DBHelper;
import com.example.studentenrollmentsystem.R;
import com.example.studentenrollmentsystem.StudentAdapter;
import com.example.studentenrollmentsystem.StudentAdapterModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class TeacherActivity extends AppCompatActivity {

    private RecyclerView rv_teacher;
    private BottomNavigationView bnv_teacher;
    private List<StudentAdapterModel> students;
    private StudentAdapter adapter;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        initValues();
        setListener();
        populateRV();
    }

    private void initValues()
    {
        rv_teacher = findViewById(R.id.rvStudentList);

        bnv_teacher = findViewById(R.id.bnvTeacher);
    }

    private List<StudentAdapterModel> getAllStudents()
    {
        dbHelper = new DBHelper(this);
        List<StudentAdapterModel> s = new ArrayList<>();

        Cursor cursor = dbHelper.GetAllData();
        while(cursor.moveToNext())
        {
            if(getUsernameExtra().equals("ROS01rina"))
            {
                int id = Integer.parseInt(cursor.getString(0));
                String course = cursor.getString(9);
                String fname = cursor.getString(1);
                String mname = cursor.getString(2);
                String lname = cursor.getString(3);

                if(course.equals("BSIT") || course.equals("BSCPE"))
                {
                    s.add(new StudentAdapterModel(id, fname, lname, mname, course));
                }
            }

            else if(getUsernameExtra().equals("ROS01paul"))
            {
                int id = Integer.parseInt(cursor.getString(0));
                String course = cursor.getString(9);
                String fname = cursor.getString(1);
                String mname = cursor.getString(2);
                String lname = cursor.getString(3);

                if(course.equals("BSA") || course.equals("BSCPE"))
                {
                    s.add(new StudentAdapterModel(id, fname, lname, mname, course));
                }
            }

            else if(getUsernameExtra().equals("ROS01mika"))
            {

                int id = Integer.parseInt(cursor.getString(0));
                String course = cursor.getString(9);
                String fname = cursor.getString(1);
                String mname = cursor.getString(2);
                String lname = cursor.getString(3);

                if(course.equals("BSIT") || course.equals("BSCPE"))
                {
                    s.add(new StudentAdapterModel(id, fname, lname, mname, course));
                }
            }


        }

        dbHelper.close();
        return s;




    }

    private void populateRV()
    {
        students = getAllStudents();
        adapter = new StudentAdapter(this);
        adapter.setStudents(students);
        rv_teacher.setAdapter(adapter);
        rv_teacher.setLayoutManager(new LinearLayoutManager(this));
    }




    private void setListener()
    {
        bnv_teacher.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId() == R.id.menu_logout_teacher)
                {
                    finish();
                }
                return true;
            }
        });
    }

    private String getUsernameExtra()
    {
        return getIntent().getStringExtra("username");
    }

    private String getPasswordExtra()
    {
        return getIntent().getStringExtra("password");
    }

}