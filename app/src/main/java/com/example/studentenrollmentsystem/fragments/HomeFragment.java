package com.example.studentenrollmentsystem.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.studentenrollmentsystem.R;
import com.example.studentenrollmentsystem.activities.EnrollmentActivity;

public class HomeFragment extends Fragment {

TextView tv_enroll_now;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initValues();
        setListener();

    }

    private void initValues()
    {
        tv_enroll_now = getView().findViewById(R.id.tvEnrollNow);
    }

    private void setListener()
    {
        tv_enroll_now.setOnClickListener(click -> {
            String username = getActivity().getIntent().getStringExtra("username");
            String password = getActivity().getIntent().getStringExtra("password");

           Intent intent = new Intent(getContext(), EnrollmentActivity.class);
           intent.putExtra("username", username);
           intent.putExtra("password", password);
           startActivity(intent);
        });

    }

}