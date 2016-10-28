package com.example.itzpulu.assignment_3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iTz Pulu on 10/2/2016.
 */
public class DataBaseHandler extends SQLiteOpenHelper {
    private static final int version = 1;
    private static final String Database_name = "Database";
    private static final String Table_name = "student";
    private static final String name = "Name";
    private static final String roll_no = "Roll_Number";
    private static final String semester = "semester";

    public DataBaseHandler(Context context) {
        super(context, Database_name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Create_table = "CREATE TABLE " + Table_name + "(" + roll_no + " INTEGER PRIMARY KEY, " + name + " TEXT," + semester + " TEXT" + ")";
        db.execSQL(Create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Table_name);

        // Create tables again
        onCreate(db);
    }

    void addStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(name, student.getName());
        values.put(roll_no, student.getRoll_no());
        values.put(semester, student.getSemester());
        db.insert(Table_name, null, values);
        db.close();
    }

    public int deleteStudent(Integer value) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(Table_name, "Roll_Number = ? ", new String[]{Integer.toString(value)});
    }

    public List<Student> getStudents() {
        List<Student> ret = new ArrayList<Student>();
        boolean flag = false;
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from " + Table_name;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setName(cursor.getString(1));
                student.setRoll_no(Integer.parseInt(cursor.getString(0)));
                student.setSemester(Integer.parseInt(cursor.getString(2)));
                ret.add(student);
            } while (cursor.moveToNext());
        }
        return ret;
    }

}

