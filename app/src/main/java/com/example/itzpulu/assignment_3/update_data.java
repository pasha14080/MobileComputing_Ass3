package com.example.itzpulu.assignment_3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

/**
 * Created by iTz Pulu on 10/2/2016.
 */
public class update_data extends AppCompatActivity {
    public  EditText name2,roll_number2,semester2;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_data);
        roll_number2=(EditText)findViewById(R.id.roll_number2);
        Bundle extras = getIntent().getExtras();
        int r = extras.getInt("Roll");
        Log.d("Value",""+r);
        roll_number2.setText(""+r);
        roll_number2.setKeyListener(null);
    }
    public void save2(View view)
    {

        name2=(EditText)findViewById(R.id.name2);
        int flag=0;

        semester2=(EditText)findViewById(R.id.semester2);
        if (name2.getText()==null || name2.getText().toString().compareTo("")==0)
        {
            Toast.makeText(update_data.this,"Please fill the name",Toast.LENGTH_LONG).show();
            return;
        }

        else if (semester2.getText()==null || semester2.getText().toString().compareTo("")==0) {
            Toast.makeText(update_data.this, "Please fill the semester", Toast.LENGTH_LONG).show();
            return;
        }
        else
        {

            DataBaseHandler db = new DataBaseHandler(this);
            db.deleteStudent(Integer.parseInt(roll_number2.getText().toString()));
            db.addStudent(new Student(Integer.valueOf(roll_number2.getText().toString()), name2.getText().toString(), Integer.valueOf(semester2.getText().toString())));
            Toast.makeText(update_data.this,"Data updated",Toast.LENGTH_LONG).show();
            Intent resultIntent = new Intent();
            setResult(RESULT_OK,resultIntent);
            super.onBackPressed();
            }
        }
        // here goes the database thing
    }
