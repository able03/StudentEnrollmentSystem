package com.example.studentenrollmentsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;
;

public class DBHelper extends SQLiteOpenHelper {
    Context context;

    Cursor c;
    String query;

    public DBHelper(@Nullable Context context) {
        super(context, "Accounts", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        query = "CREATE TABLE IF NOT EXISTS Users(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "FirstName VARCHAR, " +
                "MiddleName VARCHAR, " +
                "LastName VARCHAR, " +
                "Birthday DATE, " +
                "ContactNumber INTEGER, " +
                "Email VARCHAR, " +
                "Username VARCHAR, " +
                "Password VARCHAR, " +
                "Course VARCHAR, " +
                "SubjectOne VARCHAR, " +
                "SubjectTwo VARCHAR, " +
                "ElectiveOne VARCHAR, " +
                "ElectiveTwo VARCHAR)";
        db.execSQL(query);
        String teacherQuery = "CREATE TABLE IF NOT EXISTS Teachers(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name VARCHAR(50), " +
                "subject1 VARCHAR(50), " +
                "subject2 VARCHAR(50), " +
                "subject3 VARCHAR(50), " +
                "schedule1 VARCHAR(50), " +
                "schedule2 VARCHAR(50), " +
                "schedule3 VARCHAR(50), " +
                "username VARCHAR(50), " +
                "password VARCHAR(50))";
        db.execSQL(teacherQuery);
    }

    public boolean addTeacherData(String name, String subj1, String subj2, String subj3, String sched1,
                                  String sched2, String sched3, String username, String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues teachValues = new ContentValues();
        teachValues.put("name", name);
        teachValues.put("subject1", subj1);
        teachValues.put("subject2", subj2);
        teachValues.put("subject3", subj3);
        teachValues.put("schedule1", sched1);
        teachValues.put("schedule2", sched2);
        teachValues.put("schedule3", sched3);
        teachValues.put("username", username);
        teachValues.put("password", password);

        long i = db.insert("Teachers" , null, teachValues);
        if(i != -1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }


    public boolean isTeacherNotRegistered(String username, String password)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Teachers WHERE username = '"+username+"' AND password = '"+password+"'", null);
        if(cursor.moveToFirst())
        {
            return false;
        }
        return true;
    }

    public Cursor GetStudentName(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Users WHERE Username = '"+username+"' AND Password = '"+password+"'", null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            return cursor;
        }
        return cursor;
    }

    public boolean AddData(String fn, String mn, String ln, String bday, String cn, String email, String un, String pw, String course, String s1, String s2, String e1, String e2){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put("FirstName",fn);
        v.put("MiddleName", mn);
        v.put("LastName", ln);
        v.put("Birthday", bday);
        v.put("ContactNumber", cn);
        v.put("Email", email);
        v.put("Username", un);
        v.put("Password", pw);
        v.put("Course", course);
        v.put("SubjectOne", s1);
        v.put("SubjectTwo", s2);
        v.put("ElectiveOne", e1);
        v.put("ElectiveTwo", e2);
        long res = db.insert("Users",null,v);
        if(res ==-1){
            Toast.makeText(this.context, "Failed to add data", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            Toast.makeText(this.context, "Successfully added data", Toast.LENGTH_SHORT).show();
            return true;
        }

    }

    public boolean FindAccount(String un, String pw){
        SQLiteDatabase db = this.getWritableDatabase();
        query = "SELECT * FROM Users WHERE Username = un AND Password = pw";
        c = db.rawQuery(query,null);
        if(c.getCount() > 0){
            // Account is found
            return true;
        }else{
            // Account is not found
            return false;
        }
    }

    public Cursor GetAllData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Users", null);
        return c;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean checkLogin(String username, String password)
    {
        SQLiteDatabase db= this.getReadableDatabase();
        if(username.substring(0,5).equals("ROS01"))
        {
            Cursor cursor = db.rawQuery("SELECT * FROM Teachers WHERE Username = '"+username+"' AND Password = '"+password+"'", null);
            if(cursor.moveToFirst())
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            Cursor cursor = db.rawQuery("SELECT * FROM Users WHERE Username = '"+username+"' AND Password = '"+password+"'", null);
            if(cursor.moveToFirst())
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }

    public boolean addSubjects(String course, String subject1, String subject2, String elective1, String elective2, int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Course", course);
        values.put("SubjectOne", subject1);
        values.put("SubjectTwo", subject2);
        values.put("ElectiveOne", elective1);
        values.put("ElectiveTwo", elective2);


//        db.rawQuery("UPDATE Users SET SubjectOne = '"+subject1+"', SubjectTwo = '"+subject2+"', ElectiveOne = '"+elective1+"', ElectiveTwo = '"+elective2+"' WHERE ID = '"+id+"'", null);

        long i = db.update("Users", values, "ID = '"+id+"'", null);
        if(i != -1)
        {
            Toast.makeText(context, "success", Toast.LENGTH_SHORT).show();
            return true;
        }
        else
        {
            Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    public int findStudentId(String username, String password)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Users WHERE Username = '"+username+"' AND Password = '"+password+"'", null);

        int id = 0;
        if(cursor.moveToFirst())
        {
           id = Integer.parseInt(cursor.getString(0));

        }
        return id;
    }

    public Cursor findStudent(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM Users WHERE ID = '"+id+"'", null);
    }

    public void checkSchedule(String course)
    {
        SQLiteDatabase db = this.getReadableDatabase();

    }



}
