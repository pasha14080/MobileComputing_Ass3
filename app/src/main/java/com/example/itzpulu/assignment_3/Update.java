package com.example.itzpulu.assignment_3;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iTz Pulu on 10/2/2016.
 */
public class Update extends AppCompatActivity {
    private ArrayAdapter<String> adapter;
    public ListView listView1;
    public List<String> list;
    static final int code=1;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == code) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                DataBaseHandler db = new DataBaseHandler(this);
                List<Student> s = db.getStudents();
                listView1 = (ListView)findViewById(R.id.listView1);
                list = new ArrayList<String>();
                for (Student st:s)
                {
                    String temp = "Name : "+ st.getName()+ "\nRoll no : "+st.getRoll_no()+"\nSemester : "+st.getSemester();
                    list.add(temp);
                }
                adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
                listView1.setAdapter(adapter);
                listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                        AlertDialog.Builder adb = new AlertDialog.Builder(Update.this);
                        adb.setTitle("Update!!");
                        adb.setMessage("Are you sure?");
                        adb.setNegativeButton("Cancel", null);
                        final int rem = position;
                        adb.setPositiveButton("Ok", new AlertDialog.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int which)
                            {
                                String item = list.get(rem);
                                String[] data = item.split("\n");
                                String[] data1 = data[1].split(" : ");
                                Log.d("data1:", data1[0]);
                                Log.d("Data1:", data1[1]);
                                int value = Integer.parseInt(data1[1]);
                                list.remove(rem);
                                Intent intent = new Intent(Update.this,update_data.class);
                                intent.putExtra("Roll",value);
                                startActivityForResult(intent,code);
                                adapter.notifyDataSetChanged();
                               // Toast.makeText(getBaseContext(), item, Toast.LENGTH_LONG).show();
                            }
                        });
                        adb.show();
                    }
                });
            }
            }
        }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.update);
        DataBaseHandler db = new DataBaseHandler(this);
        List<Student> s = db.getStudents();
        listView1 = (ListView)findViewById(R.id.listView1);
        list = new ArrayList<String>();
        for (Student st:s)
        {
            String temp = "Name : "+ st.getName()+ "\nRoll no : "+st.getRoll_no()+"\nSemester : "+st.getSemester();
            list.add(temp);
        }
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        listView1.setAdapter(adapter);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder adb = new AlertDialog.Builder(Update.this);
                adb.setTitle("Update!!");
                adb.setMessage("Are you sure?");
                adb.setNegativeButton("Cancel", null);
                final int rem = position;
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String item = list.get(rem);
                        String[] data = item.split("\n");
                        String[] data1 = data[1].split(" : ");
                        Log.d("data1:", data1[0]);
                        Log.d("Data1:", data1[1]);
                        int value = Integer.parseInt(data1[1]);
                        list.remove(rem);
                        Intent intent = new Intent(Update.this,update_data.class);
                        intent.putExtra("Roll",value);
                        startActivityForResult(intent,code);
                        adapter.notifyDataSetChanged();
                        //Toast.makeText(getBaseContext(), item, Toast.LENGTH_LONG).show();
                    }
                });
                adb.show();
            }
        });
    }
}