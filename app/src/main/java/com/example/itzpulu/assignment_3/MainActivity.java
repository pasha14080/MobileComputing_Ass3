package com.example.itzpulu.assignment_3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {
    public static  final String Prefrence = "Pref";
    private SharedPreferences sharedPreferences;
    private RadioButton Internal_button,External_button;
    public int disk = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        External_button = (RadioButton)findViewById(R.id.External_button);
        Internal_button = (RadioButton)findViewById(R.id.Internal_button);
        sharedPreferences = getSharedPreferences(Prefrence, Context.MODE_PRIVATE);
        Internal_button.setChecked(true);

        if (sharedPreferences.contains("Disk"))
        {
            if(sharedPreferences.getString("Disk","0").compareTo("Internal")==0)
            {
                Internal_button.setChecked(true);
                disk=1;
            }
            else
            {
                disk=0;
                External_button.setChecked(true);
            }
        }
    }
    public void Internal(View view)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Disk","Internal");
        editor.commit();
    }
    public void External(View view)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Disk","External");
        editor.commit();
    }
    public void Add(View view)
    {
        Intent intent = new Intent(MainActivity.this,Add.class);
        intent.putExtra("Disk",disk);
        intent.putExtra("FROM","Add");
        startActivity(intent);
    }
    public void delete(View view)
    {
        Intent intent = new Intent(MainActivity.this,delete.class);
        intent.putExtra("Disk",disk);
        startActivity(intent);
    }
    public void Update(View view)
    {
        Intent intent = new Intent(MainActivity.this, Update.class);
        intent.putExtra("Disk", disk);
        startActivity(intent);
    }
}
