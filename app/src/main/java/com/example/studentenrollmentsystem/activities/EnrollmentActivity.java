package com.example.studentenrollmentsystem.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.studentenrollmentsystem.CoursesModel;
import com.example.studentenrollmentsystem.DBHelper;
import com.example.studentenrollmentsystem.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentActivity extends AppCompatActivity {

    private Spinner sp_courses;
    private TextInputEditText et_subject_1, et_subject_2;
    private ChipGroup cg_group;
    private Chip c_webtech, c_nummethods, c_tax;
    private MaterialButton btn_enroll;
    private DBHelper dbHelper;
    private List<CoursesModel>  course1 = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrollment);
        initValues();
        setSpinner();
        setCourseSubject();
        checkChip1();
        setListener();

    }

    private void generatePdf(String fname, String lname, String studName, String course)
    {


//        File dir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "MyAppFolder");
        try

        {
            File pdfFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), String.format("%s%s.pdf", lname,fname));

            PdfWriter writer = new PdfWriter(new FileOutputStream(pdfFile));
            com.itextpdf.kernel.pdf.PdfDocument pdfDocument = new com.itextpdf.kernel.pdf.PdfDocument(writer);
            Document document = new Document(pdfDocument);

            document.add(new Paragraph(studName));
            document.add(new Paragraph(course));

            Table table = new Table(3);
            table.addCell("Subject");
            table.addCell("Day");
            table.addCell("Teacher");

            for(CoursesModel c : course1)
            {
                table.addCell(c.getCourseName());
                table.addCell(c.getDay());
                table.addCell(c.getTeacher());
            }

            table.setHorizontalAlignment(HorizontalAlignment.CENTER);
            document.add(table);
            document.close();


        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    private void setCourseData(String course)
    {
        course1 = new ArrayList<>();
        switch (course)
        {
            case "BSIT" :
                course1.add(new CoursesModel("Programming 1", "Mon", "No teacher"));
                course1.add(new CoursesModel("Programming 1", "Tue", "T. Mika"));
                course1.add(new CoursesModel("Programming 1", "Thu", "T. Rina"));
                course1.add(new CoursesModel("Programming 1", "Fri", "T. Rina & T. Mika"));

                course1.add(new CoursesModel("Data Structure", "Tue", "T. Mika"));
                course1.add(new CoursesModel("Data Structure", "Wed", "T. Rina"));
                course1.add(new CoursesModel("Data Structure", "Fri", "T. Rina & T. Mika"));
                break;

            case "BSCPE" :
                course1.add(new CoursesModel("Calculus", "Mon", "T. Paul"));
                course1.add(new CoursesModel("Calculus", "Wed", "T. Rina"));
                course1.add(new CoursesModel("Calculus", "Sat", "T. Mika & T. Paul"));

                course1.add(new CoursesModel("Computer Organization", "Sat", "T. Mika & T. Paul"));
                course1.add(new CoursesModel("Computer Organization", "Sat", "T. Mika & T. Paul"));
                break;

            case "BSA" :
                course1.add(new CoursesModel("Accounting 1", "Tue", "T. Paul"));
                course1.add(new CoursesModel("Accounting 1", "Fri", "No teacher"));
                course1.add(new CoursesModel("Accounting 1", "Sat", "T. Paul"));

                course1.add(new CoursesModel("Mathematics and Investment", "Mon", "T. Paul"));
                course1.add(new CoursesModel("Mathematics and Investment", "Tue", "T. Paul"));
                course1.add(new CoursesModel("Mathematics and Investment", "Wed", "No teacher"));
                course1.add(new CoursesModel("Mathematics and Investment", "Thu", "No teacher"));
                course1.add(new CoursesModel("Mathematics and Investment", "Fri", "No teacher"));
                course1.add(new CoursesModel("Mathematics and Investment", "Sat", "T. Paul"));
                break;
            default: break;
        }


            if(c_webtech.isChecked())
            {
                course1.add(new CoursesModel(c_webtech.getText().toString(), "NA", "NA"));
            }

           if(c_nummethods.isChecked())
           {
               course1.add(new CoursesModel(c_nummethods.getText().toString(), "NA", "NA"));
           }

           if(c_tax.isChecked())
           {
               course1.add(new CoursesModel(c_tax.getText().toString(), "NA", "NA"));
           }

    }

    private void checkChip1() {
        ChipGroup chipGroup = findViewById(R.id.cgSubjects);

        chipGroup.setOnCheckedStateChangeListener(new ChipGroup.OnCheckedStateChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull ChipGroup group, @NonNull List<Integer> checkedIds) {
                int checkedChipCount = checkedIds.size();

                for (int i = 0; i < group.getChildCount(); i++) {
                    View child = group.getChildAt(i);
                    if (child instanceof Chip) {
                        Chip chip = (Chip) child;
                        chip.setCheckable(true);
                    }
                }

                if (checkedChipCount > 2) {
                    int lastCheckedChipId = checkedIds.get(checkedChipCount - 1);
                    Chip lastCheckedChip = findViewById(lastCheckedChipId);
                    lastCheckedChip.setChecked(false);
                    lastCheckedChip.setCheckable(false);
                }
            }
        });
    }



    private void initValues()
    {
        sp_courses = findViewById(R.id.spCourses);

        cg_group = findViewById(R.id.cgSubjects);

        //ELECTIVE
        c_webtech = findViewById(R.id.cWebtech);
        c_nummethods = findViewById(R.id.cNumMethods);
        c_tax = findViewById(R.id.cTaxation);

        et_subject_1 = findViewById(R.id.etSubject1);
        et_subject_2 = findViewById(R.id.etSubject2);

        btn_enroll = findViewById(R.id.btnEnroll);

    }
    private void addSubjectProcess()
    {

        dbHelper = new DBHelper(this);

        String sub1 = et_subject_1.getText().toString().trim();
        String sub2 = et_subject_2.getText().toString().trim();

        String elec1 = c_webtech.isChecked() ? c_webtech.getText().toString() :
                c_tax.isChecked() ? c_tax.getText().toString() : null;

        String elec2 = c_nummethods.isChecked() ? c_nummethods.getText().toString() :
                c_tax.isChecked() ? c_tax.getText().toString() : null;

        if(dbHelper.addSubjects(sp_courses.getSelectedItem().toString(), sub1, sub2, elec1, elec2, getStudentId()))
        {
            Cursor cursor = dbHelper.GetAllData();
            while(cursor.moveToNext())
            {
                Cursor cursor1 = dbHelper.findStudent(getStudentId());
                if(cursor1.moveToFirst())
                {
                    String fname = cursor1.getString(1);
                    String mname = cursor1.getString(2);
                    String lname = cursor1.getString(3);

                    String studName = fname + " " + mname + " " + lname;
                    String course = cursor1.getString(9);

                    setCourseData(course);
                    generatePdf(fname, lname, studName, course);

                }
                else
                {
                    Toast.makeText(this, "no data found", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }




    private void confirmAction()
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm action");
        builder.setMessage("Do you want to enroll in this course?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                addSubjectProcess();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();



    }
    public void setListener()
    {
        btn_enroll.setOnClickListener(enroll -> {
           confirmAction();

        });
    }

    private void setCourseSubject()
    {


        sp_courses.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch(i)
                {
                    case 0 :
                        et_subject_1.setText("Programming 1");
                        et_subject_2.setText("Data Structure");
                        break;
                    case 1 :
                        et_subject_1.setText("Calculus");
                        et_subject_2.setText("Computer Organization");
                        break;
                    case 2 :
                        et_subject_1.setText("Accounting 1");
                        et_subject_2.setText("Mathematics and Investment");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private String getUserameExtra()
    {
        return getIntent().getStringExtra("username");
    }

    private String getPasswordExtra()
    {
        return getIntent().getStringExtra("password");
    }

    private int getStudentId()
    {
        dbHelper = new DBHelper(this);
        return dbHelper.findStudentId(getUserameExtra(), getPasswordExtra());
    }

    private void setSpinner()
    {
        ArrayAdapter<CharSequence> coursesAdapter = ArrayAdapter.createFromResource(this, R.array.courses, android.R.layout.simple_spinner_dropdown_item);
        coursesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_courses.setAdapter(coursesAdapter);
    }

}