package com.story.mipsa.pocketify;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class FirstPage extends AppCompatActivity {
    public static String savedName,name;
    EditText editText;
    Button button;



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("Name",name);
        super.onSaveInstanceState(outState);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

        editText = (EditText)findViewById(R.id.Name);
        button = (Button)findViewById(R.id.next);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = editText.getText().toString().trim();
                Intent intent = new Intent(getApplicationContext(),AttendanceTarget.class);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });
    }
}
