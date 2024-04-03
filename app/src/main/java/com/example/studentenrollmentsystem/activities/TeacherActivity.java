package com.example.studentenrollmentsystem.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.studentenrollmentsystem.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class TeacherActivity extends AppCompatActivity {

    private RecyclerView rv_teacher;
    private BottomNavigationView bnv_teacher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        initValues();
        setListener();
    }

    private void initValues()
    {
        rv_teacher = findViewById(R.id.rvStudentList);

        bnv_teacher = findViewById(R.id.bnvTeacher);
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

}