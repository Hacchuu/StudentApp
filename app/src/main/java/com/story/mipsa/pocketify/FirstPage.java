package com.story.mipsa.pocketify;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class FirstPage extends AppCompatActivity {
    public static final String savedName = "nameKey";
    public static final String mypreference = "mypref";
    public static String name;
    SharedPreferences sharedPreferences;
    EditText editText;
    Button button;



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("Name",name);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        name = (String) savedInstanceState.getSerializable( "Name");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
        sharedPreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);

        editText = (EditText)findViewById(R.id.Name);
        button = (Button)findViewById(R.id.next);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = editText.getText().toString().trim();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(savedName, name);
                editor.commit();

                Intent intent = new Intent(getApplicationContext(),AttendanceTarget.class);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });


        if(sharedPreferences.contains(savedName)){
            name = sharedPreferences.getString(savedName,"");
            editText.setText(name);
//            Intent intent = new Intent(getApplicationContext(),AttendanceTarget.class);
//            intent.putExtra("name", name);
//            startActivity(intent);
        }


        Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);

        if (isFirstRun) {
            //show start activity

            startActivity(new Intent(getApplicationContext(), AttendanceTarget.class));

            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                    .putBoolean("isFirstRun", false).commit();
        }
    }
}
