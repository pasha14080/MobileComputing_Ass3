package com.example.itzpulu.assignment_3;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

/**
 * Created by iTz Pulu on 10/2/2016.
 */
public class Add extends AppCompatActivity{
    int disk;
    EditText name,roll_number,semester;
    String from;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);

        name=(EditText)findViewById(R.id.name2);
        setContentView(R.layout.add);
        Bundle extras = getIntent().getExtras();

        disk = extras.getInt("Disk");
        roll_number=(EditText)findViewById(R.id.roll_number2);
        semester=(EditText)findViewById(R.id.semester2);

    }
    public void save(View view) throws IOException {
        name=(EditText)findViewById(R.id.name2);
        roll_number=(EditText)findViewById(R.id.roll_number2);
        semester=(EditText)findViewById(R.id.semester2);
        int flag=0;
        assert name!=null;
        if (name.getText()==null || name.getText().toString().compareTo("")==0) {
            Toast.makeText(Add.this, "Please fill the name", Toast.LENGTH_LONG).show();
            return;
        }
        else if (semester.getText()==null || semester.getText().toString().compareTo("")==0) {
            Toast.makeText(Add.this, "Please fill the semester", Toast.LENGTH_LONG).show();
            return;
        }
        else
        {

            DataBaseHandler db = new DataBaseHandler(this);
            List<Student> s = db.getStudents();
            for (Student st: s)
            {
                String temp = "as"+st.getRoll_no();
                Log.d("Rollno", temp);

                if(st.getRoll_no()==Integer.parseInt(roll_number.getText().toString()))
                {
                    flag= 1;
                }
            }
            if (flag==1)
            {
                Toast.makeText(Add.this,"Data Exist with same Roll Number ! please change the roll number",Toast.LENGTH_LONG).show();
                return;
            }
            else
            {
                db.addStudent(new Student(Integer.valueOf(roll_number.getText().toString()), name.getText().toString(), Integer.valueOf(semester.getText().toString())));
                Toast.makeText(Add.this,"Data Added",Toast.LENGTH_LONG).show();
                if(disk==0)
                {
                    if (isExternalStorageWritable()) {
                        //EXTERNAL
                        String FileName = roll_number.getText().toString();
                        String data = FileName + "\n"+ name.getText().toString()+ "\n" + semester.getText().toString();

                        try {
                            File root = Environment.getExternalStorageDirectory();
                            File dir = new File (root.getAbsolutePath()+"/Pulkit");
                            dir.mkdirs();
                            Log.d("File",dir.toString());
                            File myFile = new File(dir, FileName+".txt");
                            FileWriter writer = new FileWriter(myFile);
                            writer.append(data);
                            writer.flush();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
                else
                {
                    String FileName = roll_number.getText().toString();
                    String data = FileName + "\n"+ name.getText().toString()+ "\n" + semester.getText().toString();
                    try{
                        FileOutputStream fos = openFileOutput(FileName, Context.MODE_PRIVATE);
                        fos.write(data.getBytes());
                        fos.close();
                    }
                    catch ( FileNotFoundException e )
                    {
                        e.printStackTrace();
                    };
                    //INTERNAL
                }
                super.onBackPressed();
            }
        }
        // here goes the database thing
    }
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

}
