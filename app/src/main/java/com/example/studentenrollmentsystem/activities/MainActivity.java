package com.example.studentenrollmentsystem.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.studentenrollmentsystem.R;
import com.example.studentenrollmentsystem.fragments.HomeFragment;
import com.example.studentenrollmentsystem.fragments.ScheduleFragment;
import com.example.studentenrollmentsystem.fragments.TestFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import javax.security.auth.Subject;

public class MainActivity extends AppCompatActivity
{

    private BottomNavigationView bnv;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initValues();
        replaceFragment(new HomeFragment());
        setBnv();
    }

    private void initValues()
    {
        bnv = findViewById(R.id.bnvAppBar);
    }

    private void setBnv()
    {
        bnv.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if(id == R.id.menu_homes)
            {
                replaceFragment(new HomeFragment());
            }
            else if(id == R.id.menu_subject)
            {
                replaceFragment(new ScheduleFragment());
            }
            else if(id == R.id.menu_teacher)
            {
                replaceFragment(new TestFragment());
            }
            else if(id == R.id.menu_logout)
            {
                finish();
            }
            return true;
        });

    }

    private void replaceFragment(Fragment fragment)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }



}