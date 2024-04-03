package com.example.studentenrollmentsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class StudentViewPdfActivity extends AppCompatActivity
{

    private PDFView pdfView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_view_pdf);
        initValues();

        pdfView.fromAsset(String.format("%s%s.pdf", getLastName(),getFirstName())).load();

    }

    private void initValues()
    {
        pdfView = findViewById(R.id.pdfView);
    }

    private String getFirstName()
    {
        return getIntent().getStringExtra("fname");
    }

    private String getLastName()
    {
        return getIntent().getStringExtra("lname");
    }


}