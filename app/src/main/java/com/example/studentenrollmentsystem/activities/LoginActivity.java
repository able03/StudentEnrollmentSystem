package com.example.studentenrollmentsystem.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studentenrollmentsystem.CustomToast;
import com.example.studentenrollmentsystem.DBHelper;
import com.example.studentenrollmentsystem.R;
import com.example.studentenrollmentsystem.ValidationClass;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class LoginActivity extends AppCompatActivity
{
    private TextInputLayout lo_uname, lo_pass;
    private TextInputEditText et_uname, et_pass;
    private MaterialButton btn_login;
    private TextView tv_jump_to_register;
    private DBHelper dbHelper;
    private ValidationClass validation;
    private CustomToast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initValues();
        setListeners();
        addTeacherAccount();

    }

    private void generatePdf()
    {
        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(300,600,1).create();
        PdfDocument.Page page = document.startPage(pageInfo);

        Canvas canvas = page.getCanvas();
        Paint paint = new Paint();
        paint.setTextSize(12);

        canvas.drawText("Subject Schedule:", 10,20, paint);
        document.finishPage(page);

        File pdfFile = new File(Environment.getExternalStorageDirectory(), "my_schedule.pdf");
        try
        {
            document.writeTo(new FileOutputStream(pdfFile));
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        document.close();
    }


    private void initValues()
    {
        lo_uname = findViewById(R.id.loUsernameLogin);
        lo_pass = findViewById(R.id.loPasswordLogin);

        et_uname = findViewById(R.id.etUsernameLogin);
        et_pass = findViewById(R.id.etPasswordLogin);

        btn_login = findViewById(R.id.btnLogin);

        tv_jump_to_register = findViewById(R.id.tvJumpToRegister);

        validation = new ValidationClass();
        toast = new CustomToast();

    }

    private void setListeners()
    {
        btn_login.setOnClickListener(login -> {
            loginProcess();
        });

        tv_jump_to_register.setOnClickListener(jump -> {
            startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
        });

    }

    public void loginProcess()
    {
        dbHelper= new DBHelper(this);
        String uname = et_uname.getText().toString().trim();
        String pass = et_pass.getText().toString().trim();

        if(uname.isEmpty() || pass.isEmpty()){
            if(uname.isEmpty()){
                lo_uname.setErrorEnabled(true);
                lo_uname.setError("Username field is empty");

                et_uname.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        lo_uname.setErrorEnabled(false);
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
            }
            if(pass.isEmpty()){
                lo_pass.setErrorEnabled(true);
                lo_pass.setError("Password field is empty");

                et_pass.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        lo_pass.setErrorEnabled(false);
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
            }

        }else{
            if(dbHelper.checkLogin(uname, pass))
            {
                Intent intent;
                if(uname.substring(0,5).equals("ROS01"))
                {
                    intent = new Intent(this, TeacherActivity.class);
                }
                else
                {
                    intent = new Intent(this, MainActivity.class);
                }
                intent.putExtra("username", uname);
                intent.putExtra("password", pass);
                startActivity(intent);
            }else{
                toast.myToast(this, R.drawable.ic_failed, "Invalid account", "Failed");


            }
        }

    }


    public void addTeacherAccount()
    {
        dbHelper = new DBHelper(this);

        String tRinaUname = "ROS01rina";
        String tRinaPass = "Rina18!";

        String tPaulUname = "ROS01paul";
        String tPaulName = "Paul18!";

        String tMikaUname = "ROS01mika";
        String tMikaPass = "Mika18!";

        if(dbHelper.isTeacherNotRegistered(tRinaUname, tRinaPass))
        {
            dbHelper.addTeacherData("T. Rina", "BSIT", "BSCPE", null, "Wed", "Thu", "Fri", tRinaUname, tRinaPass);
        }

        if(dbHelper.isTeacherNotRegistered(tPaulUname, tPaulName))
        {
            dbHelper.addTeacherData("T. Paul", "BSA", "BSCPE", null, "Mon", "Tue", "Sat", tPaulUname, tPaulName);
        }

        if(dbHelper.isTeacherNotRegistered(tMikaUname, tMikaPass))
        {
            dbHelper.addTeacherData("T. Mika", "BSIT", "BSCPE", "BSBA", "Tue", "Fri", "Sat", tMikaUname, tMikaPass);
        }

        dbHelper.close();
    }

}