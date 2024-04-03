package com.example.studentenrollmentsystem.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studentenrollmentsystem.CustomToast;
import com.example.studentenrollmentsystem.DBHelper;
import com.example.studentenrollmentsystem.R;
import com.example.studentenrollmentsystem.R;
import com.example.studentenrollmentsystem.ValidationClass;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RegistrationActivity extends AppCompatActivity {

    private String fn,ln,mn,cn,email,un,pw;
    String bday;
    private DBHelper db;
    String substr;
    private ValidationClass validation;
    private TextInputLayout lo_fname, lo_lname, lo_mname, lo_bday, lo_contact_no, lo_email, lo_uname, lo_pass;
    private TextInputEditText et_fname, et_lname, et_mname, et_bday, et_contact_no, et_email, et_uname, et_pass;
    private MaterialButton btn_register;
    private TextView tv_jump_to_login;
    private CustomToast toast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        iniValues();
        setListeners();
        listenET(et_fname, lo_fname);
        listenET(et_mname, lo_mname);
        listenET(et_lname, lo_lname);
        listenET(et_bday, lo_bday);
        listenET(et_contact_no, lo_contact_no);
        listenET(et_email, lo_email);
        listenET(et_uname, lo_uname);
        listenET(et_pass, lo_pass);

    }

    private void listenET(TextInputEditText et_view, TextInputLayout lo_et) {
        et_view.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                lo_et.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }


    private void iniValues()
    {
        lo_fname = findViewById(R.id.loFirstName);
        lo_lname = findViewById(R.id.loLastName);
        lo_mname = findViewById(R.id.loMiddleName);
        lo_bday = findViewById(R.id.loBirthday);
        lo_contact_no = findViewById(R.id.loControlNum);
        lo_email = findViewById(R.id.loEmail);
        lo_uname = findViewById(R.id.loUsername);
        lo_pass = findViewById(R.id.loPassword);

        et_fname = findViewById(R.id.etFirstName);
        et_lname = findViewById(R.id.etLastName);
        et_mname = findViewById(R.id.etMiddleName);
        et_bday = findViewById(R.id.etBirthday);
        et_contact_no = findViewById(R.id.etContactNum);
        et_email = findViewById(R.id.etEmail);
        et_uname = findViewById(R.id.etUsername);
        et_pass = findViewById(R.id.etPassword);

        btn_register = findViewById(R.id.btnRegister);

        tv_jump_to_login = findViewById(R.id.tvJumpToLogin);
        db = new DBHelper(this);
        validation = new ValidationClass();
        toast = new CustomToast();
    }

    private Date parseDate(String date)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        try
        {
            return simpleDateFormat.parse(date);
        } catch (ParseException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void GetInputValues(){
        fn = et_fname.getText().toString();
        mn = et_mname.getText().toString();
        ln = et_lname.getText().toString();
        bday = et_bday.getText().toString();
        cn = et_contact_no.getText().toString();
        email = et_email.getText().toString();
        un = et_uname.getText().toString();
        pw = et_pass.getText().toString();

    }


    public void RegistrationProcess(){
        GetInputValues();
        if(validation.IsStringEmpty(fn) || validation.IsStringEmpty(mn) || validation.IsStringEmpty(ln)
                || validation.IsStringEmpty(cn) || validation.IsStringEmpty(email) || validation.IsStringEmpty(un)
                || validation.IsStringEmpty(pw)){

        }else{
            if(validation.ContactNumberValidation(cn)){
                if(validation.EmailValidation(email)){
                    if(validation.UsernameValidation(un)){
                        if(validation.PasswordValidation(pw)){
                            if(db.AddData(fn,mn,ln,bday,cn,email,un,pw, null, null, null, null, null)){
                                toast.myToast(this, R.drawable.ic_btn_checked, "Successfully registered account", "Registration Success!");
                            }else{
                                toast.myToast(this, R.drawable.ic_failed, "Failed to register account", "Registration Failed!");
                            }
                        }else{
                            toast.myToast(this, R.drawable.ic_failed, "Failed to register account", "Password Invalid");
                        }

                    }else{
                        toast.myToast(this, R.drawable.ic_failed, "Failed to register account", "Username Invalid");
                    }
                }else{
                    toast.myToast(this, R.drawable.ic_failed, "Failed to register account", "Email Invalid");
                }
            }else{
                toast.myToast(this, R.drawable.ic_failed, "Failed to register account", "Contact Number Invalid");
                lo_contact_no.setErrorEnabled(true);
                lo_contact_no.setError("Contact Number must start with 0920");
            }
        }
    }



    private void setListeners()
    {
        btn_register.setOnClickListener(register -> {
            RegistrationProcess();

        });

        tv_jump_to_login.setOnClickListener(jump -> {
            finish();
        });

        lo_bday.setEndIconOnClickListener(bday -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                    et_bday.setText(String.format("%02d", month+1) + "/" + String.format("%02d", dayOfMonth) + "/" + String.format("%04d", year));

                }
            }, year, month, day);
            datePickerDialog.show();
        });


    }

}